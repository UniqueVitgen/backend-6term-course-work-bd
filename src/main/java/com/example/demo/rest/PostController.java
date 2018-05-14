package com.example.demo.rest;

import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/get-by-name/{name}", method = RequestMethod.GET)
    public @ResponseBody
    Post findByName(@PathVariable("name") String name) {
        return postService.findByName(name);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Post> findAll() {
        return postService.findAll();
    }
}
