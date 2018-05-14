package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findOne(Integer id) {

        Optional<Role> optional = roleRepository.findById(id);
        Role role = optional.get();
        return role;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
