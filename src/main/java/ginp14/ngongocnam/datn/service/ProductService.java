package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    List<Product> findAll();

    Product findById(int id);

    List<Product> findAllByCategoryId(int id);

    Page<Product> findAllByCategoryId(int id, Pageable pageable);

    Page<Product> findAllByTypeIdAndStatus(int id, boolean status, Pageable pageable);

    void save(Product product);

    //    List<Product> findAllByTeamId(int id);
    Page<Product> findAllByStatus(boolean status, Pageable pageable);

    List<Product> findAllByStatus(boolean status);

    List<Product> findAllByCategoryIdAndStatus(int id, boolean status);

    Page<Product> findAllByCategoryIdAndStatus(int id, boolean status, Pageable pageable);
}
