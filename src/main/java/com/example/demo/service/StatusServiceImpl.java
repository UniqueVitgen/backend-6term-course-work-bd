package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Override
    public Status findByName(String name) {
        return statusRepository.findByName(name).get();
    }

    @Override
    public Status findById(Integer id) {
        return statusRepository.findById(id).get();
    }

    @Override
    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status edit(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status get(Integer id) {
        return statusRepository.findById(id).get();
    }

    @Override
    public  void delete(Integer id) {
        statusRepository.deleteById(id);
    }

    @Override
    public void delete(Status status) {
        statusRepository.delete(status);
    }
}
