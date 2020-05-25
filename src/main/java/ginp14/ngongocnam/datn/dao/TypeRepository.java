package ginp14.ngongocnam.datn.dao;

import ginp14.ngongocnam.datn.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    Type findById(int id);
    List<Type> findAllByCategoryId(int id);
    Page<Type> findAll(Pageable pageable);
}
