package com.example.demo.service;

import com.example.demo.entity.Faculty;
import com.example.demo.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Faculty findByName(String name) {
        return facultyRepository.findByName(name);
    }

    @Override
    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty Edit(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Integer id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        Faculty faculty = facultyRepository.findById(id).get();
        facultyRepository.delete(faculty);
    }
}
