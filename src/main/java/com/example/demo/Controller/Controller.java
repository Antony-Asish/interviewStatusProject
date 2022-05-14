package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Service.service;

import com.example.demo.model.EmployeeCandidate;
import com.example.demo.model.PanelDetail;
import com.example.demo.model.RestModel.ListOfCandidate;
import com.example.demo.model.RestModel.ResponseAddPanel;
import com.example.demo.model.RestModel.ResponseModel;

@RestController
@CrossOrigin
public class Controller {

@Autowired
private service service;

//     ADMIN GIVE SOME CANDIDATE TO EMPLOYEE FOR INTERVIEW
@PostMapping("employeeCandidate")
public EmployeeCandidate employeeCandidate(@RequestBody EmployeeCandidate ob)
{
return service.employeeCandidate(ob);	
}

//     EMPLOYEE VIEWING HIS INTERVIEW CANDIDATE DETAILS
@GetMapping("employeeView")
public ArrayList<ListOfCandidate> employeeView(@RequestParam("id") String id)
{
return service.employeeView(id);	
}

//     PANEL CREATION
@PostMapping("panel")
public ResponseEntity<ResponseAddPanel> addPanel(@RequestBody PanelDetail ob)
{
return service.panelAdding(ob);
}

//     DELETE PANEL DETAIL
@DeleteMapping("panel")
public ResponseEntity<ResponseModel> deletePanel(@RequestParam("id") String id)
{
return service.deletePanel(id);
}


//   TRAINING PURPOSE


}

