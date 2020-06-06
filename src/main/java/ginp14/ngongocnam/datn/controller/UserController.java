package ginp14.ngongocnam.datn.controller;

import ginp14.ngongocnam.datn.model.*;
import ginp14.ngongocnam.datn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TypeService typeService;

//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;


    @GetMapping("/login")
    public String showLogin(Model model) {
        User user = new User();
        model.addAttribute("clothing", typeService.findAllByCategoryId(1));
        model.addAttribute("activewear", typeService.findAllByCategoryId(2));
        model.addAttribute("accessories", typeService.findAllByCategoryId(3));
        model.addAttribute("categories", categoryService.findAllByStatus(true));
        model.addAttribute("user", user);
        return "template_v2/views/user/login";
    }

    @GetMapping("/adminLogin")
    public String showAdminLogin(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "views/admin/login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("categories", categoryService.findAllByStatus(true));
        return "views/user/register";
    }

    @PostMapping("/userRegisterProcess")
    public String userRegisterProcess(@RequestParam("cfpassword") String cfpassword, @Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("clothing", typeService.findAllByCategoryId(1));
            model.addAttribute("activewear", typeService.findAllByCategoryId(2));
            model.addAttribute("accessories", typeService.findAllByCategoryId(3));
            return "template_v2/views/user/login";
        }
        if(!cfpassword.equals(user.getPassword())) {
            model.addAttribute("passwordNotMatch", true);
            model.addAttribute("clothing", typeService.findAllByCategoryId(1));
            model.addAttribute("activewear", typeService.findAllByCategoryId(2));
            model.addAttribute("accessories", typeService.findAllByCategoryId(3));
            return "template_v2/views/user/login";
        }
        if (userService.isUserExisted(user.getUsername())) {
            model.addAttribute("usernameExist", true);
            model.addAttribute("clothing", typeService.findAllByCategoryId(1));
            model.addAttribute("activewear", typeService.findAllByCategoryId(2));
            model.addAttribute("accessories", typeService.findAllByCategoryId(3));
            return "template_v2/views/user/login";
        } else if (userService.isEmailExisted(user.getEmail())) {
            model.addAttribute("clothing", typeService.findAllByCategoryId(1));
            model.addAttribute("activewear", typeService.findAllByCategoryId(2));
            model.addAttribute("accessories", typeService.findAllByCategoryId(3));
            model.addAttribute("emailExist", true);
            return "template_v2/views/user/login";
        } else {
            if (user.getRole() == null) {
                Role role = roleService.findById(1);
                user.setRole(role);
            }
            userService.saveUser(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenService.save(confirmationToken);
            try {
                String subject = "KitStore Registration Confirmation";
                String templateFileName = "views/user/confirm";
                Context context = new Context();
                context.setVariable("token", confirmationToken.getConfirmationToken());
                emailService.sendEmail(user.getEmail(), subject, templateFileName, context);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
        return "redirect:/registerSuccess";
    }

    @PostMapping("/addUser")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Role> roles = roleService.findAll();
            model.addAttribute("roles", roles);
            return "views/admin/add_user";
        }
        if (user.getUsername() == null) {
            if (userService.isUserExisted(user.getUsername())) {
                List<Role> roles = roleService.findAll();
                model.addAttribute("roles", roles);
                model.addAttribute("usernameExist", true);
                return "views/admin/add_user";
            }
        }
        if (user.getEmail() == null) {
            if (userService.isEmailExisted(user.getEmail())) {
                List<Role> roles = roleService.findAll();
                model.addAttribute("roles", roles);
                model.addAttribute("emailExist", true);
                return "views/admin/add_user";
            }
        }
        userService.saveUser(user);

        return "redirect:/admin/listUsers";
    }

    @PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes
            attributes) {
        if (result.hasErrors()) {
            return "redirect:/profile";
        }
        userService.saveUser(user);
        attributes.addFlashAttribute("updateSucces", true);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:login";
        }
        String username = principal.getName();
        User user = userService.findByUsername(username);
//        model.addAttribute("categories", categoryService.findAllByStatus(true));
        model.addAttribute("clothing", typeService.findAllByCategoryId(1));
        model.addAttribute("activewear", typeService.findAllByCategoryId(2));
        model.addAttribute("accessories", typeService.findAllByCategoryId(3));
        model.addAttribute("user", user);
        return "template_v2/views/user/profile";
    }

    @GetMapping("/orderHistory")
    public String showOrderHistory(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:login";
        }
        String username = principal.getName();
        User user = userService.findByUsername(username);
        List<Order> orders = orderService.findByUserId(user.getId());
        HashMap<Integer, List<OrderDetail>> maps = new HashMap<>();
        for (Order order : orders) {
            List<OrderDetail> orderDetails = orderDetailService.findByOrderId(order.getId());
            maps.put(order.getId(), orderDetails);
        }
        model.addAttribute("orders", orders);
        model.addAttribute("maps", maps);
        model.addAttribute("categories", categoryService.findAllByStatus(true));
        model.addAttribute("clothing", typeService.findAllByCategoryId(1));
        model.addAttribute("activewear", typeService.findAllByCategoryId(2));
        model.addAttribute("accessories", typeService.findAllByCategoryId(3));
        return "template_v2/views/user/order_history";
    }

    @PostMapping("/confirmAccount")
    public String showConfirmAccountPage(@RequestParam String token, Model model) {
        ConfirmationToken confirmToken = confirmationTokenService.findByConfirmationToken(token);
        if (token != null) {
            if (new Date().after(confirmToken.getExpired_at())) {
                model.addAttribute("expired", true);
                return "views/other/register_success";
            } else {
                User user = userService.findByUsername(confirmToken.getUser().getUsername());
                user.setStatus(true);
                userService.saveUser(user);
            }
        } else {
            model.addAttribute("error", true);
            return "views/other/register_success";
        }
        return "redirect:/";
    }

    @GetMapping("/verifyError")
    public String showVerifyErrorForm(Model model) {
        model.addAttribute("categories", categoryService.findAllByStatus(true));
        return "views/other/verify_error";
    }

    @GetMapping("/registerSuccess")
    public String showSuccessForm(Model model) {
        model.addAttribute("categories", categoryService.findAllByStatus(true));
        return "views/other/register_success";
    }

//    @PostMapping("/authenticate")
//    public @ResponseBody String generateToken(@RequestBody UserCriteria userCriteria) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userCriteria.getUsername(), userCriteria.getPassword())
//            );
//        } catch (Exception ex) {
//            System.err.println(ex);
//        }
//        return jwtUtil.generateToken(userCriteria.getUsername());
//    }
}
