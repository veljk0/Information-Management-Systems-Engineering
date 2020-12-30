package com.airline.backend.services;

import java.util.List;

import com.airline.backend.entities.Admin;
import com.airline.backend.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
 
    public String addAdmin(Admin admin){
        adminRepository.save(admin);
        return "Admin added!";
    }

    public String deleteAll(){
        adminRepository.deleteAll();
        return "ALL Admins deleted!";
    }

    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }
}
