package ginp14.ngongocnam.datn.controller;

import ginp14.ngongocnam.datn.model.*;
import ginp14.ngongocnam.datn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;


    private List<Category> categories = null;

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "views/admin/dashboard";
    }

    @GetMapping("/listUsers")
    public String showListUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "views/admin/list_users";
    }

    @GetMapping("/listProducts")
    public String showListProducts(Model model, @PageableDefault(size = 10) Pageable pageable) {
        model.addAttribute("products", productService.findAll(pageable));
        return "views/admin/list_products";
    }

    @GetMapping("/listCategories")
    public String showListCategories(Model model, @PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("categories", categoryService.findAll(pageable));
        return "views/admin/list_categories";
    }

    @GetMapping("/listTeams")
    public String showListTeams(Model model, @PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("teams", typeService.findAll(pageable));
        return "views/admin/list_teams";
    }

    @GetMapping("/listOrders")
    public String showListOrder(Model model) {
        List<HashedOrder> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "views/admin/list_orders";
    }

    @GetMapping("/orderDetail")
    public String showOrderDetail(@RequestParam("orderId") int id, Model model) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(id);
        model.addAttribute("orderId", id);
        model.addAttribute("orderDetails", orderDetails);
        return "views/admin/order_detail";
    }

    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("teams", typeService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("product", product);
        return "views/admin/add_product";
    }

    @GetMapping("/editProduct")
    public String showEditProductForm(@RequestParam("productId") int id, Model model) {
        Product product = productService.findById(id);
        categories = categoryService.findAll();
        List<Type> teams = typeService.findAll();
        model.addAttribute("teams", teams);
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "views/admin/add_product";
    }

    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        User user = new User();
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "views/admin/add_user";
    }

    @GetMapping("/editUser")
    public String showEditUserForm(@RequestParam("userId") int id, Model model) {
        User user = userService.findById(id);
        List<Role> roles = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "views/admin/add_user";
    }

    @GetMapping("/addCategory")
    public String showAddCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "views/admin/add_category";
    }

    @GetMapping("/editCategory")
    public String showEditCategoryForm(@RequestParam("catId") int id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "views/admin/add_category";
    }

//    @GetMapping("/addTeam")
//    public String showAddTeamForm(Model model) {
//        Type type = new Type();
//        model.addAttribute("type",type);
//        return "views/admin/add_team";
//    }

//    @GetMapping("/editTeam")
//    public String showEditTeamForm(@RequestParam("teamId") int id, Model model) {
//        Type type = typeService.findById(id);
//        model.addAttribute("type", type);
//        return "views/admin/add_team";
//    }

    @GetMapping("/addOrder")
    public String showAddOrderForm(Model model) {
        Order order = new Order();
        OrderDetail orderDetail = new OrderDetail();
        List<Product> products = productService.findAllByStatus(true);
        model.addAttribute("order", order);
        model.addAttribute("orderDetail", orderDetail);
        model.addAttribute("products", products);
        return "views/admin/add_order";
    }

    @GetMapping("/getTotalProducts")
    public @ResponseBody
    int getTotalProducts() {
        List<Product> products = productService.findAll();
        int size = products.size();
        return size;
    }

    @GetMapping("/getStatusProducts")
    public @ResponseBody
    Map<String, Integer> getActiveProducts() {
        List<Product> activeProducts = productService.findAllByStatus(true);
        Map<String, Integer> statusProducts = new HashMap<>();
        statusProducts.put("active", activeProducts.size());
        statusProducts.put("defective", productService.findAll().size() - activeProducts.size());
        return statusProducts;
    }

//    @GetMapping("/getTotalSales")
//    public @ResponseBody
//    double getTotalSales() {
//        List<HashedOrder> orders = orderService.findAll();
//        double totalSales = 0.0;
//        for (HashedOrder order : orders) {
//            totalSales += order.getTotalPrice();
//        }
//        return totalSales;
//    }

    @GetMapping("/getStatusOrders")
    public @ResponseBody
    Map<String, Integer> getStatusOrders() {
        List<Order> confirmedOrders = orderService.findAllByStatus(true);
        List<Order> pendingOrders = orderService.findAllByStatus(false);
        Map<String, Integer> statusOrders = new HashMap<>();
        statusOrders.put("confirmed", confirmedOrders.size());
        statusOrders.put("pending", pendingOrders.size());
        return statusOrders;
    }
}
