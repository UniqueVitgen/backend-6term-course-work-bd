package com.example.demo.rest;

import com.example.demo.entity.Specialization;
import com.example.demo.service.SpecializationService;
import com.example.demo.service.files.PDFSpecializationService;
import com.example.demo.service.files.WordSpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/specialization")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private PDFSpecializationService pdfSpecializationService;

    @Autowired
    private WordSpecializationService wordSpecializationService;


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

    @RequestMapping(value = "/pdf-{idSpecialization}", method = RequestMethod.GET)
    public
    ResponseEntity<Resource>  savePDF(@PathVariable("idSpecialization" ) Integer idSpecialization) {
        Specialization diplomWork = specializationService.get(idSpecialization);
        File file = pdfSpecializationService.writePdf(diplomWork);

        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);

    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/word-{idSpecialization}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Resource>  getWord(@PathVariable("idSpecialization") Integer id) {
        Specialization specialization = specializationService.get(id);
        File file = wordSpecializationService.writeWord(specialization);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
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
