package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ClientCandidate;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.RestModel.CandidateCount;
import com.example.demo.model.RestModel.ListOfEmployee;
import com.example.demo.repository.ClientCandidateRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class AdminService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private ClientCandidateRepository clientCandidateRepo;
	
	//  SHOW PANEL LIST USING 
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
							employeeDetail.getLastName(),employeeDetail.getEmail(),employeeDetail.getDepartment(),
							employeeDetail.getLinkedIn(),employeeDetail.getPhone(),employeeDetail.getRole()));
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
	public ClientCandidate assignCandidateToClient(ClientCandidate clientCandidate) {
		return clientCandidateRepo.save(clientCandidate);
	}

    //    SESSION TRINING
	public String sessionTry(HttpServletResponse response) {
		Cookie cookie=new Cookie("Antony","Asish");
		cookie.setMaxAge(60);
		response.addCookie(cookie);
		return "Return Cookie";
	}

}
