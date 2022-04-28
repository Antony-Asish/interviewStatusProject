package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.PanelDetail;

public interface PanelRepository extends MongoRepository<PanelDetail,String> {

	boolean existsByUserName(String userName);

	boolean existsByEmail(String email);

	boolean existsByPhone(Long phone);

	boolean existsByUserNameAndIdIsNot(String userName, String id);

	boolean existsByEmailAndIdIsNot(String email, String id);

	boolean existsByPhoneAndIdIsNot(Long phone, String id);

}
