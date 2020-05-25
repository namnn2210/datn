package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    void save(Category category);
    Category findById(int id);
    List<Category> findAllByStatus(boolean status);
    Page<Category> findAllByStatus(boolean status, Pageable pageable);
    Page<Category> findAll(Pageable pageable);
}
