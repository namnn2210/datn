package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.dao.HashedOrderRepository;
import ginp14.ngongocnam.datn.dao.OrderRepository;
import ginp14.ngongocnam.datn.dao.UserRepository;
import ginp14.ngongocnam.datn.model.HashedOrder;
import ginp14.ngongocnam.datn.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HashedOrderRepository hashedOrderRepository;
    @Autowired
    private KeyService keyService;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> findByUserId(int id) {
        List<HashedOrder> hashedOrders = hashedOrderRepository.findByUserId(id);
        List<Order> orders = new ArrayList<>();
        for (HashedOrder hashedOrder : hashedOrders) {
            if(keyService.verifySignature(hashedOrder.getHashedOrderInfo(), hashedOrder.getSignedOrderInfo(), hashedOrder)) {
                Order order = (Order) Order.convertFromBytes(hashedOrder.getHashedOrderInfo());
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public List<HashedOrder> findAll() {
        return hashedOrderRepository.findAll();
    }

    @Override
    public HashedOrder findById(int id) {
        return hashedOrderRepository.findById(id);
    }

    @Override
    public List<Order> findAllByStatus(boolean status) {
        return orderRepository.findAllByStatus(status);
    }

    @Override
    public void save(HashedOrder hashedOrder) {
        hashedOrderRepository.save(hashedOrder);
    }
}
