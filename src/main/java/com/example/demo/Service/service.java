package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.CandidateDetail;
import com.example.demo.model.EmployeeCandidate;
import com.example.demo.model.PanelDetail;
import com.example.demo.model.RestModel.ListOfCandidate;
import com.example.demo.model.RestModel.ResponseAddPanel;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.EmployeeCandidateRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PanelRepository;

@Service
public class service {
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private CandidateRepository candidateRepo;
	@Autowired
	private EmployeeCandidateRepository employeeCandidateRepo;
	@Autowired
	private PanelRepository panelRepo;

	
	int rand;
	
	Random random=new Random();
	

			


///      MY ADDDITIONAL WORK 

    

  
//      ADMIN GIVE SOME CANDIDATE TO EMPLOYEE FOR INTERVIEW
public EmployeeCandidate employeeCandidate(EmployeeCandidate ob) {
return employeeCandidateRepo.save(ob);
}

//      EMPLOYEE VIEWING HIS INTERVIEW CANDIDATE'S DETAILS
public ArrayList<ListOfCandidate> employeeView(String id) {
EmployeeCandidate object=employeeCandidateRepo.findByEmpId(id);
ListOfCandidate list=new ListOfCandidate();
CandidateDetail ob=new CandidateDetail();
ArrayList<ListOfCandidate> result=new ArrayList<ListOfCandidate>();
for(int i=0;i<object.getCandidateId().size();i++)
{
ob=candidateRepo.findById(object.getCandidateId().get(i)).get();
list=new ListOfCandidate(ob.getId(),ob.getFirstName(),ob.getEmail()
		,ob.getPhone(),ob.getSkill(),ob.getJob());
result.add(list);
}
return result;
}

//     PANEL ADDING AND UPDATING
public ResponseEntity<ResponseAddPanel> panelAdding(PanelDetail ob) {
	if(ob.getId() == null)
	{
	    if(panelRepo.existsByUserName(ob.getUserName()))
	       return new ResponseEntity<>(new ResponseAddPanel("UserName Already Taken!"),HttpStatus.BAD_REQUEST);
	    if(panelRepo.existsByEmail(ob.getEmail()))
		   return new ResponseEntity<>(new ResponseAddPanel("Email Already Taken!"),HttpStatus.BAD_REQUEST);
	    if(panelRepo.existsByPhone(ob.getPhone()))
		   return new ResponseEntity<>(new ResponseAddPanel("Phone Number Already Taken"),HttpStatus.BAD_REQUEST);
	    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	    ob.setPassword(passwordEncoder.encode(ob.getPassword()));
	    return new ResponseEntity<>(new ResponseAddPanel("Panel Create succesfully",panelRepo.insert(ob)),HttpStatus.OK);
	}
	else
	{
		if(panelRepo.existsByUserNameAndIdIsNot(ob.getUserName(),ob.getId()))
		    return new ResponseEntity<>(new ResponseAddPanel("UserName Already Taken!"),HttpStatus.BAD_REQUEST);
		if(panelRepo.existsByEmailAndIdIsNot(ob.getEmail(),ob.getId()))
			return new ResponseEntity<>(new ResponseAddPanel("Email Already Taken!"),HttpStatus.BAD_REQUEST);
		if(panelRepo.existsByPhoneAndIdIsNot(ob.getPhone(),ob.getId()))
			return new ResponseEntity<>(new ResponseAddPanel("Phone Number Already Taken"),HttpStatus.BAD_REQUEST);
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	    ob.setPassword(passwordEncoder.encode(ob.getPassword()));
		return new ResponseEntity<>(new ResponseAddPanel("Panel Deatil Updated succesfully",panelRepo.save(ob)),HttpStatus.OK);
	}
}

//      PANEL DETAIL DELETE
public ResponseEntity<ResponseModel> deletePanel(String id) {
	if(employeeRepo.existsById(id))
	{
	   employeeRepo.deleteById(id);
	   return new ResponseEntity<>(new ResponseModel("Panel Detail Deleted Successfully"),HttpStatus.OK);
	}
	else
	   return new ResponseEntity<>(new ResponseModel("Id is Wrong"),HttpStatus.BAD_REQUEST);
}

    //    TRAINING PURPOSE
}