package com.example.demo.service;

import com.example.demo.entity.Specialization;

import java.util.List;

public interface SpecializationService {

    List<Specialization> findAllByDepartment(Integer id_Department);

    List<Specialization> findAll();

    Specialization save(Specialization specialization);

    Specialization Edit(Specialization specialization);

    Specialization get(Integer id);

    void delete(Integer id);
}
