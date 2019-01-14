package com.example.demo.repository;

import com.example.demo.entity.PostOrganization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostOrganizationRepository extends CrudRepository<PostOrganization, Integer> {
    @Override
    List<PostOrganization> findAll();
}
