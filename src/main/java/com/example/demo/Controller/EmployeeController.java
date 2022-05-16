package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.EmployeeService;
import com.example.demo.model.CandidateDetail;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.RestModel.DashBoardReturn;
import com.example.demo.model.RestModel.ListOfEmployee;
import com.example.demo.model.RestModel.ResponseAddEmployee;
import com.example.demo.model.RestModel.ResponseModel;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

//  GET DASHBOARD DETAIL
@GetMapping("dashBoard")
public ArrayList<DashBoardReturn> dashBoard() {
	return employeeService.dashBoard();
}	
	
//  GET REPORT USING DASHBOARD DETAIL
@GetMapping("dashBoard/{name}")
public List<CandidateDetail> dashBoardReport(@PathVariable("name") String statusName)
{
	return employeeService.dashBoardReport(statusName);
}

//  EMPLOYEE ADDING AND UPDATING
@PostMapping("employee")
public ResponseEntity<ResponseAddEmployee> employeeAdding(@RequestBody EmployeeDetail ob)
{
return employeeService.addemployee(ob);
}

// EMPLOYEE LIST
@GetMapping("employee")
public ArrayList<ListOfEmployee> employeeList(Pageable page)
{
return employeeService.employeeList(page);
}

// VIEW EMPLOYEE FULL DETAIL
@GetMapping("employee/{id}")
public EmployeeDetail viewEmployeeDetail(@PathVariable("id") String id)
{
return employeeService.viewEmployeeDetail(id);
}

// DELETE EMPLOYEE DETAIL
@DeleteMapping("employee/{id}")
public ResponseEntity<ResponseModel> deleteEmployee(@PathVariable("id") String id)
{
return employeeService.deleteEmployee(id);
}
}
