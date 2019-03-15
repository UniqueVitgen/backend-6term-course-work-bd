package com.example.demo.service;

import com.example.demo.entity.Group;
import com.example.demo.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {

    Student findOne(Integer id);

    List<Student> findAll();

    Set<Student> findAll(Set<Student> students);

    List<Student> findAllByGroup(Group group);

    List<Student> findAllByGroupId(Integer idGroup);

    List<Student> findAllByGroupIdIn(List<Integer> groupsIds);
}
