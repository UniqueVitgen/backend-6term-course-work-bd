package com.example.demo.repository;

import com.example.demo.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends ImageModelBaseRepository<ImageModel> {
}
