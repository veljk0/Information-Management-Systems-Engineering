package com.airline.backend.services;

import java.util.List;

import com.airline.backend.entities.Company;
import com.airline.backend.repositories.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company getCompanyPerName(String name){
        List<Company> companies = companyRepository.findAll();
        Company result = companies.stream().filter(c -> c.getCompanyName().equals(name)).findFirst().orElse(null);
        return result;
    }

    public String createCompany(Company company){
        companyRepository.save(company);
        return "Company created!";
    }

    public String deleteAll(){
        companyRepository.deleteAll();
        return "ALL companies deleted";
    }

    public String deleteCompany(int id){
        companyRepository.deleteById(id);
        return "Company " + id + " deleted!";
    }

	public String updateFlight(Company company) {
        companyRepository.save(company);
		return "flight added!";
    }
    
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

	public String deletePerName(String name) {
        List<Company> companies = companyRepository.findAll();
        Company deleteMe = companies.stream().filter(c -> c.getCompanyName().equals(name)).findFirst().orElse(null);
        if(deleteMe == null) return "no company found";
        else return deleteCompany(deleteMe.getCompanyId());
    }
}
