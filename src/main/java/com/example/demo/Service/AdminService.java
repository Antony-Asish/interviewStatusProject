package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.CandidateDetail;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.RestModel.ClientCandidate;
import com.example.demo.model.RestModel.ListOfEmployee;
import com.example.demo.model.RestModel.ResponseAddCandidate;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class AdminService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private CandidateRepository candidateRepo;
	
	//  SHOW PANEL LIST USING PAGINATION
	public ArrayList<ListOfEmployee> panelList() {
		ArrayList<ListOfEmployee> employeeList=new ArrayList<ListOfEmployee>();
		List<EmployeeDetail> DBemployeeList=employeeRepo.findAll();
		for(EmployeeDetail employeeDetail : DBemployeeList)
		{
			if(employeeDetail.getRole() != null)
			for(String role : employeeDetail.getRole())
			{
				if(role.equals("panel"))
				{
					employeeList.add(new ListOfEmployee(employeeDetail.getId(),employeeDetail.getFirstName(),
							employeeDetail.getEmail(),employeeDetail.getDepartment(),employeeDetail.getRole()));
				}
			}
		}
	    return employeeList;
	}
	
	//  ASSIGN SOME CANDIDATE TO CLIENT
	public ResponseEntity<ResponseAddCandidate> assignCandidateToClient(ClientCandidate clientCandidate) {
		for(String id :clientCandidate.getCandidateId())
		{
			CandidateDetail candidateDetail=candidateRepo.findById(id).get();
			candidateDetail.setClientId(clientCandidate.getClientId());
			candidateRepo.save(candidateDetail);
		}
		return new ResponseEntity<>(new ResponseAddCandidate("CandidateAssign Successfully"),HttpStatus.OK);
	}

}
