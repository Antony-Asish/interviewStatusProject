package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AdminService;
import com.example.demo.model.ClientCandidate;
import com.example.demo.model.RestModel.CandidateCount;
import com.example.demo.model.RestModel.ListOfEmployee;
import com.example.demo.model.RestModel.ResponseModel;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	//  SHOW PANEL LIST
	@GetMapping("panel")
	public ArrayList<ListOfEmployee> panelList()
	{
		return adminService.panelList();
	}
	
    //  SHOW PANEL LIST COUNT
	@GetMapping("panelCount")
	public CandidateCount panelCount()
	{
		return adminService.panelCount();
	}
	
	//  ADMIN ASSIGN SOME CANDIDATE TO 
	@PostMapping("clientCandiate")
	public ResponseEntity<ResponseModel> assignCandidateToClient(@RequestBody ClientCandidate clientCandidate)
	{
		return adminService.assignCandidateToClient(clientCandidate);
	}
}
