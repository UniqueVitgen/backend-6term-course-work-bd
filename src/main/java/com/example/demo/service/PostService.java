package com.example.demo.service;

import com.example.demo.entity.Post;

import java.util.List;

public interface PostService {

    Post findByName(String name);

    List<Post> findAll();

    Post findById(Integer id);

    Post save(Post post);

    Post edit(Post post);

    void delete(Post post);

    void delete(Integer id);
}
