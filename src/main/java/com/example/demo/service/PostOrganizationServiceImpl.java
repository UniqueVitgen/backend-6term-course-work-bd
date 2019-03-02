package com.example.demo.service;

import com.example.demo.entity.PostOrganization;
import com.example.demo.repository.PostOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostOrganizationServiceImpl implements PostOrganizationService {
    @Autowired
    private PostOrganizationRepository postOrganizationRepository;

    @Override
    public List<PostOrganization> getAll() {
        return postOrganizationRepository.findAll();
    }

    @Override
    public PostOrganization findOrCreatePostOrganization(PostOrganization postOrganization) {
        Optional<PostOrganization> findedPostOrganization = postOrganizationRepository.findByName(postOrganization.getName());
        return findedPostOrganization.orElseGet(() -> postOrganizationRepository.save(postOrganization));
    }
}
