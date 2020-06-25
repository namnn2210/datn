package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.dao.HashedOrderRepository;
import ginp14.ngongocnam.datn.model.HashedOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashedOrderServiceImpl implements HashOrderService {

    @Autowired
    private HashedOrderRepository hashedOrderRepository;

    @Override
    public List<HashedOrder> findAllByUserId(int id) {
        return hashedOrderRepository.findByUserId(id);
    }
}
