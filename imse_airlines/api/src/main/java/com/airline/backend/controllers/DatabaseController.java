package com.airline.backend.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.airline.backend.entities.Admin;
import com.airline.backend.entities.Company;
import com.airline.backend.entities.Flight;
import com.airline.backend.entities.User;
import com.airline.backend.services.AdminService;
import com.airline.backend.services.CompanyService;
import com.airline.backend.services.FlightService;
import com.airline.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/database")
public class DatabaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/fill")
    public void fill() throws ParseException {
   
        Date date5 = new Date();
        Date date6 = new Date();
        Date date7 = new Date();
        Date date8 = new Date();
    
        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
        
        date5 = dateformat3.parse("03/07/2020");
        date6 = dateformat3.parse("03/08/2021");
        date7 = dateformat3.parse("13/12/2020");
        date8 = dateformat3.parse("14/12/2020");

        /**************USER**************************/ 
        User user1 = new User("Veljko", "Radunovic", "velja213@gmail.com", "123456");
        userService.addUser(user1);
        User user2 = new User("Jelena", "Mikic", "jelena@gmail.com", "123456");
        userService.addUser(user2);
        User user3 = new User("Test", "User", "test@user.com", "test123");
        userService.addUser(user3);
        /**************ADMINS**************************/ 
        Admin admin1 = new Admin(1, "admin", "admin");
        adminService.addAdmin(admin1);
        /**************COMPANIES**************************/ 
        Company company1 = new Company(1, "Emirates", null);
        Company company2 = new Company(2, "Etihad", null);
        Company company3 = new Company(3, "Lufthansa", null);
        Company company4 = new Company(4, "Turkish Airlines", null);
        companyService.createCompany(company1);
        companyService.createCompany(company2);
        companyService.createCompany(company3);
        companyService.createCompany(company4);
        /**************FLIGHTS**************************/
      
        Flight flight5 = new Flight(5, "Milano", date5, "Geneve", date5, company1);
        Flight flight6 = new Flight(6, "Budapest", date6, "Prague", date6, company2);
        Flight flight7 = new Flight(7, "Frankfurt", date7, "Rostock", date7, company3);
        Flight flight8 = new Flight(8, "Amsterdam", date7, "Manchester", date8, company4);
        
        
        flightService.createFlight(flight5);
        flightService.createFlight(flight6);
        flightService.createFlight(flight7);
        flightService.createFlight(flight8);
    }       
}
