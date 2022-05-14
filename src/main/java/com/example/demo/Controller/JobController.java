package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.JobService;
import com.example.demo.model.Job;
import com.example.demo.model.RestModel.JobResponse;
import com.example.demo.model.RestModel.ResponseModel;

@RestController
@CrossOrigin
public class JobController {

	@Autowired
	JobService jobService;
	
//  CREATE JOBDESCRIPTION 
@PostMapping("job")
public ResponseEntity<JobResponse> createJobDescription(@RequestBody Job ob)
{    
	return jobService.createJobDescription(ob);
}

//   DELETE JOBDESCRIPTION 
@DeleteMapping("job/{id}")
public ResponseEntity<ResponseModel> deletJobDescription(@PathVariable("id") String id)
{
	return jobService.deleteJobDescription(id);
}

}
