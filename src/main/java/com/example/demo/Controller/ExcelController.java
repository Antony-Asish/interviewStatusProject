package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.CandidateService;
import com.example.demo.Service.ExcelService;
import com.example.demo.model.CandidateDetail;
import com.example.demo.repository.CandidateRepository;

@RestController
@CrossOrigin
public class ExcelController {

	@Autowired
	CandidateService candidateService;
	
	@Autowired
	CandidateRepository candidateRepo;
		
	//   GIVE LIST OF DETAIL BY PAGE 
	@GetMapping("getExcelReport")
	public void exportToExcel(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=CandidateDetails.xlsx";
		
		response.setHeader(headerKey, headerValue);
		List<CandidateDetail> listCandidate=candidateRepo.findAll();
		ExcelService excelExporter=new ExcelService(listCandidate);
		excelExporter.export(response);
	}
}
