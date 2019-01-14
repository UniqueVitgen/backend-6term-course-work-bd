package com.example.demo.rest;

import com.example.demo.entity.*;
import com.example.demo.service.LectorService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/sign-up")
public class RegisterController {
    @Autowired
    public UserService userService;

    @Autowired
    public LectorService lectorService;

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    Student SignUpStudent(@RequestBody Student student) {
        System.out.println("entered in stuedent sign up");
        User user = userService.findByUsername(student.getUsername());
        if(user != null) {
            return null;
        }
        return userService.saveStudent(student);
    }

    @RequestMapping(value = "/organizer", method = RequestMethod.POST)
    User SignUpOrganizer(@RequestBody User organizer) {
        System.out.println("entered in stuedent sign up");
        User user = userService.findByUsername(organizer.getUsername());
        if(user != null) {
            return null;
        }
        return userService.saveOrganizer(organizer);
    }

    @RequestMapping(value = "/secretary-sec", method = RequestMethod.POST)
    User SignUpSecretary(@RequestBody User secretary) {
        System.out.println("entered in stuedent sign up");
        User user = userService.findByUsername(secretary.getUsername());
        if(user != null) {
            return null;
        }
        return userService.saveSecretary(secretary);
    }

    public <T> List<T> jsonArrayToObjectList(String json, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
        List<T> ts = mapper.readValue(json, listType);
//        LOGGER.debug("class name: {}", ts.get(0).getClass().getName());
        return ts;
    }

    @RequestMapping(value = "/lector", method = RequestMethod.POST, produces="application/json")
    Lector SignUpLector(@RequestBody LectorUniversity lector) {
        System.out.println(lector);
        System.out.println("entered in stuedent 1 sign up");
        User user = userService.findByUsername(lector.getUsername());
        if(user != null) {
            return null;
        }
        return userService.saveLectorUniversity(lector);
    }

    @PostMapping(value ="/lector-organizer")
    Lector SignUpLectorOrganizer(@RequestBody LectorOrganization lectorOrganization) {;
        User user = userService.findByUsername(lectorOrganization.getUsername());
        if(user != null) {
            return null;
        }
        return userService.saveLectorOrganizer(lectorOrganization);

    }
}
