package ginp14.ngongocnam.datn.dao;

import ginp14.ngongocnam.datn.model.HashedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HashedOrderRepository extends JpaRepository<HashedOrder, Integer> {
    @Query(value = "SELECT o.* FROM hashed_order o where o.user_id = ?1", nativeQuery = true)
    List<HashedOrder> findByUserId(int id);

    HashedOrder findById(int id);
}
