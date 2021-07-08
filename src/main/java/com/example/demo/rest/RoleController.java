package com.example.demo.rest;

import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Role> lectorsList() {
        List<Role> roles = roleService.findAll();
        return roles;
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/save", method = POST,  produces = "application/json")
    public @ResponseBody
    Role save(@RequestBody Role role) {
        return roleService.save(role);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/edit", method = PUT)
    public @ResponseBody
    Role edit(@RequestBody Role role) {
        return roleService.Edit(role);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/delete-{id}", method = DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        try {
            roleService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
