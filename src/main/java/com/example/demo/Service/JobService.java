package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.Job;
import com.example.demo.model.RestModel.JobResponse;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.JobRepository;


@Service
public class JobService {

	@Autowired
	private JobRepository jobDescriptionRepo;
	@Autowired
	private EmployeeRepository employeeRepo;
	
//  ADD JOBDESCRIPTION AND UPDATE
public ResponseEntity<JobResponse> createJobDescription(Job job) {
     if(job.getId() == null)
     {
    	 EmployeeDetail employeeDetail=employeeRepo.findById(job.getEmployeeId()).get();
    	 ArrayList<String> newRole= employeeDetail.getRole();
    	 newRole.add("panel");
    	 employeeDetail.setRole(newRole);
    	 employeeRepo.save(employeeDetail);
         return new ResponseEntity<>(new JobResponse("New JobDescription Was Created",jobDescriptionRepo.insert(job)),HttpStatus.OK);
     }
     else
        return new ResponseEntity<>(new JobResponse("JobDescription Was Updated",jobDescriptionRepo.save(job)),HttpStatus.OK);
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
