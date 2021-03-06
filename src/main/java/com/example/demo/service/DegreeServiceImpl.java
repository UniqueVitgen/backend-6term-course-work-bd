package com.example.demo.service;

import com.example.demo.entity.Degree;
import com.example.demo.repository.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DegreeServiceImpl implements DegreeService {
    @Autowired
    private DegreeRepository degreeRepository;

    @Override
    public Degree findByName(String name) {
        return degreeRepository.findByName(name).get();
    }

    @Override
    public List<Degree> findAll() {
        return degreeRepository.findAll();
    }

    @Override
    public Degree findById(Integer id) {
        return degreeRepository.findById(id).get();
    }

    @Override
    public Degree save(Degree degree) {
        return degreeRepository.save(degree);
    }

    @Override
    public Degree edit(Degree degree) {
        return degreeRepository.save(degree);
    }

    @Override
    public void delete(Degree degree) {
        degreeRepository.delete(degree);
    }

    @Override
    public void delete(Integer id) {
        degreeRepository.deleteById(id);
    }
}
