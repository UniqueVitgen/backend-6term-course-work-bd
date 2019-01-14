package com.example.demo.service;

import com.example.demo.entity.Faculty;
import com.example.demo.entity.University;

import java.util.List;

public interface FacultyService {

    Faculty findByName(String name);

    List<Faculty> findAll();

    List<Faculty> findAllByUniversity(University university);

    List<Faculty> findAllByUniversityId(Integer id);

    Faculty save(Faculty faculty);

    Faculty Edit(Faculty faculty);

    Faculty get(Integer id);

    void delete(Integer id);
}
