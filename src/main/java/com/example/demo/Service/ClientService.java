package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.ClientDetail;
import com.example.demo.model.RestModel.ClientAddResponse;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.model.RestModel.ShowClientList;
import com.example.demo.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;
	
   //  SHOW CLIENT LIST USING PAGINATION
	public ArrayList<ShowClientList> clientList(Pageable page) {
		Page<ClientDetail> clientDetailList=clientRepo.findAll(page);
		ArrayList<ShowClientList> clientList=new ArrayList<ShowClientList>();
		for(ClientDetail clientDetail: clientDetailList)
		{
			clientList.add(new ShowClientList(clientDetail.getId(),clientDetail.getFirstName(),
					clientDetail.getEmail(),clientDetail.getPhone()));
		}
		return clientList;
	}
	
	//   NEW CLIENT ADDING AND UPDATING
	public ResponseEntity<ClientAddResponse> clientAdding(ClientDetail clientDetail) {
		if(clientDetail.getId()==null)
		{
			if(clientRepo.existsByUserName(clientDetail.getUserName()))
			    return new ResponseEntity<>(new ClientAddResponse("UserName Already Exists!"),HttpStatus.BAD_REQUEST);
		    if(clientRepo.existsByEmail(clientDetail.getEmail()))
			    return new ResponseEntity<>(new ClientAddResponse("Email Already Exists!"),HttpStatus.BAD_REQUEST);
		    if(clientRepo.existsByPhone(clientDetail.getPhone()))
		     	return new ResponseEntity<>(new ClientAddResponse("Phone Number Already Exists!"),HttpStatus.BAD_REQUEST);
		    return new ResponseEntity<>(new ClientAddResponse("New Client Detail Added",clientRepo.insert(clientDetail)),HttpStatus.OK);
		}
		else
		{
			if(clientRepo.existsByUserNameAndIdIsNot(clientDetail.getUserName(),clientDetail.getId()))
			    return new ResponseEntity<>(new ClientAddResponse("UserName Already Exists!"),HttpStatus.BAD_REQUEST);
			 if(clientRepo.existsByEmailAndIdIsNot(clientDetail.getEmail(),clientDetail.getId()))
				    return new ResponseEntity<>(new ClientAddResponse("Email Already Exists!"),HttpStatus.BAD_REQUEST);
			 if(clientRepo.existsByPhoneAndIdIsNot(clientDetail.getPhone(),clientDetail.getId()))
			     	return new ResponseEntity<>(new ClientAddResponse("Phone Number Already Exists!"),HttpStatus.BAD_REQUEST);
			 return new ResponseEntity<>(new ClientAddResponse("Client Detail Updated",clientRepo.save(clientDetail)),HttpStatus.OK);
		}
	}

//    CLIENT DETAIL DELETING
	public ResponseEntity<ResponseModel> clientDeleted(String id) {
		if(clientRepo.existsById(id))
		{
		  clientRepo.deleteById(id);
		  return new ResponseEntity<>(new ResponseModel("Candidate Detail Deleted"),HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(new ResponseModel("Id is Wrong"),HttpStatus.BAD_REQUEST);
		}
	}