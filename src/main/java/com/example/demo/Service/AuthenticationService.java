package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.model.RestModel.ReturnData;
import com.example.demo.repository.EmployeeRepository;


@Service
public class AuthenticationService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
//     EMPLOYEES LOGIN VALIDATION
	public ResponseEntity<ResponseModel> employeeLogin(EmployeeDetail employeeDetail) {
		EmployeeDetail DBemployeeDetail=employeeRepo.findByUserName(employeeDetail.getUserName());
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		if(DBemployeeDetail == null)
			return new ResponseEntity<>(new ResponseModel("UserName Incorrect"),HttpStatus.UNAUTHORIZED);
		boolean check=true;
		for(String role : DBemployeeDetail.getRole())
		{
		   if(role.equals(employeeDetail.getPosition()))
			 check=false;
		}
		if(check)
			return new ResponseEntity<>(new ResponseModel("You are not "+employeeDetail.getPosition()),HttpStatus.UNAUTHORIZED);
		if(passwordEncoder.matches(employeeDetail.getPassword(),DBemployeeDetail.getPassword()))
		{
			ReturnData data=new ReturnData(DBemployeeDetail.getId(),DBemployeeDetail.getUserName(),DBemployeeDetail.getEmail(),DBemployeeDetail.getFirstName(),DBemployeeDetail.getPhone());
			return new ResponseEntity<>(new ResponseModel(employeeDetail.getPosition(),data),HttpStatus.OK);
	    }
		else
		return new ResponseEntity<>(new ResponseModel("PassWord Incorrect"),HttpStatus.UNAUTHORIZED);
	}

}
