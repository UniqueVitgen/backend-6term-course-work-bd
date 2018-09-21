package com.example.demo.service;

import com.example.demo.entity.Title;

import java.util.List;

public interface TitleService {

    Title findByName(String name);

    List<Title> findAll();

    Title findById(Integer id);

    Title save(Title title);

    Title edit(Title title);

    void delete(Title title);

    void delete(Integer id);
}
