package com.airline.backend.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Company {
    @Id
    private int companyId;
    private String companyName;
    @OneToMany(targetEntity = Flight.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "companyid_fk", referencedColumnName = "companyId")
    private List<Flight> flights;


    public Company() {}

    public Company(int companyId, String companyName, List<Flight> flights) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.flights = flights;
    }
    

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonManagedReference
    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "Company [companyId=" + companyId + ", companyName=" + companyName + ", flights=" + flights + "]";
    }

}
