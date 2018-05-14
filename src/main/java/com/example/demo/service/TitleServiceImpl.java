package com.example.demo.service;

import com.example.demo.entity.Title;
import com.example.demo.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleServiceImpl implements TitleService {
    @Autowired
    private TitleRepository titleRepository;

    @Override
    public Title findByName(String name) {
        return titleRepository.findByName(name).get();
    }

    @Override
    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    @Override
    public Title findById(Integer id) {
        return titleRepository.findById(id).get();
    }
}
