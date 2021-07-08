package com.example.demo.service.sec;

import com.example.demo.entity.sec.SEC;

import java.util.List;

public interface SECService {

    List<SEC> findAll();

    SEC findById(Integer id);

    SEC save(SEC sec);

    SEC edit(SEC sec);

    void delete(SEC sec);

    void delete(Integer id);
}
