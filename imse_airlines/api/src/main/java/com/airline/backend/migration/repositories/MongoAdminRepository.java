package com.airline.backend.migration.repositories;

import com.airline.backend.migration.collections.MongoAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoAdminRepository extends MongoRepository<MongoAdmin, String> {
}
