package ginp14.ngongocnam.datn.dao;

import ginp14.project3.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findById(int id);
}
