package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.CandidateDetail;

public interface CandidateRepository extends MongoRepository<CandidateDetail,String> {

	CandidateDetail findByUserName(String userName);

	boolean existsByEmail(String email);
		
	boolean existsByPhone(Long phone);

	CandidateDetail findByEmail(String email);

	boolean existsByEmailAndIdIsNot(String email, String id);

	boolean existsByPhoneAndIdIsNot(Long phone, String id);

	int countByStatus(String string);
}
