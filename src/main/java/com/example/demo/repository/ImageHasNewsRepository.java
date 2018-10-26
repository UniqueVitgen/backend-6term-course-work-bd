package com.example.demo.repository;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.ImageModelHasNews;

import java.util.List;

public interface ImageHasNewsRepository extends ImageModelBaseRepository<ImageModelHasNews>{
    @Override
    List<ImageModel> findAllById(Iterable<Integer> iterable);
}
