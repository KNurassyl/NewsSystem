package com.example.newssystem.controller;

import com.example.newssystem.dto.AuthRequest;
import com.example.newssystem.entity.User;
import com.example.newssystem.service.impl.JwtServiceImpl;
import com.example.newssystem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserServiceImpl service;
    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody User user) {
        List<User> allUsers = service.getAllUser();
        boolean isDuplicate = allUsers.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));
        if (isDuplicate) {
            return "Please create another unique username";
        }
        service.addUser(user);
        return "Successfully added";
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtServiceImpl.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}
