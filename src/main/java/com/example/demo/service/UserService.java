package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.*;
//import org.springframework.security.core.userdetails.UserDetails;

public interface UserService<T,IRepository> {

    public List<User> findAll();

    public User findByUsername(String username);

    public User findOne(Integer id);

    public void save (T t);

    public Lector saveLectorUniversity(LectorUniversity lector);

    public Lector saveLectorOrganizer(LectorOrganization lectorOrganization);

    public Student saveStudent(Student student);

    public User saveOrganizer(User organizer);

    public User saveSecretary(User secretary);

    public String formatFullName(User user);

    public String formatSurnameWithInitials(User user);
}