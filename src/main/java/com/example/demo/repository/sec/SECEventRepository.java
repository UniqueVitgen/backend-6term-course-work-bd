package com.example.demo.repository.sec;

import com.example.demo.entity.sec.SECEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SECEventRepository extends CrudRepository<SECEvent, Integer> {

    @Override
    Optional<SECEvent> findById(Integer integer);

    @Override
    List<SECEvent> findAll();
}
