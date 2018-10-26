package com.example.demo.service;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.ImageModelHasNews;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<ImageModel> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public List<ImageModel> findAllByFilename(String filename) {
        return imageRepository.findAllByFilename(filename);
    }

    @Override
    public List<String> findDistrinctFilenames() {
        return imageRepository.findDistinctFilenames();
    }

    @Override
    public ImageModel save(ImageModel imageModel) {
        return imageRepository.save(imageModel);
    }

    @Override
    public ImageModelHasNews saveHasNews(ImageModel imageModel) {
        imageModel = imageRepository.save(imageModel);
        ImageModelHasNews imageModelHasNews = new ImageModelHasNews();
        imageModelHasNews.setContent(imageModel.getContent());
        imageModelHasNews.setContentType(imageModel.getContentType());
        imageModelHasNews.setCreated(imageModel.getCreated());
        imageModelHasNews.setFilename(imageModel.getFilename());
        imageModelHasNews.setDescription(imageModel.getDescription());
        imageModelHasNews.setId(imageModel.getId());
        return imageRepository.save(imageModelHasNews);

    }

    @Override
    public ImageModel findByFilename(String filename) {
        return  imageRepository.findByFilename(filename).get();
    }
}
