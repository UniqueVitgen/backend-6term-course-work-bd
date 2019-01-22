package com.example.demo.rest;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.News;
import com.example.demo.entity.form.NewsForm;
import com.example.demo.service.ImageService;
import com.example.demo.service.NewsService;
import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
//    @Autowired
    private ImageService imageService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/news-{id}", method = RequestMethod.GET)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody
    News diplomWork(@PathVariable("id") Integer id) {
        return newsService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    News save(@RequestBody NewsForm newsForm) {
        return newsService.save(newsForm);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody
    News edit(@RequestBody News newsForm) {
        return newsService.save(newsForm);
    }

    @RequestMapping(value = "/edit-by-form", method = RequestMethod.POST)
    public @ResponseBody
    News editByForm(@RequestBody NewsForm newsForm) {
        return newsService.save(newsForm);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<News> findAll() {
        return newsService.getAll();
    }

    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            newsService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
