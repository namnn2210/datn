package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findById(int id);
}
