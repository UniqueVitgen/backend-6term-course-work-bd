package com.example.demo.rest;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.News;
import com.example.demo.entity.form.NewsForm;
import com.example.demo.service.ImageService;
import com.example.demo.service.NewsService;
import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    News save(@RequestBody NewsForm newsForm) {
        return newsService.save(newsForm);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<News> findAll() {
        return newsService.getAll();
    }
}
