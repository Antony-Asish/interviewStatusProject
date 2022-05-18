package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.CandidateService;

@RestController
@CrossOrigin
public class ExcelController {

	@Autowired
	CandidateService candidateService;
		
	//   GIVE LIST OF DETAIL BY PAGE 
	@GetMapping("getExcelReport")
	public void excelImport()
	{
	}

}
