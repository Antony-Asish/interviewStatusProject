package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.JobService;
import com.example.demo.model.Job;
import com.example.demo.model.RestModel.JobResponse;


@RestController
@CrossOrigin
public class JobController {

	@Autowired
	JobService jobService;
	
//  GET ALL JOB DETAIL
@GetMapping("job")
public List<Job> getAllJobDetail()
{
	return jobService.getAllJobDetail();
}
	
//   GET SINGLE JOB DETAIL
@GetMapping("job/{id}")
public ResponseEntity<JobResponse> getSingleJobDetail(@PathVariable("id") String id)
{
	return jobService.getSingleJobDetail(id);
}

//  CREATE JOBDESCRIPTION 
@PostMapping("job")
public ResponseEntity<JobResponse> createJobDescription(@RequestBody Job ob)
{    
	return jobService.createJobDescription(ob);
}

//   DELETE JOBDESCRIPTION 
@DeleteMapping("job/{id}")
public ResponseEntity<JobResponse> deletJobDescription(@PathVariable("id") String id)
{
	return jobService.deleteJobDescription(id);
}

}
