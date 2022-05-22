package com.example.demo.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Client_Candidate")
public class ClientCandidate {

	@Id
	private String clientId;
	private ArrayList<String> candidateId;
	
	public ClientCandidate(String clientId, ArrayList<String> candidateId) {
		super();
		this.clientId = clientId;
		this.candidateId = candidateId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public ArrayList<String> getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(ArrayList<String> candidateId) {
		this.candidateId = candidateId;
	}	
}
