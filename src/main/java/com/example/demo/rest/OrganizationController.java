package com.example.demo.rest;

import com.example.demo.entity.Organization;
import com.example.demo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
@CrossOrigin(origins = {"http://localhost:4200"})
public class OrganizationController {
    @Autowired
    private OrganizationRepository organizationRepository;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Organization> lectorsList() {
        return organizationRepository.findAll();
    }
}
