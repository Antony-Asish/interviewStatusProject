package com.example.demo.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AdminService;
import com.example.demo.model.ClientCandidate;
import com.example.demo.model.RestModel.CandidateCount;
import com.example.demo.model.RestModel.ListOfEmployee;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	//  SHOW PANEL LIST USING PAGINATION
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
	public ClientCandidate assignCandidateToClient(@RequestBody ClientCandidate clientCandidate)
	{
		return adminService.assignCandidateToClient(clientCandidate);
	}
	
	
    //  SESSION TRINING
	@GetMapping("sessionTry")
	public String sessionTry(HttpServletResponse response)
	{
		return adminService.sessionTry(response);
	}
	
}
