package com.example.demo.rest;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = {"http://localhost:4200"})
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

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
}
