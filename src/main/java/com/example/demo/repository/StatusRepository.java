package com.example.demo.repository;

import com.example.demo.entity.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends CrudRepository<Status, Integer> {

    @Override
    Optional<Status> findById(Integer integer);

    @Override
    List<Status> findAll();

    Optional<Status> findByName(String name);
}
