package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    void save(OrderDetail orderDetail);

    List<OrderDetail> findByOrderId(int id);


}
