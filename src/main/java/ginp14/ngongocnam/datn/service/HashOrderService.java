package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.model.HashedOrder;

import java.util.List;

public interface HashOrderService {
    List<HashedOrder> findAllByUserId(int id);
}
