package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.model.HashedOrder;
import ginp14.ngongocnam.datn.model.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);

    List<Order> findByUserId(int id);

//    List<Order> findAll();

//    List<HashedOrder> findByUserId(int id);
    List<HashedOrder> findAll();

//    Order findById(int id);
    HashedOrder findById(int id);

    List<Order> findAllByStatus(boolean status);

    void save(HashedOrder hashedOrder);
}
