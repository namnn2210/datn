package ginp14.ngongocnam.datn.controller;

import ginp14.ngongocnam.datn.service.CategoryService;
import ginp14.ngongocnam.datn.service.ProductService;
import ginp14.ngongocnam.datn.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("clothing", typeService.findAllByCategoryId(1));
        model.addAttribute("activewear", typeService.findAllByCategoryId(2));
        model.addAttribute("accessories", typeService.findAllByCategoryId(3));
        return "template_v2/views/index";
    }

    @GetMapping("/homepage")
    public String showHomepage(Model model) {
        model.addAttribute("categories", categoryService.findAllByStatus(true));
        model.addAttribute("products", productService.findAllByStatus(true));
        return "views/index";
    }

    @GetMapping("/403")
    public String showForbidden() {
        return "views/other/page_error_403";
    }
}
