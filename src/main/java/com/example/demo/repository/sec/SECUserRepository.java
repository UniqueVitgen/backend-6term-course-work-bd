package com.example.demo.repository.sec;

import com.example.demo.entity.sec.SECUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SECUserRepository extends CrudRepository<SECUser, Integer> {
    @Override
    List<SECUser> findAll();

}
