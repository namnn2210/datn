package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.model.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);

    List<Order> findByUserId(int id);

    List<Order> findAll();

    Order findById(int id);

    List<Order> findAllByStatus(boolean status);
}
