package ginp14.ngongocnam.datn.dao;

import ginp14.ngongocnam.datn.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    Category findById(int id);

    List<Category> findAllByStatus(boolean status);

    Page<Category> findAllByStatus(boolean status, Pageable pageable);
}
