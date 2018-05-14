package com.example.demo.rest;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Title;
import com.example.demo.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/title")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @RequestMapping(value = "/get-by-name/{name}", method = RequestMethod.GET)
    public @ResponseBody
    Title findByName(@PathVariable("name") String name) {
        return titleService.findByName(name);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Title> findAll() {
        return titleService.findAll();
    }
}
