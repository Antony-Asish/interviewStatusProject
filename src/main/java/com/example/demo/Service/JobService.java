package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Job;
import com.example.demo.model.RestModel.JobResponse;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.repository.JobRepository;


@Service
public class JobService {

	@Autowired
	private JobRepository jobDescriptionRepo;
	
//  ADD JOBDESCRIPTION AND UPDATE
public ResponseEntity<JobResponse> createJobDescription(Job ob) {
     if(ob.getId() == null)
         return new ResponseEntity<>(new JobResponse("New JobDescription Was Created",jobDescriptionRepo.insert(ob)),HttpStatus.OK);
     else
        return new ResponseEntity<>(new JobResponse("JobDescription Was Updated",jobDescriptionRepo.save(ob)),HttpStatus.OK);
 }

//  DELETE JOBDISCRIPTIION
 public ResponseEntity<ResponseModel> deleteJobDescription(String id) {
     if(jobDescriptionRepo.existsById(id))
     {
        jobDescriptionRepo.deleteById(id);
        return new ResponseEntity<>(new ResponseModel("JobDescription was deleted"),HttpStatus.OK);
     }
     else
        return new ResponseEntity<>(new ResponseModel("Id is Wrong"),HttpStatus.BAD_REQUEST);
  }
}
