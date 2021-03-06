package com.example.demo.repository.sec;

import com.example.demo.entity.sec.SECRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SECRoleRepository extends CrudRepository<SECRole, Integer> {
    @Override
    List<SECRole> findAll();
}
