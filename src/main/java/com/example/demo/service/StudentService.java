package com.example.demo.service;

import com.example.demo.entity.Student;

import java.util.List;

public interface StudentService {

    Student findOne(Integer id);

    List<Student> findAll();
}
