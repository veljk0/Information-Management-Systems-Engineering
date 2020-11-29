package com.airline.backend.controllers;

import java.util.List;

import com.airline.backend.entities.User;
import com.airline.backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/get")
    public List<User> getAllUsers() {

        return userService.getAllUsers();

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
        System.out.println("user inserted");
    }

}
