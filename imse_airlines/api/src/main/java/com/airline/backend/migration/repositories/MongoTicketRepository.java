package com.airline.backend.migration.repositories;

import com.airline.backend.migration.collections.MongoTicket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoTicketRepository extends MongoRepository<MongoTicket, String> {
}
