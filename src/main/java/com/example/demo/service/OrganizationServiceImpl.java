package com.example.demo.service;

import com.example.demo.entity.Organization;
import com.example.demo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Override
    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization findOrCreate(Organization organization) {
        Optional<Organization> findedOrganization = organizationRepository.findByName(organization.getName());
        return findedOrganization.orElseGet(() -> organizationRepository.save(organization));
    }
}
