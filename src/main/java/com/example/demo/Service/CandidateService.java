package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.CandidateDetail;
import com.example.demo.model.RestModel.CandidateCount;
import com.example.demo.model.RestModel.ListOfCandidate;
import com.example.demo.model.RestModel.ResponseAddCandidate;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.repository.CandidateRepository;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepo;
	
//      DISPLAY CANDIDATE LIST DETAIL BY PAGE
	public ArrayList<ListOfCandidate> page(Pageable page) {
		Page<CandidateDetail> allCandidateList=candidateRepo.findAll(page);
		ArrayList<ListOfCandidate> pageByCandidateList=new ArrayList<ListOfCandidate>();
		for(CandidateDetail ob:allCandidateList)
			pageByCandidateList.add(new ListOfCandidate(ob.getId(),ob.getFirstName(),ob.getStatus()
					,ob.getPhone(),ob.getJob()));
		return pageByCandidateList;
	}
	
//     CANDIDATE DETAIL COUNT
	public CandidateCount countCandidateDetail() {
		List<CandidateDetail> ob=candidateRepo.findAll();
		CandidateCount ob1=new CandidateCount(ob.size());
		return ob1;
	}
	
//   CANDIDATE STATUS UPDATE
	public ResponseEntity<ResponseAddCandidate> candidateStatusUpdate(String id, String status) {
		boolean check=false;
		switch(status)
		{
		case "hired":
		{
			check=true;
			break;
		}
		case "rejected":
		{
			check=true;
			break;
		}
		case "waitingList":
		{
			check=true;
			break;
		}	
		case "progress":
		{
			check=true;
			break;
		}		
		}
		if(check)
		{
			CandidateDetail candidateDetail=candidateRepo.findById(id).get();
			if(candidateDetail==null)
				return new ResponseEntity<>(new ResponseAddCandidate("You give Wrong ID"),HttpStatus.BAD_REQUEST);
			else
			{
				candidateDetail.setStatus(status);
				return new ResponseEntity<>(new ResponseAddCandidate("Candidate was "+status,candidateRepo.save(candidateDetail)),HttpStatus.OK);
			}	
		}	
		else
			return new ResponseEntity<>(new ResponseAddCandidate("Your Status Input is wrong"),HttpStatus.BAD_REQUEST);
	}
	
	
// CANDIDATE ADDING AND CANDIDATE UPDATE
public ResponseEntity<ResponseAddCandidate> addcandidate(CandidateDetail candidateDetail)
{
	if(candidateDetail.getId() != null)
	{
		if(candidateRepo.existsByEmailAndIdIsNot(candidateDetail.getEmail(),candidateDetail.getId()))
			 return new ResponseEntity<>(new ResponseAddCandidate("Email Already Exist!"),HttpStatus.BAD_REQUEST);
		if(candidateRepo.existsByPhoneAndIdIsNot(candidateDetail.getPhone(),candidateDetail.getId()))
		    return new ResponseEntity<>(new ResponseAddCandidate("Phone number Already Exist!"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new ResponseAddCandidate("candidateDetail Updated",candidateRepo.save(candidateDetail)),HttpStatus.OK);	
	}  
	else {
		if(candidateRepo.existsByEmail(candidateDetail.getEmail()))
			 return new ResponseEntity<>(new ResponseAddCandidate("Email Already Exist!"),HttpStatus.BAD_REQUEST);
		if(candidateRepo.existsByPhone(candidateDetail.getPhone()))
		     return new ResponseEntity<>(new ResponseAddCandidate("Phone number Already Exist!"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new ResponseAddCandidate("CandidateDetail Add Successfully!",candidateRepo.insert(candidateDetail)),HttpStatus.OK);
	}
	}

//CANDIDATE DETAIL DELETED
public ResponseEntity<ResponseModel> deleteCandidate(String id) {
if(candidateRepo.existsById(id))
{
  candidateRepo.deleteById(id);
  return new ResponseEntity<>(new ResponseModel("Candidate Detail Deleted"),HttpStatus.OK);
}
else
	return new ResponseEntity<>(new ResponseModel("Id is Wrong"),HttpStatus.BAD_REQUEST);
}

//VIEW CANDIDATE FULL DETAIL
public CandidateDetail viewCandidateDetail(String id) {
return candidateRepo.findById(id).get();
}

	
}
