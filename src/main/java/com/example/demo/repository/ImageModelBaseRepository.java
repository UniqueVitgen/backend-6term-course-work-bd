package com.example.demo.repository;

import com.example.demo.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface ImageModelBaseRepository<T extends ImageModel> extends JpaRepository<ImageModel, Integer> {

    @Override
    Optional<ImageModel> findById(Integer integer);

    Optional<ImageModel> findByFilename(String filename);

    List<ImageModel> findAllByFilename(String filename);

    @Query(value = "select distinct im.filename from diplom_work.dbo.[Изображение] as im", nativeQuery = true)
    List<String> findDistinctFilenames();

    List<ImageModel> findAll();
}
