package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.ExcelService;
import com.example.demo.model.CandidateDetail;
import com.example.demo.model.ClientDetail;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.ClientRepository;

@RestController
@CrossOrigin
@RequestMapping("excel")
public class ExcelController {
	
	@Autowired
	private CandidateRepository candidateRepo;
		
	@Autowired
	private ClientRepository clientRepo;
	
	//   GIVE LIST OF DETAIL BY PAGE 
	@GetMapping("/candidate")
	public void allCandidateReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Candidate_Details.xlsx";
		
		response.setHeader(headerKey, headerValue);
		List<CandidateDetail> listCandidate=candidateRepo.findAll();
		ExcelService excelExporter=new ExcelService(listCandidate);
		excelExporter.allCandidateReport(response);
	}
	
	//   GET REPORT BY STATUS OF THAT CANDIDATE
	@GetMapping("/candidate/{status}")
	public void hiredCandidateReport(HttpServletResponse response,
			@PathVariable("status") String status) throws IOException
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename="+status+"_Candidate_Details.xlsx";
		response.setHeader(headerKey, headerValue);
		List<CandidateDetail> listCandidate=candidateRepo.findByStatus(status);
		ExcelService excelExporter=new ExcelService(listCandidate);
		excelExporter.hiredCandidateReport(response);
	}
	
	//   GET CLIENT DETAIL REPORT
	@GetMapping("/client")
	public void clientReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Client_Details.xlsx";
		response.setHeader(headerKey, headerValue);
		List<ClientDetail> listOfClient=clientRepo.findAll();
		ExcelService excelExporter=new ExcelService();
		excelExporter.assignClientList(listOfClient);
		excelExporter.clientReport(response);
	}
	
}
