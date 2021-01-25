package com.airline.backend.controllers;

import java.util.LinkedList;
import java.util.List;

import com.airline.backend.entities.Company;
import com.airline.backend.migration.collections.MongoCompany;
import com.airline.backend.migration.collections.MongoFlight;
import com.airline.backend.migration.collections.MongoTicket;
import com.airline.backend.migration.services.MongoCompanyService;
import com.airline.backend.migration.services.MongoFlightService;
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

    @Autowired
    private MongoFlightService mongoFlightService;

    @Autowired
    private MongoCompanyService mongoCompanyService;

    @PostMapping(value = "/addCompany")
    public String createCompany(@RequestBody Company company) {
        if (mongoCompanyService.getFlag()) {
            MongoCompany mongoCompany = new MongoCompany(company.getCompanyName());
            return mongoCompanyService.createCompany(mongoCompany);
        }
        return companyService.createCompany(company);
    }

    @GetMapping(value = "/getAll")
    public List<?> getAllCompanies() {
        if (mongoCompanyService.getFlag()) {
            return mongoCompanyService.getAllCompanies();
        }
        return companyService.getAllCompanies();
    }

    @GetMapping("/deleteCompany")
    @ResponseBody
    public String deleteCompanyPerName(@RequestParam String name) {
        if (mongoCompanyService.getFlag()) {
            return mongoCompanyService.deletePerName(name);
        }
        return companyService.deletePerName(name);
    }

    @GetMapping(value = "/deleteAll")
    public String deleteAll() {
        if (mongoCompanyService.getFlag()) {
            return mongoCompanyService.deleteAll();
        }
        return companyService.deleteAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUserViaID(@PathVariable("id") int id) {
        if (mongoCompanyService.getFlag()) {
            return mongoCompanyService.deleteCompany(Integer.toString(id));
        }
        return companyService.deleteCompany(id);
    }

    @PostMapping(value = "/updateFlight")
    public String updateCompany(@RequestBody Company company) {
        if (mongoCompanyService.getFlag()) {
            MongoCompany mongoCompany = mongoCompanyService.getCompanyPerName(company.getCompanyName());
            return mongoCompanyService.updateFlight(mongoCompany);
        }
        return companyService.updateFlight(company);
    }

    @GetMapping("/getCompany")
    @ResponseBody
    public Object getCompany(@RequestParam String name) {
        if(mongoCompanyService.getFlag()) {
            return mongoCompanyService.getCompanyPerName(name);
        }
        return companyService.getCompanyPerName(name);
    }

    @GetMapping("/getFlights/{companyName}")
    @ResponseBody
    public List<?> flights(@PathVariable("companyName") String companyName){
        if(mongoCompanyService.getFlag()){
            List<MongoFlight> flights = new LinkedList<>();
            for(MongoFlight mongoFlight: mongoFlightService.getAll()) {
                if(mongoFlight.getCompany().getCompanyName().equals(companyName)){
                    flights.add(mongoFlight);
                }
            }
            return flights;
        }
        return companyService.getCompanyPerName(companyName).getFlights();
    }

}