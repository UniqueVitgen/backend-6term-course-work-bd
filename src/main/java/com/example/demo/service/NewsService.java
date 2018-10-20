package com.example.demo.service;

import com.example.demo.entity.News;
import com.example.demo.entity.form.NewsForm;

import java.util.List;

public interface NewsService {

    List<News> getAll();

    News findById(Integer id);

    News save(News news);

    News save(NewsForm newsForm);

    void delete(News news);

    void delete(Integer id);
}
