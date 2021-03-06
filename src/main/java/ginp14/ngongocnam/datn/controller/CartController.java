package ginp14.ngongocnam.datn.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import ginp14.ngongocnam.datn.config.PaypalPaymentIntent;
import ginp14.ngongocnam.datn.config.PaypalPaymentMethod;
import ginp14.ngongocnam.datn.model.*;
import ginp14.ngongocnam.datn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.security.PrivateKey;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private PaypalService paypalService;

    @Autowired
    HttpSession session;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private KeyService keyService;

    private static final String SUCCESS_URL = "/cart/checkout/success";
    private static final String CANCEL_URL = "/cart/checkout/cancel";

    private String checkoutName = "";
    private String checkoutAddress = "";
    private String checkoutPhone = "";

    @GetMapping("/detail")
    public String showCartDetail(Model model, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            return "redirect:/cart/noitem";
        }
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("clothing", typeService.findAllByCategoryId(1));
        model.addAttribute("activewear", typeService.findAllByCategoryId(2));
        model.addAttribute("accessories", typeService.findAllByCategoryId(3));
        return "template_v2/views/product/cart";
    }

    @GetMapping("/go-to-checkout")
    public String gotoCheckout(Model model, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            return "redirect:/cart/noitem";
        }
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("clothing", typeService.findAllByCategoryId(1));
        model.addAttribute("activewear", typeService.findAllByCategoryId(2));
        model.addAttribute("accessories", typeService.findAllByCategoryId(3));
        return "template_v2/views/product/checkout";
    }

    @GetMapping("/noitem")
    public String showNoItemError(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("clothing", typeService.findAllByCategoryId(1));
        model.addAttribute("activewear", typeService.findAllByCategoryId(2));
        model.addAttribute("accessories", typeService.findAllByCategoryId(3));
        return "template_v2/views/product/no_item_cart";
    }

    @GetMapping("/ordersuccess")
    public String showOrderSuccess() {
        return "views/order/order_confirmation";
    }

    @PostMapping("/updateCart")
    public @ResponseBody
    ShoppingCart updateCart(@RequestBody List<ProductCriteria> productsCriteria, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItem();
        for (ProductCriteria productCriteria : productsCriteria) {
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getId() == productCriteria.getId() && cartItem.getSize().equalsIgnoreCase(productCriteria.getSize())) {
                    cartItem.setQuantity(productCriteria.getQuantity());
                }
            }
        }
        cart.setTotalPrice(ProductController.getTotalPrice(cart.getCartItem()));
        return cart;
    }

    @PostMapping("/removeCartProduct")
    public @ResponseBody
    ShoppingCart removeFromCart(@RequestBody ProductCriteria productCriteria, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItem();
        cartItems.removeIf(cartItem -> cartItem.getProduct().getId() == productCriteria.getId() && cartItem.getSize().equalsIgnoreCase(productCriteria.getSize()));
        cart.setTotalPrice(ProductController.getTotalPrice(cart.getCartItem()));
        return cart;
    }

    @GetMapping("/checkout")
    public String checkout(Principal principal, Model model) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItem();
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(checkoutAddress);
        order.setShippingName(checkoutName);
        order.setShippingPhone(checkoutPhone);
        order.setTotalPrice(cart.getTotalPrice());
//        orderService.save(order);
        GenerateKeys keys = keyService.generateKeys();
//        keyService.save(keys);
//        Order to_order = orderService.findById(1);
        byte[] byteOrder = Order.convertToBytes(order);
        byte[] signed_order = keyService.sign(byteOrder, keys.getPrivateKey());
        HashedOrder hashedOrder = new HashedOrder(user, byteOrder, signed_order);
        orderService.save(hashedOrder);
        keys.setHashedOrder(hashedOrder);
        keyService.save(keys);
//        System.out.println(signed_order);
//        if (keyService.verifySignature(hashed_order, signed_order, to_order)) {
//            System.out.println("true");
//            Order order1 = (Order) Order.convertFromBytes(hashed_order);
//            System.out.println(order1);
//        }
        for (CartItem item : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(hashedOrder);
            orderDetail.setProduct(item.getProduct());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setSize(item.getSize());
            orderDetail.setItemTotalPrice(item.getQuantity() * item.getProduct().getPrice());
            orderDetailService.save(orderDetail);
        }
        //Send order confirmation
        try {
            String subject = "KitStore Order Confirmation";
            String templateFileName = "views/order/email_confirmation";
            Context context = new Context();
            emailService.sendEmail(user.getEmail(), subject, templateFileName, context);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        model.addAttribute("user", user);
        model.addAttribute("order", order);
        model.addAttribute("cartItems", cartItems);
        session.removeAttribute("cart");
        return "views/order/order_confirmation";
    }

    @PostMapping("/pay")
    public String pay(@RequestParam(required = false, name = "checkoutName") String checkoutName, @RequestParam(required = false, name = "checkoutAddress") String checkoutAddress, @RequestParam(required = false, name = "checkoutPhone") String checkoutPhone, HttpSession session, Principal principal, RedirectAttributes attributes) {
        if (principal == null) {
            return "redirect:/login";
        }
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        this.checkoutAddress = checkoutAddress;
        this.checkoutName = checkoutName;
        this.checkoutPhone = checkoutPhone;
        try {
            Payment payment = paypalService.createPayment(cart.getTotalPrice(), "USD", PaypalPaymentMethod.paypal, PaypalPaymentIntent.order, "http://localhost:8080" + CANCEL_URL, "http://localhost:8080" + SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException ppex) {
            ppex.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/checkout/cancel")
    public String cancelCheckout() {
        return "redirect:/cart/detail";
    }

    @GetMapping("/checkout/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "redirect:/cart/checkout";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
