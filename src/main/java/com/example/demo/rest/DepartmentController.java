package com.example.demo.rest;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.files.WordDepartmentService;
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

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = {"http://localhost:4200"})
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private WordDepartmentService wordDepartmentService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Department> facultiesList() {
        return departmentService.findAll();
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/findAll-by-department-{idDepartment}", method = RequestMethod.GET)
    public @ResponseBody
    List<Department> facultiesListByDepartment(@PathVariable("idDepartment") Integer id) {
        return departmentService.findAllByFacultyId(id);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/department-{id}", method = RequestMethod.GET)
    public @ResponseBody
    Department department(@PathVariable("id") Integer id) {
        return departmentService.get(id);
    }


    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Department save(@RequestBody Department department) {
        return departmentService.save(department);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    Department edit(@RequestBody Department department) {
        return departmentService.Edit(department);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        try {
            departmentService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @RequestMapping(value = "/pdf-{idSpecialization}", method = RequestMethod.GET)
//    public
//    ResponseEntity<Resource>  savePDF(@PathVariable("idSpecialization" ) Integer idSpecialization) {
//        Specialization diplomWork = specializationService.get(idSpecialization);
//        File file = pdfSpecializationService.writePdf(diplomWork);
//
//        InputStreamResource resource = null;
//        try {
//            resource = new InputStreamResource(new FileInputStream(file));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
//                .body(resource);
//
//    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/word-{idDeparment}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Resource>  getWord(@PathVariable("idDeparment") Integer id) {
        Department department = departmentService.get(id);
        File file = wordDepartmentService.writeWord(department);
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
}
