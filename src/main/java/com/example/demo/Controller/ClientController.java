package com.example.demo.Controller;

import java.util.ArrayList;

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

import com.example.demo.Service.ClientService;
import com.example.demo.model.ClientDetail;
import com.example.demo.model.RestModel.ClientAddResponse;
import com.example.demo.model.RestModel.DashBoardReturn;
import com.example.demo.model.RestModel.ListOfCandidate;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.model.RestModel.ShowClientList;

@RestController
@CrossOrigin
public class ClientController {

	@Autowired
	private ClientService clientService;
	
//  SHOW CLIENT DETAIL USING PAGINATION
	@GetMapping("client")
	public ArrayList<ShowClientList>clientList(Pageable page)
	{
		return clientService.clientList(page);
	}
	
//  SHOW CLIENT DASHBOARD DETAIL
	@GetMapping("client/{id}/dashBoard")
	public ArrayList<DashBoardReturn> clientDashBoard(@PathVariable("id") String clientId)
	{
		return clientService.clientDashBoard(clientId);
	}
	
//  NEW CLIENT ADDING AND UPDATING
	@PostMapping("client")
	public ResponseEntity<ClientAddResponse> clientAdding(@RequestBody ClientDetail clientDetail)
	{
		return clientService.clientAdding(clientDetail);
	}
	
//  CLIENT DETAIL DELETING
	@DeleteMapping("client/{id}")
	public ResponseEntity<ResponseModel> clientDeleted(@PathVariable("id") String id)
	{
		return clientService.clientDeleted(id);
	}
	
//  SHOW CLIENT'S CANDIDATE DETAIL
	@GetMapping("client/{id}")
	public ArrayList<ListOfCandidate> viewClientCandidateList(@PathVariable("id") String clientId)
	{
		return clientService.viewClientCandidateList(clientId);
	}
}
