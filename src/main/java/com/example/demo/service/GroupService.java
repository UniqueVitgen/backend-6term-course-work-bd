package com.example.demo.service;

import com.example.demo.entity.Group;
import com.example.demo.entity.Specialization;

import java.util.List;

public interface GroupService {
    List<Group> findAllBySpecialization(Integer id_Specialization);

    List<Group> findAll();

    Group save(Group group);

    Group Edit(Group group);

    Group get(Integer id);

    void delete(Integer id);
}
