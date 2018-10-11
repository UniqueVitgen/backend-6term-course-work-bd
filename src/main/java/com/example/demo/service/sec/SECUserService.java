package com.example.demo.service.sec;

import com.example.demo.entity.sec.SECUser;

import java.util.List;

public interface SECUserService {

    List<SECUser> findAll();

    SECUser findById(Integer id);

    SECUser save(SECUser secUser);

    SECUser edit(SECUser secUser);

    void delete(SECUser secUser);

    void delete(Integer id);
}
