package com.example.demo.rest;

import com.example.demo.entity.PostOrganization;
import com.example.demo.repository.PostOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-organization")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PostOrganizationController {
    @Autowired
    private PostOrganizationRepository postOrganizationRepository;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<PostOrganization> lectorsList() {
        return postOrganizationRepository.findAll();
    }
}
