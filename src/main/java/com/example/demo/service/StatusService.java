package com.example.demo.service;

import com.example.demo.entity.Status;

import java.util.List;

public interface StatusService {
    Status findByName(String name);

    Status findById(Integer id);

    List<Status> getAll();

    Status save(Status status);

    Status edit(Status status);

    Status get(Integer id);

    void delete(Integer id);

    void delete(Status status);
}
