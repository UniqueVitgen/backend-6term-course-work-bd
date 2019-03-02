package com.example.demo.repository;

import com.example.demo.entity.Organization;
import com.example.demo.entity.PostOrganization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
    @Override
    List<Organization> findAll();

    Optional<Organization> findByName(String name);
}
