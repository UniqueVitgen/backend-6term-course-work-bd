package com.example.demo.service;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Title;

import java.util.List;

public interface DegreeService {

    Degree findByName(String name);

    List<Degree> findAll();

    Degree findById(Integer id);
}
