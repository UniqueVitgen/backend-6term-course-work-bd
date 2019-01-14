package com.example.demo.repository;

import com.example.demo.entity.University;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversityRepository extends CrudRepository<University, Integer> {
    @Query("SELECT t.name FROM University as t where t.name = :name")
    University findByName(@Param("name") String name);

    @Override
    List<University> findAll();
}
