package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.ClientCandidate;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.RestModel.CandidateCount;
import com.example.demo.model.RestModel.ListOfEmployee;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.ClientCandidateRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class AdminService {
	
	@Autowired
	private CandidateRepository candidateRepo;

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ClientCandidateRepository clientCandidateRepo;
	
	//  SHOW PANEL LIST 
	public ArrayList<ListOfEmployee> panelList() {
		ArrayList<ListOfEmployee> employeeList=new ArrayList<ListOfEmployee>();
		List<EmployeeDetail> DBemployeeList=employeeRepo.findAll();
		for(EmployeeDetail employee : DBemployeeList)
		{
			if(employee.getRole() != null)
			for(String role : employee.getRole())
			{
				if(role.equals("panel"))
				{
					employeeList.add(new ListOfEmployee(employee.getId(),employee.getFirstName(),
							employee.getLastName(),employee.getEmail(),employee.getDepartment(),
							employee.getLinkedIn(),employee.getPhone(),employee.getRole()));
				}
			}
		}
	    return employeeList;
	}
	
	//     SHOW PANEL LIST COUNT
	public CandidateCount panelCount() {
		int count=0;
		List<EmployeeDetail> DBemployeeList=employeeRepo.findAll();
		for(EmployeeDetail employeeDetail : DBemployeeList)
		{
			if(employeeDetail.getRole() != null)
			for(String role : employeeDetail.getRole())
			{
				if(role.equals("panel"))
				{
					count++;
				}
			}
		}
		return new CandidateCount(count);
	}
	
	//  ASSIGN SOME CANDIDATE TO CLIENT
	public ResponseEntity<ResponseModel> assignCandidateToClient(ClientCandidate clientCandidate) {
		if(clientCandidate.getClientId() ==null || clientCandidate.getClientId() == null)
			return new ResponseEntity<>(new ResponseModel("ClientId and CandidateId Need"),HttpStatus.BAD_REQUEST); 
		if(clientRepo.existsById(clientCandidate.getClientId()))
		{
			for(String candidateId : clientCandidate.getCandidateId())
			{
				if(candidateRepo.existsById(candidateId))
				{}
				else
				   return new ResponseEntity<>(new ResponseModel("Candiate Is Not Found"),HttpStatus.BAD_REQUEST); 
			}
		}
		else
		   return new ResponseEntity<>(new ResponseModel("Client Is Not Found"),HttpStatus.BAD_REQUEST); 
		clientCandidateRepo.save(clientCandidate);
		return new ResponseEntity<>(new ResponseModel("Candiate Assign Successfully"),HttpStatus.OK); 		
	}
}
