package ginp14.ngongocnam.datn.dao;

import ginp14.ngongocnam.datn.model.GenerateKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.security.PrivateKey;

public interface KeyRepository extends JpaRepository<GenerateKeys, Integer> {
    @Query(value = "SELECT private_key FROM digital_sign ds where ds.order_id = ?1", nativeQuery = true)
    byte[] findPrivateKeyByOrderId(int id);

    @Query(value = "SELECT public_key FROM digital_sign ds where ds.hashed_order_id = ?1", nativeQuery = true)
    byte[] findPublicKeyByOrderId(int id);
}
