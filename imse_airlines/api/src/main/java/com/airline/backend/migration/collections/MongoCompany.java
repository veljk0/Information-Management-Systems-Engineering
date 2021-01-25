package com.airline.backend.migration.collections;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document(collection = "companies")
public class MongoCompany {
    @Id
    private String id;
    private String companyName;
    private List<MongoFlight> mongoFlights;

    public MongoCompany() {}

    public MongoCompany(String id, String companyName, List<MongoFlight> mongoFlights) {
        this.id = id;
        this.companyName = companyName;
        this.mongoFlights = mongoFlights;
    }

    public MongoCompany(String id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public MongoCompany(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return id;
    }

    public void setCompanyId(String companyId) {
        this.id = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonManagedReference
    public List<MongoFlight> getFlights() {
        return mongoFlights;
    }

    public void setFlights(List<MongoFlight> flights) {
        this.mongoFlights = flights;
    }

    @Override
    public String toString() {
        return "Company [companyId=" + id + ", companyName=" + companyName + ", flights=" + mongoFlights + "]";
    }

}
