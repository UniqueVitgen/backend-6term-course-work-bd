package com.example.demo.service;

import com.example.demo.entity.Organization;
import com.example.demo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Override
    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }
}
