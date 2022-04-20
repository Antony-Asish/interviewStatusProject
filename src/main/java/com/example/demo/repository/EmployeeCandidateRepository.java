package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.EmployeeCandidate;

public interface EmployeeCandidateRepository extends MongoRepository<EmployeeCandidate,String> {

	EmployeeCandidate findByEmpId(String id);
}
