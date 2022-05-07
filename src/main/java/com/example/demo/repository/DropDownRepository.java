package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.DropDownData;

public interface DropDownRepository extends MongoRepository<DropDownData,String> {

}
