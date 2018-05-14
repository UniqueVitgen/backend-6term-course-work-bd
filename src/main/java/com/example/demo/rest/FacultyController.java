package com.example.demo.rest;

import com.example.demo.entity.Faculty;
import com.example.demo.entity.Lector;
import com.example.demo.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/faculty")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Faculty> facultiesList() {
        return facultyService.findAll();
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/faculty-{id}", method = RequestMethod.GET)
    public @ResponseBody
    Faculty faculty(@PathVariable("id") Integer id) {
        return facultyService.get(id);
    }


    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Faculty save(@RequestBody Faculty faculty) {
        return facultyService.save(faculty);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    Faculty edit(@RequestBody Faculty faculty) {
        return facultyService.Edit(faculty);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        try {
            facultyService.delete(id);
            return ResponseEntity.noContent().build();
        }
     catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }
}
