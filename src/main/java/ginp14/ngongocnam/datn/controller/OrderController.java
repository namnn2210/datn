package ginp14.ngongocnam.datn.controller;

import ginp14.ngongocnam.datn.model.Order;
import ginp14.ngongocnam.datn.service.OrderDetailService;
import ginp14.ngongocnam.datn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

//    @PostMapping("/confirmOrder")
//    public @ResponseBody
//    String confirmOrder(@RequestBody int orderId) {
//        Order order = orderService.findById(orderId);
//        order.setStatus(true);
//        orderService.save(order);
//        return "false";
//    }
}
