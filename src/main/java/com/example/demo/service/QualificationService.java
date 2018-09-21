package com.example.demo.service;

import com.example.demo.entity.Qualification;

import java.util.List;

public interface QualificationService {

    Qualification findById(Integer id);

    List<Qualification> findAll();

    Qualification save(Qualification qualification);

    Qualification edit(Qualification qualification);

    void delete(Integer id);

    void delete(Qualification qualification);
}
