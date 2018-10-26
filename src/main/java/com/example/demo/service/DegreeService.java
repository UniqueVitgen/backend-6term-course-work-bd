package com.example.demo.service;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Title;

import java.util.List;

public interface DegreeService {

    Degree findByName(String name);

    List<Degree> findAll();

    Degree findById(Integer id);

    Degree save(Degree degree);

    Degree edit(Degree degree);

    void delete(Degree degree);

    void delete(Integer id);
}
