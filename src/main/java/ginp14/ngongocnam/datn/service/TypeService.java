package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    List<Type>findAll();
    Type findById(int id);
    void save(Type team);
    List<Type> findAllByCategoryId(int id);
    Page<Type> findAll(Pageable pageable);
}
