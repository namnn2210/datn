package ginp14.ngongocnam.datn.dao;

import ginp14.ngongocnam.datn.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);

    Product findById(int id);

    List<Product> findAllByCategoryId(int id);

    Page<Product> findAllByCategoryId(int id, Pageable pageable);

    //    List<Product> findAllByTeamId(int id);
    Page<Product> findAllByTypeIdAndStatus(int id, boolean status, Pageable pageable);

    Page<Product> findAllByStatus(boolean status, Pageable pageable);

    List<Product> findAllByStatus(boolean status);

    List<Product> findAllByCategoryIdAndStatus(int id, boolean status);

    Page<Product> findAllByCategoryIdAndStatus(int id, boolean status, Pageable pageable);
}
