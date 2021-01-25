package com.airline.backend.migration.services;


import com.airline.backend.migration.collections.MongoCompany;
import com.airline.backend.migration.repositories.MongoCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoCompanyService {

    @Autowired
    private MongoCompanyRepository companyRepository;

    private boolean flag = false;

    public MongoCompany getCompanyPerName(String name){
        List<MongoCompany> companies = companyRepository.findAll();
        MongoCompany result = companies.stream().filter(c -> c.getCompanyName().equals(name)).findFirst().orElse(null);
        return result;
    }

    public String createCompany(MongoCompany company){
        companyRepository.save(company);
        return "Company created!";
    }

    public String addCompany(MongoCompany company) {
        companyRepository.save(company);
        return "Company saved!";
    }

    public String deleteAll(){
        companyRepository.deleteAll();
        return "ALL companies deleted";
    }

    public String deleteCompany(String id){
        companyRepository.deleteById(id);
        return "Company " + id + " deleted!";
    }

    public String updateFlight(MongoCompany company) {
        companyRepository.save(company);
        return "flight added!";
    }

    public List<MongoCompany> getAllCompanies(){
        return companyRepository.findAll();
    }

    public String deletePerName(String name) {
        List<MongoCompany> companies = companyRepository.findAll();
        MongoCompany deleteMe = companies.stream().filter(c -> c.getCompanyName().equals(name)).findFirst().orElse(null);
        if(deleteMe == null) return "no company found";
        else return deleteCompany(deleteMe.getCompanyId());
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }
}
