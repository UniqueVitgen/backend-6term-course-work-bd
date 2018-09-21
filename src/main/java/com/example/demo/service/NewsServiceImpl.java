package com.example.demo.service;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.ImageModelHasNews;
import com.example.demo.entity.News;
import com.example.demo.entity.form.NewsForm;
import com.example.demo.repository.ImageHasNewsRepository;
import com.example.demo.repository.ImageModelBaseRepository;
import com.example.demo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageHasNewsRepository imageHasNewsRepository;

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }


    @Override
    public News findById(Integer id) {
        return newsRepository.findById(id).get();
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News save(NewsForm newsForm) {
        News news = new News();
        if(newsForm.getFilename() != null && !newsForm.getFilename().equals("")) {
            ImageModel imageModel = imageService.findAllByFilename(newsForm.getFilename()).get(0);
            ImageModelHasNews imageModelHasNews = imageService.saveHasNews(imageModel);
            news.setImageModel(imageModelHasNews);
        }
        //imageModelHasNews = imageHasNewsRepository.save(imageModelHasNews);
        news.setTitle(newsForm.getTitle());
        news.setContent(newsForm.getContent());
        news.setCreated(new Date());
        return newsRepository.save(news);
    }
}
