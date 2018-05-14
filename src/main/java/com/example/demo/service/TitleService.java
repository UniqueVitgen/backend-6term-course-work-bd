package com.example.demo.service;

import com.example.demo.entity.Title;

import java.util.List;

public interface TitleService {

    Title findByName(String name);

    List<Title> findAll();

    Title findById(Integer id);
}
