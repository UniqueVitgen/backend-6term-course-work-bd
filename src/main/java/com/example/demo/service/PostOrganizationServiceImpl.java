package com.example.demo.service;

import com.example.demo.entity.PostOrganization;
import com.example.demo.repository.PostOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostOrganizationServiceImpl implements PostOrganizationService {
    @Autowired
    private PostOrganizationRepository postOrganizationRepository;

    @Override
    public List<PostOrganization> getAll() {
        return postOrganizationRepository.findAll();
    }
}
