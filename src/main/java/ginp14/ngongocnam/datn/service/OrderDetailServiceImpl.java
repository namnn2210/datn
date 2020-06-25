package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.dao.HashedOrderRepository;
import ginp14.ngongocnam.datn.dao.OrderDetailRepository;
import ginp14.ngongocnam.datn.model.HashedOrder;
import ginp14.ngongocnam.datn.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> findByOrderId(int id) {
        return orderDetailRepository.findByOrderId(id);
    }


}
