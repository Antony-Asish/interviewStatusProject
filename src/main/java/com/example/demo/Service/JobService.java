package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.Job;
import com.example.demo.model.RestModel.JobResponse;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.JobRepository;


@Service
public class JobService {

	@Autowired
	private JobRepository jobRepo;
	@Autowired
	private EmployeeRepository employeeRepo;
	
	
//    GET ALL JOB DETAIL
public List<Job> getAllJobDetail() {
	return jobRepo.findAll();
}
	
//    GET SINGLE JOB DETAIL
public ResponseEntity<JobResponse> getSingleJobDetail(String id) {
	if(jobRepo.existsById(id))
	   return new ResponseEntity<>(new JobResponse("JobDescription Was Updated",jobRepo.findById(id).get()),HttpStatus.OK);
	else
	   return new ResponseEntity<>(new JobResponse("Job Not Found"),HttpStatus.BAD_REQUEST);
}
	
//    ADD JOBDESCRIPTION AND UPDATE
public ResponseEntity<JobResponse> createJobDescription(Job job) {
     if(job.getId() == null)
     {
    	 EmployeeDetail employeeDetail=employeeRepo.findById(job.getEmployeeId()).get();
    	 ArrayList<String> newRole= employeeDetail.getRole();
    	 newRole.add("panel");
    	 employeeDetail.setRole(newRole);
    	 employeeRepo.save(employeeDetail);
         return new ResponseEntity<>(new JobResponse("New JobDescription Was Created",jobRepo.insert(job)),HttpStatus.OK);
     }
     else
        return new ResponseEntity<>(new JobResponse("JobDescription Was Updated",jobRepo.save(job)),HttpStatus.OK);
 }

//    DELETE JOBDISCRIPTIION
 public ResponseEntity<JobResponse> deleteJobDescription(String id) {
     if(jobRepo.existsById(id))
     {
        jobRepo.deleteById(id);
        return new ResponseEntity<>(new JobResponse("JobDescription was deleted"),HttpStatus.OK);
     }
     else
        return new ResponseEntity<>(new JobResponse("Id is Wrong"),HttpStatus.BAD_REQUEST);
  }

}
