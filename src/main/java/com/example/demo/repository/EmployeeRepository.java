package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.EmployeeDetail;

public interface EmployeeRepository extends MongoRepository<EmployeeDetail,String> {

	EmployeeDetail findByUserName(String userName);

	boolean existsByEmail(String email);

	boolean existsByPhone(Long phone);

	boolean existsByUserName(String userName);

	boolean existsByEmailAndIdIsNot(String email, String id);

	boolean existsByPhoneAndIdIsNot(Long phone, String id);

	boolean existsByUserNameAndIdIsNot(String userName, String id);

}
