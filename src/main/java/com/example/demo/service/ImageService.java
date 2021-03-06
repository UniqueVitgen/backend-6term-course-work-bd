package com.example.demo.service;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.ImageModelHasNews;

import java.util.List;

public interface ImageService {

    List<ImageModel> getAll();

    List<ImageModel> findAllByFilename(String filename);

    List<String> findDistrinctFilenames();

    ImageModel save(ImageModel imageModel);

    ImageModelHasNews saveHasNews(ImageModel imageModel);

    ImageModel findByFilename(String filename);
}
