package com.airline.backend.controllers;

import java.util.List;
import com.airline.backend.entities.User;
import com.airline.backend.migration.collections.MongoUser;
import com.airline.backend.migration.services.MongoUserService;
import com.airline.backend.services.Token;
import com.airline.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoUserService mongoUserService;

    @GetMapping("/getUser")
    @ResponseBody
    public Object getUser(@RequestParam String email) {
        if(mongoUserService.getFlag()) {
            return mongoUserService.getUserPerEmail(email);
        }
        return userService.getUserPerEmail(email);
    }

    @GetMapping(value = "/getAll")
    public List<?> getUsers() {
        if(mongoUserService.getFlag()) {
            System.out.println("mongo users");
            return mongoUserService.getAllUsers();
        }
        return userService.getAllUsers(); }

    @PostMapping(value = "/addUser")
    public String addUser(@RequestBody User user) {
        if(mongoUserService.getFlag()) {
            return mongoUserService.addUser(new MongoUser(user.getFirstName(), user.getSecondName(), user.getEmail(), user.getPassword()));
        }
        return userService.addUser(user);
    }

    @GetMapping(value = "/deleteAll")
    public String deleteAllUsers() {
        if(mongoUserService.getFlag()) {
            return mongoUserService.deleteAllUsers();
        }
        return userService.deleteAllUsers();
    }

    @GetMapping("/check")
    @ResponseBody
    public boolean check(@RequestParam String email) {
        if(mongoUserService.getFlag()) {
            return mongoUserService.checkIfUserExists(email);
        }
        return userService.checkIfUserExists(email);
    }

    @GetMapping("/login")
    @ResponseBody
    public String loginUser(@RequestParam String username) {
        if(mongoUserService.getFlag()) {
            return mongoUserService.login(username);
        }
        return userService.login(username);
    }

    @GetMapping("/getLogi")
    public Token getUser(){
        if(mongoUserService.getFlag()) {
            return mongoUserService.getUser();
        }
        return userService.getUser();
    }

    @GetMapping("/logout")
    public String logout(){
        if(mongoUserService.getFlag()) {
            return mongoUserService.logout();
        }
        return userService.logout();
    }
}