package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Job;

public interface JobRepository extends MongoRepository<Job,String> {

}
