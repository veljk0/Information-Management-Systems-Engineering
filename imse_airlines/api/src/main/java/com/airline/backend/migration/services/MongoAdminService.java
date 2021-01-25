package com.airline.backend.migration.services;

import com.airline.backend.entities.Admin;
import com.airline.backend.migration.collections.MongoAdmin;
import com.airline.backend.migration.repositories.MongoAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoAdminService {

    private boolean flag = false;

    @Autowired
    private MongoAdminRepository adminRepository;


    public String addAdmin(MongoAdmin admin){
        adminRepository.save(admin);
        return "Admin added!";
    }

    public String deleteAll(){
        adminRepository.deleteAll();
        return "ALL Admins deleted!";
    }

    public List<MongoAdmin> getAllAdmins(){
        return adminRepository.findAll();
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return this.flag;
    }
}
