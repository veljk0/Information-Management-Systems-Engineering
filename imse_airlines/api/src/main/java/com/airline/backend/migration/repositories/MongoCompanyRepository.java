package com.airline.backend.migration.repositories;

import com.airline.backend.migration.collections.MongoCompany;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoCompanyRepository extends MongoRepository<MongoCompany, String> {
}
