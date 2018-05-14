package com.example.demo.rest;

import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
