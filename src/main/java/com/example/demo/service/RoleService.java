package com.example.demo.service;

import com.example.demo.entity.Role;

import java.util.List;

public interface RoleService {

    Role findOne(Integer id);

    Role save(Role role);

    Role Edit(Role role);

    List<Role> findAll();

    Role findByName(String name);

    void delete(Integer id);
}
