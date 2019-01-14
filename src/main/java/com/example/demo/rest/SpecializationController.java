package com.example.demo.rest;

import com.example.demo.entity.Specialization;
import com.example.demo.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/specialization")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;


    @RequestMapping(value = "/get-by-department/{id-department}", method = GET, consumes = "application/json; charset=UTF-8")
    public @ResponseBody
    List<Specialization> getSpecializationByFaculty(@PathVariable("id-department") Integer id) {
        List<Specialization> users = specializationService.findAllByDepartment(id);
        return users;
    }



    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/getAll", method = GET)
    public @ResponseBody
    List<Specialization> specializationsList() {
        List<Specialization> specializations = specializationService.findAll();
        return specializations;
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/specialization-{id}", method = GET)
    public @ResponseBody
    Specialization specialization(@PathVariable("id") Integer id) {
        return specializationService.get(id);
    }


    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/save", method = POST,  produces = "application/json")
    public @ResponseBody
    Specialization save(@RequestBody Specialization specialization) {
        return specializationService.save(specialization);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/edit", method = PUT)
    public @ResponseBody
    Specialization edit(@RequestBody Specialization specialization) {
        return specializationService.Edit(specialization);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/delete-{id}", method = DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        try {
            specializationService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
