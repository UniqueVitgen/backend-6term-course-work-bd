package com.example.demo.service;

import com.example.demo.entity.Lector;
import com.example.demo.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectorServiceImpl implements LectorService {

  @Autowired
  private LectorRepository lectorRepository;
  @Override
  public List<Lector> findAll() {
    return lectorRepository.findAll();
  }


}
