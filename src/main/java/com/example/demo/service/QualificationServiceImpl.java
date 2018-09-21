package com.example.demo.service;

import com.example.demo.entity.Qualification;
import com.example.demo.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {
    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    public Qualification findById(Integer id) {
        return qualificationRepository.findById(id).get();
    }

    @Override
    public List<Qualification> findAll() {
        return qualificationRepository.findAll();
    }

    @Override
    public Qualification save(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    @Override
    public Qualification edit(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    @Override
    public void delete(Integer id) {
        qualificationRepository.deleteById(id);
    }

    @Override
    public void delete(Qualification qualification) {
        qualificationRepository.delete(qualification);
    }
}
