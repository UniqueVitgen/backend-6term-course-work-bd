package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student findOne(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = optional.get();
        return student;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
