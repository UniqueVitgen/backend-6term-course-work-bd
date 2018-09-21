package com.example.demo.rest;

import com.example.demo.entity.Degree;
import com.example.demo.entity.Title;
import com.example.demo.service.DegreeService;
import com.example.demo.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/degree")
public class DegreeController {

    @Autowired
    private DegreeService degreeService;

    @RequestMapping(value = "/get-by-name/{name}", method = RequestMethod.GET)
    public @ResponseBody
    Degree findByName(@PathVariable("name") String name) {
        return degreeService.findByName(name);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Degree> findAll() {
        return degreeService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Degree save(@RequestBody Degree degree) {
        return degreeService.save(degree);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    Degree edit(@RequestBody Degree degree) {
        return degreeService.edit(degree);
    }

    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            degreeService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
