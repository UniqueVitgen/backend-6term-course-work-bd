package com.example.demo.repository;

import com.example.demo.entity.Faculty;
import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacultyRepository extends CrudRepository<Faculty, Integer> {
    @Query("SELECT t.name FROM Faculty as t where t.name = :name")
    Faculty findByName(@Param("name") String name);

    @Override
    List<Faculty> findAll();


}
