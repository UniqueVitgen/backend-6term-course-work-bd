package com.example.demo.service;

import com.example.demo.entity.Lector;

import java.util.List;

public interface LectorService {

  public List<Lector> findAll();

  public List<Lector> findAllBySecUserIdIsNotInOrSecUserNull(List<Integer> ids);

  public Lector save(Lector lector);

  public Lector edit(Lector lector);

  public Lector findByUsername(String username);
}
