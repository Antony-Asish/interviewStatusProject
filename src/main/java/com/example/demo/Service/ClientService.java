package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.CandidateDetail;
import com.example.demo.model.ClientCandidate;
import com.example.demo.model.ClientDetail;
import com.example.demo.model.RestModel.ClientAddResponse;
import com.example.demo.model.RestModel.DashBoardReturn;
import com.example.demo.model.RestModel.ListOfCandidate;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.model.RestModel.ShowClientList;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.ClientCandidateRepository;
import com.example.demo.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private CandidateRepository candidateRepo;
	
	@Autowired
	private ClientCandidateRepository clientCandidateRepo;
	
   //  SHOW CLIENT LIST USING PAGINATION
	public ArrayList<ShowClientList> clientList(Pageable page) {
		Page<ClientDetail> clientDetailList=clientRepo.findAll(page);
		ArrayList<ShowClientList> clientList=new ArrayList<ShowClientList>();
		for(ClientDetail client: clientDetailList)
		{
			clientList.add(new ShowClientList(client.getId(),client.getFirstName(),
					client.getEmail(),client.getPhone()));
		}
		return clientList;
	}
	
	//   SHOW CLIENT DASHBOARD DETAIL
	public ArrayList<DashBoardReturn> clientDashBoard(String clientId) {
		int hired=0,rejected=0,waitingList=0,progress=0,totalCandidate=0;
		ClientCandidate clientCandidate=clientCandidateRepo.findById(clientId).get();	
		for(String candidateId : clientCandidate.getCandidateId())
		{
			CandidateDetail candidate=candidateRepo.findById(candidateId).get();
			switch(candidate.getStatus())
			{
			    case "hired":
				      hired++;
				      break;
			    case "rejected":
			    	  rejected++;
				      break;
			    case "progress":
				      progress++;
				      break;
			    case "waitingList":
				      waitingList++;
				      break;
			}
			totalCandidate++;
		}
		ArrayList<DashBoardReturn> clientDashBoard=new ArrayList<DashBoardReturn>();
		clientDashBoard.add(new DashBoardReturn("Hired",hired));
		clientDashBoard.add(new DashBoardReturn("Rejected",rejected));
		clientDashBoard.add(new DashBoardReturn("WaitingList",waitingList));
		clientDashBoard.add(new DashBoardReturn("Progress",progress));
		clientDashBoard.add(new DashBoardReturn("TotalCandidate",totalCandidate));
		return clientDashBoard;
	}
	
	//   NEW CLIENT ADDING AND UPDATING
	public ResponseEntity<ClientAddResponse> clientAdding(ClientDetail clientDetail) {
		 BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		if(clientDetail.getId()==null)
		{
			if(clientRepo.existsByUserName(clientDetail.getUserName()))
			    return new ResponseEntity<>(new ClientAddResponse("UserName Already Exists!"),HttpStatus.BAD_REQUEST);
		    if(clientRepo.existsByEmail(clientDetail.getEmail()))
			    return new ResponseEntity<>(new ClientAddResponse("Email Already Exists!"),HttpStatus.BAD_REQUEST);
		    if(clientRepo.existsByPhone(clientDetail.getPhone()))
		     	return new ResponseEntity<>(new ClientAddResponse("Phone Number Already Exists!"),HttpStatus.BAD_REQUEST);
			String password=passwordEncoder.encode(clientDetail.getPassword());
			clientDetail.setPassword(password);
		    return new ResponseEntity<>(new ClientAddResponse("New Client Detail Added",clientRepo.insert(clientDetail)),HttpStatus.OK);
		}
		else
		{
			if(clientDetail.getPassword().equals( clientDetail.getCpassword()))
			{
			    if(clientRepo.existsByUserNameAndIdIsNot(clientDetail.getUserName(),clientDetail.getId()))
			        return new ResponseEntity<>(new ClientAddResponse("UserName Already Exists!"),HttpStatus.BAD_REQUEST);
			    if(clientRepo.existsByEmailAndIdIsNot(clientDetail.getEmail(),clientDetail.getId()))
				    return new ResponseEntity<>(new ClientAddResponse("Email Already Exists!"),HttpStatus.BAD_REQUEST);
			    if(clientRepo.existsByPhoneAndIdIsNot(clientDetail.getPhone(),clientDetail.getId()))
			     	return new ResponseEntity<>(new ClientAddResponse("Phone Number Already Exists!"),HttpStatus.BAD_REQUEST);
			    String password=passwordEncoder.encode(clientDetail.getPassword());
			    clientDetail.setPassword(password);
			    clientDetail.setCpassword(null);
			    return new ResponseEntity<>(new ClientAddResponse("Client Detail Updated",clientRepo.save(clientDetail)),HttpStatus.OK);
			}
			else
				 return new ResponseEntity<>(new ClientAddResponse("Password And Confirm Password Not Same!"),HttpStatus.BAD_REQUEST);
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
			return new ResponseEntity<>(new ResponseModel("CandidateId is Wrong"),HttpStatus.BAD_REQUEST);
		}

    //    SHOW CLIENT'S CANDIDATE DETAIL	
	public ArrayList<ListOfCandidate> viewClientCandidateList(String clientId) {
		ClientCandidate clientCandidate=clientCandidateRepo.findById(clientId).get();
		ArrayList<ListOfCandidate> returnCandidateList=new ArrayList<ListOfCandidate>();
		for(String candidateId : clientCandidate.getCandidateId())
		{
			CandidateDetail candidate=candidateRepo.findById(candidateId).get();
			returnCandidateList.add(new ListOfCandidate(candidate.getId(),candidate.getFirstName(),
					candidate.getStatus(),candidate.getPhone(),candidate.getJob()));
		}
		return returnCandidateList;
	}
	}