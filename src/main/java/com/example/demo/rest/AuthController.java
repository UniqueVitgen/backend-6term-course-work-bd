package com.example.demo.rest;

import com.example.demo.entity.AuthToken;
import com.example.demo.entity.User;
import com.example.demo.config.JwtTokenUtil;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/token")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User loginUser) throws AuthenticationException {

        final User user = userService.findByUsername(loginUser.getUsername());
        if(user == null) {
//            return ResponseEntity.ok(ResponseEntity.);
            throw new UsernameNotFoundException("username is not exist");
        }
        AuthToken authToken = new AuthToken();
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(user);
        authToken.setToken(token);
        authToken.setUser(user);
        return ResponseEntity.ok(authToken);
    }
}
