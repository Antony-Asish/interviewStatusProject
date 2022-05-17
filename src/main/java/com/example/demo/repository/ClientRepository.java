package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.ClientDetail;

public interface ClientRepository extends MongoRepository<ClientDetail,String> {

	boolean existsByEmail(String email);

	boolean existsByPhone(Long phone);

	boolean existsByUserName(String userName);

	boolean existsByUserNameAndIdIsNot(String userName, String id);

	boolean existsByEmailAndIdIsNot(String email, String id);

	boolean existsByPhoneAndIdIsNot(Long phone, String id);

	ClientDetail findByUserName(String userName);

}
