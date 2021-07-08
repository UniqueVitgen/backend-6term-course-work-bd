package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Faculty;

import java.util.List;

public interface DepartmentService {

    Department findByName(String name);

    List<Department> findAll();

    List<Department> findAllByFaculty(Faculty faculty);

    List<Department> findAllByFacultyId(Integer idFaculty);

    Department save(Department department);

    Department Edit(Department department);

    Department get(Integer id);

    void delete(Integer id);
}
