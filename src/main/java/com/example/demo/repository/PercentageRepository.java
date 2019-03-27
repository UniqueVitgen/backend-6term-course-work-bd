package com.example.demo.repository;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.News;
import com.example.demo.entity.Percentage;
import com.example.demo.entity.sec.SEC;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PercentageRepository extends CrudRepository<Percentage, Integer> {

    @Override
    List<Percentage> findAll();

    List<Percentage> findAllBySec(SEC sec);

    List<Percentage> findAllBySecId(Integer idSec);

    void delete(Percentage percentage);

    void deleteById(Integer id);


}
