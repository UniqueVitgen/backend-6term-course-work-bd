package com.example.demo.repository;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Override
    Optional<Post> findById(Integer integer);

    @Override
    List<Post> findAll();

    Optional<Post> findByName(String name);
}
