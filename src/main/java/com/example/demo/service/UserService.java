package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Lector;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
//import org.springframework.security.core.userdetails.UserDetails;

public interface UserService<T,IRepository> {

    public List<User> findAll();

    public User findByUsername(String username);

    public User findOne(Integer id);

    public void save (T t);

    public Lector saveLector(Lector lector);

    public Student saveStudent(Student student);
}