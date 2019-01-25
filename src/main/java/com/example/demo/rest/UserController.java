package com.example.demo.rest;

import com.example.demo.entity.Lector;
import com.example.demo.entity.LectorUniversity;
import com.example.demo.entity.User;
import com.example.demo.entity.form.UserUploadForm;
import com.example.demo.service.LectorService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    public LectorService lectorService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/getAll", method = GET, consumes = "application/json; charset=UTF-8")
    public @ResponseBody
    List<User> lectorsList() {
        List<User> users = userService.findAll();
        return userService.findAll();
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/user-username/{username}", method = GET)
    public @ResponseBody
    User getByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/user-{id}", method = GET)
    public @ResponseBody
    User getById(@PathVariable("id") Integer id) {
        return userService.findOne(id);
    }

    @RequestMapping(value = "/upload-photo", method = GET)
    public User uploadPhoto(@RequestParam("idUser") Integer idUser, @RequestParam("filename") String filename) {
        return userService.uploadPhoto(idUser, filename);
    }

    @RequestMapping(value = "/update-user-organizer-role-{isOrganizer}", method = PUT)
    public User updateUserOrganizer(@RequestBody User user, @PathVariable("isOrganizer") Boolean isOrganizer) {
        return userService.editOrganizerRole(user, isOrganizer);
    }
}
