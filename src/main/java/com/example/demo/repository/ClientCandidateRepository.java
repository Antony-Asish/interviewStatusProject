package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.ClientCandidate;

public interface ClientCandidateRepository extends MongoRepository<ClientCandidate, String> {

}
