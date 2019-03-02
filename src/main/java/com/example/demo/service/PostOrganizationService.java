package com.example.demo.service;

import com.example.demo.entity.PostOrganization;

import java.util.List;

public interface PostOrganizationService {
    List<PostOrganization> getAll();

    PostOrganization findOrCreatePostOrganization(PostOrganization postOrganization);
}
