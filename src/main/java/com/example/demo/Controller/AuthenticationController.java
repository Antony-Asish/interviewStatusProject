package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AuthenticationService;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.RestModel.ResponseModel;

@RestController
@CrossOrigin
public class AuthenticationController {
	@Autowired
	AuthenticationService authenticationService;

//  EMPLOYEE LOGIN
@PutMapping("login")
public ResponseEntity<ResponseModel> employeeLogin(@RequestBody EmployeeDetail ob)
{
return authenticationService.employeeLogin(ob);
}
	
}
