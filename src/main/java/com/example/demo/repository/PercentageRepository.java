package com.example.demo.repository;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.News;
import com.example.demo.entity.Percentage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PercentageRepository extends CrudRepository<Percentage, Integer> {

    @Override
    List<Percentage> findAll();

    List<Percentage> findAllByDiplomWork(DiplomWork diplomWork);

    List<Percentage> findAllByDiplomWorkId(Integer id);

    void delete(Percentage percentage);

    void deleteById(Integer id);


}
