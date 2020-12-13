package com.airline.backend.controllers;

import java.util.List;
import com.airline.backend.entities.User;
import com.airline.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAll")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/addUser")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUserViaID(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }

    @GetMapping(value = "/deleteAll")
    public String deleteAllUsers() {
        return userService.deleteAllUsers();
    }

    @GetMapping("/check")
    @ResponseBody
    public boolean check(@RequestParam String email) {
        return userService.checkIfUserExists(email);
    }

}
