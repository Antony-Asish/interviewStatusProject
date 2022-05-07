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
import com.example.demo.Response.ResponseAddPanel;
import com.example.demo.Response.ResponseModel;
import com.example.demo.model.CandidateDetail;
import com.example.demo.model.DropDownData;
import com.example.demo.model.JobDescription;
import com.example.demo.model.EmployeeCandidate;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.PanelDetail;

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

//CANDIDATE STATUS UPDATING
@PostMapping("candidate/{status}/{id}")
public ResponseEntity<ResponseAddCandidate> candidateStatusUpdate(@PathVariable("status") String status,
		@PathVariable("id") String id)
{
    return service.candidateStatusUpdate(id,status);
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

//    GIVE DROP DOWN DATA FOR FORM FILLING
@GetMapping("dropDown/{name}")
public DropDownData dropDownDetail(@PathVariable("name") String name)
{
return service.dropDownDetail(name);
}

//DROP DOWN DATA CREATE AND UPDATE
@PostMapping("dropDown")
public DropDownData dropDownUpdate(@RequestParam("dropDownName") String dropDownName,
		@RequestParam("newData") ArrayList<String> newData	)
{
	return service.dropDownUpdate(dropDownName,newData);
}

@DeleteMapping("dropDown")
public DropDownData dropDownDelete(@RequestParam("dropDownName") String dropDownName,
		@RequestParam("removeData") ArrayList<String> removeData)
{
	return service.dropDownDelete(dropDownName,removeData);
}


//    PANEL CREATION
@PostMapping("panel")
public ResponseEntity<ResponseAddPanel> addPanel(@RequestBody PanelDetail ob)
{
	return service.panelAdding(ob);
}

//DELETE EMPLOYEE DETAIL
@DeleteMapping("panel")
public ResponseEntity<ResponseModel> deletePanel(@RequestParam("id") String id)
{
return service.deletePanel(id);
}

//   CREATE JOBDESCRIPTION 
@PostMapping("jobDescription")
public ResponseEntity<ResponseModel> jobDescription(@RequestBody JobDescription ob)
{
	return service.jobDescription(ob);
}

//   DELETE JOBDESCRIPTION 
@DeleteMapping("jobDescription")
public ResponseEntity<ResponseModel> updateJobDescription(@RequestParam("id") String id)
{
	return service.deleteJobDescription(id);
}


//   TRAINING PURPOSE


}

