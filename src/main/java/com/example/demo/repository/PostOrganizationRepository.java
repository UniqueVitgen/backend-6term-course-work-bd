package com.example.demo.repository;

import com.example.demo.entity.PostOrganization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostOrganizationRepository extends CrudRepository<PostOrganization, Integer> {
    @Override
    List<PostOrganization> findAll();

    Optional<PostOrganization> findByName(String name);
}
