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

import com.example.demo.Service.CandidateService;
import com.example.demo.model.CandidateDetail;
import com.example.demo.model.RestModel.CandidateCount;
import com.example.demo.model.RestModel.ListOfCandidate;
import com.example.demo.model.RestModel.ResponseAddCandidate;
import com.example.demo.model.RestModel.ResponseModel;

@RestController
@CrossOrigin
public class CandidateController {

@Autowired
CandidateService candidateService;
	
//   GIVE LIST OF DETAIL BY PAGE 
@GetMapping("candidate")
public ArrayList<ListOfCandidate> page(Pageable page)
{
	return candidateService.page(page);
}

//GIVE CANDIDATE COUNT
@GetMapping("candidateCount")
public CandidateCount count()
{
return candidateService.countCandidateDetail();
}

//CANDIDATE STATUS UPDATING
@PostMapping("candidate/{id}/{status}")
public ResponseEntity<ResponseAddCandidate> candidateStatusUpdate(@PathVariable("status") String status,
		@PathVariable("id") String id)
{
 return candidateService.candidateStatusUpdate(id,status);
}

//  CANDIDATE ADDING AND UPDATING
@PostMapping("candidate")
public ResponseEntity<ResponseAddCandidate> addCandidate(@RequestBody CandidateDetail ob)
{
return candidateService.addcandidate(ob);
}

//  VIEW CANDIDATE FULL DETAIL
@GetMapping("candidate/{id}")
public CandidateDetail viewCandidateDetail(@PathVariable("id") String id)
{
return candidateService.viewCandidateDetail(id);
}

//  DELETE CANDIDATE DETAIL
@DeleteMapping("candidate/{id}")
public ResponseEntity<ResponseModel> deleteCandidate(@PathVariable("id") String id)
{
return candidateService.deleteCandidate(id);
}

}
