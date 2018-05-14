package com.example.demo.service;

import com.example.demo.entity.Faculty;
import com.example.demo.entity.Specialization;
import com.example.demo.repository.FacultyRepository;
import com.example.demo.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<Specialization> findAllByFaculty(Integer id_Faculty) {
        return specializationRepository.findSpecializationByFacultyId(id_Faculty);
    }

    @Override
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization save(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    @Override
    public Specialization Edit(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    @Override
    public Specialization get(Integer id) {
        return specializationRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        try {
            Specialization specialization = this.specializationRepository.findById(id).get();
            specializationRepository.delete(specialization);
        }
        catch (Exception ex) {

        }
    }
}
