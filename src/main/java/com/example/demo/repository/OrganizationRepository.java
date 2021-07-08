package com.example.demo.repository;

import com.example.demo.entity.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
    @Override
    List<Organization> findAll();
}
