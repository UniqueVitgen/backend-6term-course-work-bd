package com.example.demo.service;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public University findByName(String name) {
        return universityRepository.findByName(name);
    }

    @Override
    public List<University> findAll() {
        return universityRepository.findAll();
    }

    @Override
    public University save(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University Edit(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University get(Integer id) {
        return universityRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        University university = universityRepository.findById(id).get();
        universityRepository.delete(university);
    }
}
