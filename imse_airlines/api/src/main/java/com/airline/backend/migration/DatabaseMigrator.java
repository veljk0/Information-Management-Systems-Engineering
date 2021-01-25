package com.airline.backend.migration;

import com.airline.backend.entities.*;
import com.airline.backend.migration.collections.*;
import com.airline.backend.migration.services.*;
import com.airline.backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.LinkedList;
import java.util.List;

@Component
public class DatabaseMigrator {

    @Autowired
    private MongoUserService mongoUserService;

    @Autowired
    private MongoAdminService mongoAdminService;

    @Autowired
    private MongoCompanyService mongoCompanyService;

    @Autowired
    private MongoTicketService mongoTicketService;

    @Autowired
    private MongoFlightService mongoFlightService;

    public DatabaseMigrator() {
    }


    public void migrateUsers(UserService userService) {
        for (User user : userService.getAllUsers()) {
            MongoUser mongoUser = new MongoUser(Integer.toString(user.getId()), user.getFirstName(), user.getSecondName(), user.getEmail(), user.getPassword());
            List<MongoTicket> tickets = new LinkedList<>();

            for (Ticket ticket : user.getTickets()) {
                Flight flight = ticket.getFlight();
                MongoFlight mongoFlight = new MongoFlight(Integer.toString(flight.getFlightID()),
                        flight.getDepartureCity(),
                        flight.getDepartureDate(),
                        flight.getArrivalCity(),
                        flight.getArrivalDate()
                );
                MongoTicket mongoTicket = new MongoTicket(Integer.toString(ticket.getTicketID()), ticket.getPrice(), ticket.getSold(), mongoFlight);
                tickets.add(mongoTicket);
            }
            mongoUser.setTickets(tickets);
            mongoUserService.addUser(mongoUser);
        }
        mongoUserService.setFlag(true);
    }

    public void migrateAdmin(AdminService adminService) {
        for (Admin admin : adminService.getAllAdmins()) {
            MongoAdmin mongoAdmin = new MongoAdmin(Integer.toString(admin.getAdminID()), admin.getUsername(), admin.getPassword());
            System.out.println("AAAAAAA:" + mongoAdmin);
            mongoAdminService.addAdmin(mongoAdmin);
        }
        mongoAdminService.setFlag(true);
    }

    public void migrateCompanies(CompanyService companyService) {
        for (Company company : companyService.getAllCompanies()) {
            List<MongoFlight> mongoFlights = new LinkedList<>();
            for (Flight flight : company.getFlights()) {
                mongoFlights.add(new MongoFlight(Integer.toString(flight.getFlightID()),
                                flight.getDepartureCity(),
                                flight.getDepartureDate(),
                                flight.getArrivalCity(),
                                flight.getArrivalDate()
                        )
                );
            }
            MongoCompany mongoCompany = new MongoCompany(Integer.toString(company.getCompanyId()), company.getCompanyName(), mongoFlights);
            mongoCompanyService.addCompany(mongoCompany);
        }
        mongoCompanyService.setFlag(true);
    }

    public void migrateTickets(TicketService ticketService) {
        for(Ticket ticket : ticketService.getAllTickets()) {
            Flight ticketFlight = ticket.getFlight();
            Company company = ticketFlight.getCompany();
            MongoCompany mongoCompany = new MongoCompany(Integer.toString(company.getCompanyId()), company.getCompanyName());
            MongoFlight mongoFlight = new MongoFlight(Integer.toString(ticketFlight.getFlightID()),  ticketFlight.getDepartureCity(),
                    ticketFlight.getDepartureDate(),
                    ticketFlight.getArrivalCity(),
                    ticketFlight.getArrivalDate(),
                    mongoCompany);
            MongoTicket mongoTicket = new MongoTicket(Integer.toString(ticket.getTicketID()), ticket.getPrice(), ticket.getSold(), mongoFlight);
            if(ticket.getSold()){
                mongoTicket.setUser(mongoUserService.getUserPerEmail(ticket.getUser().getEmail()));
            }

            /*
            mongoTicket.setUser(new MongoUser(Integer.toString(ticket.getUser().getId()),
                                                ticket.getUser().getFirstName(),
                                                ticket.getUser().getSecondName(),
                                                ticket.ge
                    ));*/
            mongoTicketService.addTicket(mongoTicket);
        }
        mongoTicketService.setFlag(true);
    }


    public void migrateFlights(FlightService flightService) {
        for(Flight flight : flightService.getAll()) {
            List<MongoTicket> tickets = new LinkedList<>();
            for(Ticket ticket : flight.getTickets()) {
                tickets.add(new MongoTicket(Integer.toString(ticket.getTicketID()), ticket.getPrice(), ticket.getSold()));
            }
            Company company = flight.getCompany();
            System.out.println("aaaaa.a..a.a " + company.getCompanyId() + company.getCompanyName());
            MongoCompany mongoCompany = new MongoCompany(Integer.toString(company.getCompanyId()), company.getCompanyName());
            MongoFlight mongoFlight = new MongoFlight(Integer.toString(flight.getFlightID()), flight.getDepartureCity(), flight.getDepartureDate(), flight.getArrivalCity(), flight.getArrivalDate(), mongoCompany);
            mongoFlight.setTickets(tickets);
            mongoFlight.setPlaceLeft(flight.getPlaceLeft());
            mongoFlightService.createFlight(mongoFlight);
        }
        mongoFlightService.setFlag(true);
    }

    public void dropTables(AdminService adminService, CompanyService companyService, UserService userService, TicketService ticketService, FlightService flightService) {
        adminService.deleteAll();
        companyService.deleteAll();
        userService.deleteAllUsers();
        ticketService.deleteAllTickets();
        flightService.deleteAll();
    }
}
