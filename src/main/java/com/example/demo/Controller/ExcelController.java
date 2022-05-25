package com.example.demo.Controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.example.demo.model.ClientCandidate;
import com.example.demo.model.ClientDetail;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.ClientCandidateRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.EmployeeRepository;

@RestController
@CrossOrigin
@RequestMapping("excel")
public class ExcelController {
	
	@Autowired
	private CandidateRepository candidateRepo;
		
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private ClientCandidateRepository clientCandidateRepo;
	
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
	
    //     GET EMPLOYEE DETAIL REPORT
	@GetMapping("/employee")
	public void employeeReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Employee_Details.xlsx";
		response.setHeader(headerKey, headerValue);
		List<EmployeeDetail> listOfemployee=employeeRepo.findAll();
		ExcelService excelExporter=new ExcelService();
		excelExporter.assignEmployeeList(listOfemployee);
		excelExporter.employeeReport(response);
	}
	
    //   GET PANEL DETAIL REPORT
	@GetMapping("/panel")
	public void panelReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Panel_Details.xlsx";
		response.setHeader(headerKey, headerValue);
		ArrayList<EmployeeDetail> listOfPanel=new ArrayList<EmployeeDetail>();
		List<EmployeeDetail> DBemployeeList=employeeRepo.findAll();
		for(EmployeeDetail employee : DBemployeeList)
			if(employee.getRole() != null)
			   for(String role : employee.getRole())
				  if(role.equals("panel"))
				  {
					  listOfPanel.add(employee);  
				  }
		ExcelService excelExporter=new ExcelService();
		excelExporter.assignEmployeeList(listOfPanel);
		excelExporter.employeeReport(response);
	}
	
    //     GET CLIENT DETAIL REPORT
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
	
	    //     GET CLIENT DETAIL REPORT
		@GetMapping("/client/{id}/{status}")
		public void clientDashoardStatusReport(@PathVariable("id") String clientId,
				@PathVariable("status") String status,HttpServletResponse response) throws IOException
		{
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = "attachment;filename=Client's_"+status+"_Candidate's_Details.xlsx";
			response.setHeader(headerKey, headerValue);
			ClientCandidate clientCandidate=clientCandidateRepo.findById(clientId).get();
			ArrayList<CandidateDetail> candidateList=new ArrayList<CandidateDetail>();
			for(String candidateId : clientCandidate.getCandidateId())
			{
				CandidateDetail candidate=candidateRepo.findById(candidateId).get();
				if(candidate.getStatus().equals(status))
					candidateList.add(candidate);
			}
			ExcelService excelExporter=new ExcelService(candidateList);
			// WE CAN USE CANDIDATE DETAIL FLOW BECAUSE BOTH ARE SAME
			excelExporter.hiredCandidateReport(response);
		}
		

		
		     //     THIS IS MY TRAINING PURPOSE NOT PROJECT PROPERTY
		
		//     IMPORT DATA FROM EXCEL
		@GetMapping("/import")
		public String importFromExcel() throws IOException
		{
			ExcelService excelService=new ExcelService();
			List<ClientDetail> clientList = excelService.importClientDetail();
			for(ClientDetail client : clientList)
			    System.out.println(client);
			return "import Successfully";
		}
}
