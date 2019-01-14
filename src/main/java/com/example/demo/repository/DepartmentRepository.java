package com.example.demo.repository;

import com.example.demo.entity.Department;
import com.example.demo.entity.Department;
import com.example.demo.entity.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    @Query("SELECT t.name FROM Department as t where t.name = :name")
    Department findByName(@Param("name") String name);

    @Override
    List<Department> findAll();

    List<Department> findAllByFaculty(Faculty faculty);
}
