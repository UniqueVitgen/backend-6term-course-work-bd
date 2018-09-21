package com.example.demo.repository;

import com.example.demo.entity.Qualification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface QualificationRepository extends CrudRepository<Qualification, Integer> {

    @Override
    Optional<Qualification> findById(Integer integer);

    @Override
    List<Qualification> findAll();

    @Override
    void delete(Qualification qualification);

    @Override
    void deleteById(Integer integer);
}
