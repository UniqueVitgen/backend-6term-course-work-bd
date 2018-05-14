package com.example.demo.rest;

import com.example.demo.entity.User;
import com.example.demo.service.LectorService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    public LectorService lectorService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, consumes = "application/json; charset=UTF-8")
    public @ResponseBody
    List<User> lectorsList() {
        List<User> users = userService.findAll();
        return userService.findAll();
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/user-username/{username}", method = RequestMethod.GET)
    public @ResponseBody
    User getByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }




//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    void login() {
//        String jwtToken = "";
//
//        if (login.getEmail() == null || login.getPassword() == null) {
////            throw new ServletException("Please fill in username and password");
//        }
//
//        String email = login.getEmail();
//        String password = login.getPassword();
//
//        User user = userService.findByEmail(email);
//
//        if (user == null) {
////            throw new ServletException("User email not found.");
//        }
//
//        String pwd = user.getPassword();
//
//        if (!password.equals(pwd)) {
//            throw new ServletException("Invalid login. Please check your name and password.");
//        }
//
//        jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
//                .signWith(SignatureAlgorithm.HS, "secretkey").compact();
//
//        return jwtToken;
//    }
}
