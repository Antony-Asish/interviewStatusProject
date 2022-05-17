package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.CandidateDetail;

public interface CandidateRepository extends MongoRepository<CandidateDetail,String> {

	CandidateDetail findByUserName(String userName);

	boolean existsByEmail(String email);
		
	boolean existsByPhone(Long phone);

	CandidateDetail findByEmail(String email);

	boolean existsByEmailAndIdIsNot(String email, String id);

	boolean existsByPhoneAndIdIsNot(Long phone, String id);
	
	List<CandidateDetail> findByStatus(String statusName);

	int countByStatus(String string);

	List<CandidateDetail> findByClientId(String clientId);

	int countByStatusAndClientIdIs(String string, String clientId);

	int countByClientId(String clientId);
}
