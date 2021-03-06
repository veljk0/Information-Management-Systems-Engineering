package com.airline.backend.controllers;

import java.util.List;

import com.airline.backend.entities.Admin;
import com.airline.backend.migration.collections.MongoAdmin;
import com.airline.backend.migration.services.MongoAdminService;
import com.airline.backend.services.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admins")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MongoAdminService mongoAdminService;

    @PostMapping(value = "/addAdmin")
    private String addAdmin(@RequestBody Admin admin){
        if(mongoAdminService.getFlag()) {
            return mongoAdminService.addAdmin(new MongoAdmin(admin.getUsername(), admin.getPassword()));
        }
        return adminService.addAdmin(admin);
    }

    @GetMapping(value = "/deleteAll")
    private String deleteAll(){
        if(mongoAdminService.getFlag()) {
            return mongoAdminService.deleteAll();
        }
        return adminService.deleteAll();
    }

    @GetMapping(value = "/getAll")
    private List<?> getAll(){
        if(mongoAdminService.getFlag()) {
            System.out.println("mongo admins");
            return mongoAdminService.getAllAdmins();
        }
        System.out.println("mysql admins");
        return adminService.getAllAdmins();
    }

}
