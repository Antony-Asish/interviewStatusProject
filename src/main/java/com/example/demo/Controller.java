package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Response.CandidateCount;
import com.example.demo.Response.DashBoardReturn;
import com.example.demo.Response.ListOfCandidate;
import com.example.demo.Response.ListOfEmployee;
import com.example.demo.Response.ResponseAddCandidate;
import com.example.demo.Response.ResponseAddEmployee;
import com.example.demo.Response.ResponseModel;
import com.example.demo.dummyClass.DepartmentDetail;
import com.example.demo.model.CandidateDetail;
import com.example.demo.model.EmployeeCandidate;
import com.example.demo.model.EmployeeDetail;

@RestController
@CrossOrigin
public class Controller {

@Autowired
private service service;
	
//     EMPLOYEE LOGIN
@PutMapping("login")
public ResponseEntity<ResponseModel> employeeLogin(@RequestBody EmployeeDetail ob)
{
return service.employeeLogin(ob);

}

//     GET DASHBOARD DETAIL
@GetMapping("dashBoard")
public ArrayList<DashBoardReturn> dashBoard() {
	return service.dashBoard();
}

//     GIVE CANDIDATE COUNT
@GetMapping("candidateCount")
public CandidateCount count()
{
	return service.countCandidateDetail();
}

//      GIVE LIST OF DETAIL BY PAGE 
@GetMapping("candidate")
public ArrayList<ListOfCandidate> page(Pageable page)
{
	return service.page(page);
}

//     GIVE FORM DEATIL ( CANDIDATE JOB SELECTION)
@GetMapping("jobData")
public DepartmentDetail jobDetail()
{
	return service.jobDetail();
}

//     CANDIDATE ADDING AND UPDATING
@PostMapping("candidate")
public ResponseEntity<ResponseAddCandidate> addCandidate(@RequestBody CandidateDetail ob)
{
return service.addcandidate(ob);
}

//     VIEW CANDIDATE FULL DETAIL
@GetMapping("candidate/{id}")
public CandidateDetail viewCandidateDetail(@PathVariable("id") String id)
{
return service.viewCandidateDetail(id);
}

//     DELETE CANDIDATE DETAIL
@DeleteMapping("candidate")
public ResponseEntity<ResponseModel> deleteCandidate(@RequestParam("id") String id)
{
return service.deleteCandidate(id);
}

//     EMPLOYEE ADDING AND UPDATING
@PostMapping("employee")
public ResponseEntity<ResponseAddEmployee> employeeAdding(@RequestBody EmployeeDetail ob)
{
return service.addemployee(ob);
}

//    EMPLOYEE LIST
@GetMapping("employee")
public ArrayList<ListOfEmployee> employeeList(Pageable page)
{
return service.employeeList(page);
}

//    VIEW EMPLOYEE FULL DETAIL
@GetMapping("employee/{id}")
public EmployeeDetail viewEmployeeDetail(@PathVariable("id") String id)
{
return service.viewEmployeeDetail(id);
}

//    DELETE EMPLOYEE DETAIL
@DeleteMapping("employee")
public ResponseEntity<ResponseModel> deleteEmployee(@RequestParam("id") String id)
{
return service.deleteEmployee(id);
}

//    ADMIN GIVE SOME CANDIDATE TO EMPLOYEE FOR INTERVIEW
@PostMapping("employeeCandidate")
public EmployeeCandidate employeeCandidate(@RequestBody EmployeeCandidate ob)
{
	return service.employeeCandidate(ob);	
}

//    EMPLOYEE VIEWING HIS INTERVIEW CANDIDATE DETAILS
@GetMapping("employeeView")
public ArrayList<ListOfCandidate> employeeView(@RequestParam("id") String id)
{
	return service.employeeView(id);	
}

//   TRAINING PURPOSE

//   GET ALL CANDIDATE DETAIL


//EMAIL VALIDATION
@PutMapping("emailCheck")
public String emailCheck(@RequestParam("name") String name)
{
return service.emailChecking(name);
}

//UPDATE PASSWORD USING OTP
@PutMapping("updatePassword")
public String updatePassword(@RequestBody EmployeeDetail ob)
{
return service.updatePassword(ob);
}
}

