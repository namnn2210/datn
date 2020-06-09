package ginp14.ngongocnam.datn.dao;

import ginp14.ngongocnam.datn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findById(int id);
}
