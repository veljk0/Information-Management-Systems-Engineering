package com.airline.backend.controllers;

import java.util.List;
import com.airline.backend.entities.Company;
import com.airline.backend.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/companies")
@CrossOrigin
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;
    
    @PostMapping(value = "/addCompany")
    public String createCompany(@RequestBody Company company) { return companyService.createCompany(company); }

    @GetMapping(value = "/getAll")
    public List<Company> getAllCompanies(){ return companyService.getAllCompanies(); }

    @GetMapping("/deleteCompany")
    @ResponseBody
    public String deleteCompanyPerName(@RequestParam String name) {
        return companyService.deletePerName(name);
    }
    
    @GetMapping(value = "/deleteAll")
    public String deleteAll(){ return companyService.deleteAll(); }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUserViaID(@PathVariable("id") int id) { return companyService.deleteCompany(id); }

    @PostMapping(value = "/updateFlight")
    public String updateCompany(@RequestBody Company company) { return companyService.updateFlight(company); }

    @GetMapping("/getCompany")
    @ResponseBody
    public Company getCompany(@RequestParam String name) {
        return companyService.getCompanyPerName(name);
    }

}