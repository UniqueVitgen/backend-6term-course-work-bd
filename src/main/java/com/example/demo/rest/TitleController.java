package com.example.demo.rest;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Title;
import com.example.demo.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/title")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @RequestMapping(value = "/get-by-name/{name}", method = GET)
    public @ResponseBody
    Title findByName(@PathVariable("name") String name) {
        return titleService.findByName(name);
    }

    @RequestMapping(value = "/getAll", method = GET)
    public @ResponseBody
    List<Title> findAll() {
        return titleService.findAll();
    }

    @RequestMapping(value = "/save", method = POST)
    public @ResponseBody
    Title save(@RequestBody Title title) {
        return titleService.save(title);
    }

    @RequestMapping(value = "/edit", method = PUT)
    public @ResponseBody
    Title edit(@RequestBody Title title) {
        return titleService.edit(title);
    }

    @RequestMapping(value = "/delete-{id}", method = DELETE)
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            titleService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
