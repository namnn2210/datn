package ginp14.ngongocnam.datn.controller;

import ginp14.ngongocnam.datn.model.Product;
import ginp14.ngongocnam.datn.model.Type;
import ginp14.ngongocnam.datn.service.ProductService;
import ginp14.ngongocnam.datn.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TeamController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private ProductService productService;

//    @PostMapping("/addTeam")
//    public String addTeam(@Valid @ModelAttribute("team") Type type, BindingResult result) {
//        if (result.hasErrors()) {
//            return "views/admin/add_team";
//        }
//        type.setStatus(true);
//        typeService.save(type);
//        return "redirect:/admin/listTeams";
//    }
//
//    @PostMapping("/deleteTeam")
//    public @ResponseBody
//    String deleteTeam(@RequestBody int teamId) {
//        Type type = typeService.findById(teamId);
//        List<Product> products = productService.findAllByTeamId(teamId);
//        for (Product product:products) {
//            product.setStatus(false);
//            productService.save(product);
//        }
//        type.setStatus(false);
//        typeService.save(type);
//        return "false";
//    }
}
