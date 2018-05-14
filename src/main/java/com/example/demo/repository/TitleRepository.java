package com.example.demo.repository;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Title;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TitleRepository extends CrudRepository<Title, Integer> {

    @Override
    Optional<Title> findById(Integer integer);

    @Override
    List<Title> findAll();

    Optional<Title> findByName(String name);
}
