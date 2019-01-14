package com.example.demo.service;

import com.example.demo.entity.University;

import java.util.List;

public interface UniversityService {

    University findByName(String name);

    List<University> findAll();

    University save(University faculty);

    University Edit(University faculty);

    University get(Integer id);

    void delete(Integer id);
}
