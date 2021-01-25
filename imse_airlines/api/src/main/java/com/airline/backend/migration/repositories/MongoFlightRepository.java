package com.airline.backend.migration.repositories;

import com.airline.backend.migration.collections.MongoFlight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoFlightRepository extends MongoRepository<MongoFlight, String> {
}
