package com.example.demo.rest;

import com.example.demo.entity.Qualification;
import com.example.demo.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/qualification")
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @RequestMapping(value = "/qualification-{id}", method = RequestMethod.GET)
    public @ResponseBody
    Qualification findById(@PathVariable("id") Integer id) {
        return qualificationService.findById(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Qualification> findAll() {
        return qualificationService.findAll();
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Qualification save(@RequestBody Qualification qualification) {
        return qualificationService.save(qualification);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    Qualification edit(@RequestBody Qualification qualification) {
        return qualificationService.edit(qualification);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        try {
            qualificationService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
