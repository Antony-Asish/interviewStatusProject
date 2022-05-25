package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.ClientDetail;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.model.RestModel.ReturnData;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.EmployeeRepository;


@Service
public class AuthenticationService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
//     EMPLOYEES LOGIN VALIDATION
	public ResponseEntity<ResponseModel> employeeLogin(EmployeeDetail employee) {
		if(employee.getUserName() == null || employee.getPassword() == null || employee.getPosition() == null)
			return new ResponseEntity<>(new ResponseModel("Fill All Field"),HttpStatus.BAD_REQUEST);
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		if(employee.getPosition().equals("client"))
		{
			ClientDetail clientDetail=clientRepo.findByUserName(employee.getUserName());
			if(clientDetail == null)
				return new ResponseEntity<>(new ResponseModel("UserName Incorrect"),HttpStatus.UNAUTHORIZED);
			if(passwordEncoder.matches(employee.getPassword(),clientDetail.getPassword()))
			{
				ReturnData data=new ReturnData(clientDetail.getId(),clientDetail.getUserName(),clientDetail.getEmail(),
						clientDetail.getFirstName(),clientDetail.getPhone());
				return new ResponseEntity<>(new ResponseModel(employee.getPosition(),data),HttpStatus.OK);
		    }
			else
			return new ResponseEntity<>(new ResponseModel("PassWord Incorrect"),HttpStatus.UNAUTHORIZED);
		}
		EmployeeDetail DBemployeeDetail=employeeRepo.findByUserName(employee.getUserName());
		if(DBemployeeDetail == null)
			return new ResponseEntity<>(new ResponseModel("UserName Incorrect"),HttpStatus.UNAUTHORIZED);
		boolean check=true;
		for(String role : DBemployeeDetail.getRole())
		{
		   if(role.equals(employee.getPosition()))
			 check=false;
		}
		if(check)
			return new ResponseEntity<>(new ResponseModel("You are not "+employee.getPosition()),HttpStatus.UNAUTHORIZED);
		if(passwordEncoder.matches(employee.getPassword(),DBemployeeDetail.getPassword()))
		{
			ReturnData data=new ReturnData(DBemployeeDetail.getId(),DBemployeeDetail.getUserName(),DBemployeeDetail.getEmail(),DBemployeeDetail.getFirstName(),DBemployeeDetail.getPhone());
			return new ResponseEntity<>(new ResponseModel(employee.getPosition(),data),HttpStatus.OK);
	    }
		else
		return new ResponseEntity<>(new ResponseModel("PassWord Incorrect"),HttpStatus.UNAUTHORIZED);
	}

}
