package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.dao.OrderRepository;
import ginp14.ngongocnam.datn.dao.UserRepository;
import ginp14.ngongocnam.datn.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> findByUserId(int id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAllByStatus(boolean status) {
        return orderRepository.findAllByStatus(status);
    }
}
