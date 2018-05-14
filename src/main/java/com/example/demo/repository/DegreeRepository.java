package com.example.demo.repository;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Faculty;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DegreeRepository extends CrudRepository<Degree, Integer> {

    @Override
    Optional<Degree> findById(Integer integer);

    @Override
    List<Degree> findAll();

    Optional<Degree> findByName(String name);
}
