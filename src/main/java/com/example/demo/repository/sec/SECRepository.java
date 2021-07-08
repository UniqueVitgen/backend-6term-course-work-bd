package com.example.demo.repository.sec;

import com.example.demo.entity.sec.SEC;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SECRepository extends CrudRepository<SEC, Integer> {

    @Override
    Optional<SEC> findById(Integer integer);

    @Override
    List<SEC> findAll();
}
