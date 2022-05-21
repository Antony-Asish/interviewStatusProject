package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.CandidateDetail;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.RestModel.DashBoardReturn;
import com.example.demo.model.RestModel.ListOfEmployee;
import com.example.demo.model.RestModel.ResponseAddEmployee;
import com.example.demo.model.RestModel.ResponseModel;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private CandidateRepository candidateRepo;
	
	int rand;
	Random random=new Random();
	
//  DASHBORD CALCULATION 
		public ArrayList<DashBoardReturn> dashBoard() {
			ArrayList<DashBoardReturn> dashBoard=new ArrayList<DashBoardReturn>();
			List<CandidateDetail> candidateList=candidateRepo.findAll();
			dashBoard.add(new DashBoardReturn("Hired",candidateRepo.countByStatus("hired")));
			dashBoard.add(new DashBoardReturn("Rejected",candidateRepo.countByStatus("rejected")));
			dashBoard.add(new DashBoardReturn("WaitingList",candidateRepo.countByStatus("waitingList")));
			dashBoard.add(new DashBoardReturn("Progress",candidateRepo.countByStatus("progress")));
		    dashBoard.add(new DashBoardReturn("TotalCandidate",candidateList.size()));
			return dashBoard;
		}
	
//    GET REPORT USING DASHBOARD DETAIL
	public List<CandidateDetail> dashBoardReport(String statusName) {
		return candidateRepo.findByStatus(statusName);
	}
		
//  DISPLAY EMPLOYEE LIST DETAIL BY PAGE
  public ArrayList<ListOfEmployee> employeeList(Pageable page) {
       Page<EmployeeDetail> employeeList=employeeRepo.findAll(page);
       ArrayList<ListOfEmployee> pageByEmployeeList=new ArrayList<ListOfEmployee>();
       for(EmployeeDetail ob:employeeList)
           pageByEmployeeList.add(new ListOfEmployee(ob.getId(),ob.getFirstName(),ob.getLastName(),ob.getEmail(),
        		   ob.getDepartment(),ob.getLinkedIn(),ob.getPhone(),ob.getRole()));
      return pageByEmployeeList;
   }

   //  EMPLOYEE ADDING AND UPDATING
   public ResponseEntity<ResponseAddEmployee> addemployee(EmployeeDetail employeeDetail) {
     if(employeeDetail.getId() != null)
     {
         if(employeeDetail.getPassword().equals(employeeDetail.getCpassword()))
	     {
	        if(employeeRepo.existsByEmailAndIdIsNot(employeeDetail.getEmail(),employeeDetail.getId()))
		       return new ResponseEntity<>(new ResponseAddEmployee("Email Already exists!"),HttpStatus.BAD_REQUEST);
	        if(employeeRepo.existsByPhoneAndIdIsNot(employeeDetail.getPhone(),employeeDetail.getId()))
		       return new ResponseEntity<>(new ResponseAddEmployee("Phone Number Already exists!"),HttpStatus.BAD_REQUEST);
	        if(employeeRepo.existsByUserNameAndIdIsNot(employeeDetail.getUserName(),employeeDetail.getId()))
			       return new ResponseEntity<>(new ResponseAddEmployee("UserName Already exists!"),HttpStatus.BAD_REQUEST);
        	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	        String password=passwordEncoder.encode(employeeDetail.getPassword());
	        employeeDetail.setPassword(password);
	        employeeDetail.setCpassword(null);
	        return new ResponseEntity<>(new ResponseAddEmployee("Employee Detail Update Successfully",employeeRepo.save(employeeDetail)),HttpStatus.OK);
	     }
	return new ResponseEntity<>(new ResponseAddEmployee("Password And ConfirmPassword not same"),HttpStatus.BAD_REQUEST);
    }
    else
    {
        if(employeeRepo.existsByUserName(employeeDetail.getUserName()))
	         return new ResponseEntity<>(new ResponseAddEmployee("UserName Already exists!"),HttpStatus.BAD_REQUEST);
        if(employeeRepo.existsByEmail(employeeDetail.getEmail()))
	         return new ResponseEntity<>(new ResponseAddEmployee("Email Already exists!"),HttpStatus.BAD_REQUEST);
        if(employeeRepo.existsByPhone(employeeDetail.getPhone()))
	         return new ResponseEntity<>(new ResponseAddEmployee("Phone Number Already exists!"),HttpStatus.BAD_REQUEST);
    }
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    String password = passwordEncoder.encode(employeeDetail.getUserName());
    employeeDetail.setPassword(password);
    return new ResponseEntity<>(new ResponseAddEmployee("New EmployeeDetail was Created",employeeRepo.insert(employeeDetail)),HttpStatus.OK);
    }

    //   VIEW EMPLOYEE FULL DETAIL
    public EmployeeDetail viewEmployeeDetail(String id) {
        return employeeRepo.findById(id).get();
    }

    //   DELETE EMPLOYEE DETAIL
    public ResponseEntity<ResponseModel> deleteEmployee(String id) {
      if(employeeRepo.existsById(id))
      {
        employeeRepo.deleteById(id);
        return new ResponseEntity<>(new ResponseModel("Employee Detail Deleted Successfully"),HttpStatus.OK);
      }
      else
        return new ResponseEntity<>(new ResponseModel("Id is Wrong"),HttpStatus.BAD_REQUEST);
   }

}
