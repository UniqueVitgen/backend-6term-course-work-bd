package com.example.demo.service;

import com.example.demo.entity.Post;

import java.util.List;

public interface PostService {

    Post findByName(String name);

    List<Post> findAll();

    Post findById(Integer id);
}
