package com.example.demo.repository;

import com.example.demo.entity.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends CrudRepository<News, Integer> {

    @Override
    News save(News news);

    @Override
    List<News> findAll();

    @Override
    Optional<News> findById(Integer integer);
}
