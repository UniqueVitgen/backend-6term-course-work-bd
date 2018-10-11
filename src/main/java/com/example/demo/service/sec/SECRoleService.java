package com.example.demo.service.sec;

import com.example.demo.entity.sec.SECRole;

import java.util.List;

public interface SECRoleService {

    List<SECRole> findAll();

    SECRole findById(Integer id);

    SECRole save(SECRole secRole);

    SECRole edit(SECRole secRole);

    void delete(SECRole secRole);

    void delete(Integer id);
}
