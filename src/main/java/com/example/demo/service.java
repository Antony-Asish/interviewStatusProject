package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Response.CandidateCount;
import com.example.demo.Response.DashBoardReturn;
import com.example.demo.Response.ListOfCandidate;
import com.example.demo.Response.ListOfEmployee;
import com.example.demo.Response.ResponseAddCandidate;
import com.example.demo.Response.ResponseAddEmployee;
import com.example.demo.Response.ResponseModel;
import com.example.demo.dummyClass.DepartmentDetail;
import com.example.demo.dummyClass.ReturnData;
import com.example.demo.model.CandidateDetail;
import com.example.demo.model.EmployeeCandidate;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.EmployeeCandidateRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class service {
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private CandidateRepository candidateRepo;
	@Autowired
	private EmployeeCandidateRepository employeeCandidateRepo;
	
	public String Name;
	
	Random random=new Random();
	
	
//     EMPLOYEES LOGIN VALIDATION
		public ResponseEntity<ResponseModel> employeeLogin(EmployeeDetail ob) {
			EmployeeDetail ob1=employeeRepo.findByUserName(ob.getUserName());
			if(ob1 == null)
				return new ResponseEntity<>(new ResponseModel("Email Incorrect"),HttpStatus.UNAUTHORIZED);
				if(ob1.getPassword().equals(ob.getPassword()))
				{
				   ReturnData data=new ReturnData(ob1.getId(),ob1.getUserName(),ob1.getEmail(),ob1.getFirstName(),ob1.getPhone());
				   return new ResponseEntity<>(new ResponseModel(ob1.getRoll(),data),HttpStatus.OK);
				}
				else
			return new ResponseEntity<>(new ResponseModel("PassWord Incorrect"),HttpStatus.UNAUTHORIZED);
		}

//     DASHBORD CALCULATION 
		public ArrayList<DashBoardReturn> dashBoard() {
			ArrayList<DashBoardReturn> obj=new ArrayList<DashBoardReturn>();
			List<CandidateDetail> candidate=candidateRepo.findAll();
			DashBoardReturn object=new DashBoardReturn("Hired",candidateRepo.countByStatus("hired"));
			obj.add(object);
			object=new DashBoardReturn("Rejected",candidateRepo.countByStatus("rejected"));
			obj.add(object);
			object=new DashBoardReturn("WaitingList",candidateRepo.countByStatus("waitingList"));
			obj.add(object);
			object=new DashBoardReturn("Progress",candidateRepo.countByStatus("progress"));
			obj.add(object);
		    object=new DashBoardReturn("TotalCandidate",candidate.size());
			obj.add(object);
			return obj;
		}
			
//       CANDIDATE DETAIL COUNT
		public CandidateCount countCandidateDetail() {
			List<CandidateDetail> ob=candidateRepo.findAll();
			CandidateCount ob1=new CandidateCount(ob.size());
			return ob1;
		}
		
 //      DISPLAY CANDIDATE LIST DETAIL BY PAGE
		public ArrayList<ListOfCandidate> page(Pageable page) {
			Page<CandidateDetail> list=candidateRepo.findAll(page);
			ArrayList<ListOfCandidate> result=new ArrayList<ListOfCandidate>();
			for(CandidateDetail ob:list)
			{
				ListOfCandidate object=new ListOfCandidate(ob.getId(),ob.getFirstName(),ob.getEmail()
						,ob.getPhone(),ob.getSkill(),ob.getJob());
			    result.add(object);
			}
			return result;
		}
		
//      JOB DETAIL FOR DROP DOWN
public DepartmentDetail jobDetail() {
String[] job={"React Js","Api","React Native","DevOps"};
return new DepartmentDetail(job);
}
		
//      CANDIDATE ADDING AND CANDIDATE UPDATE
	public ResponseEntity<ResponseAddCandidate> addcandidate(CandidateDetail ob)
	{
		if(ob.getId() != null)
		{
			if(candidateRepo.existsByEmailAndIdIsNot(ob.getEmail(),ob.getId()))
				 return new ResponseEntity<>(new ResponseAddCandidate("Email Already Exist!"),HttpStatus.BAD_REQUEST);
			if(candidateRepo.existsByPhoneAndIdIsNot(ob.getPhone(),ob.getId()))
			return new ResponseEntity<>(new ResponseAddCandidate("Phone number Already Exist!"),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new ResponseAddCandidate("candidateDetail Updated",candidateRepo.save(ob)),HttpStatus.OK);	
		}  
		else {
			if(candidateRepo.existsByEmail(ob.getEmail()))
				 return new ResponseEntity<>(new ResponseAddCandidate("Email Already Exist!"),HttpStatus.BAD_REQUEST);
			if(candidateRepo.existsByPhone(ob.getPhone()))
			return new ResponseEntity<>(new ResponseAddCandidate("Phone number Already Exist!"),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new ResponseAddCandidate("CandidateDetail Add Successfully!",candidateRepo.insert(ob)),HttpStatus.OK);
		}
		}
	
//     CANDIDATE DETAIL DELETED
public ResponseEntity<ResponseModel> deleteCandidate(String id) {
	if(candidateRepo.existsById(id))
	{
	candidateRepo.deleteById(id);
	return new ResponseEntity<>(new ResponseModel("Candidate Detail Deleted"),HttpStatus.OK);
	}
	else
		return new ResponseEntity<>(new ResponseModel("Id is Wrong"),HttpStatus.BAD_REQUEST);
}

//     VIEW CANDIDATE FULL DETAIL
public CandidateDetail viewCandidateDetail(String id) {
return candidateRepo.findById(id).get();
}

//      DISPLAY EMPLOYEE LIST DETAIL BY PAGE
public ArrayList<ListOfEmployee> employeeList(Pageable page) {
Page<EmployeeDetail> list=employeeRepo.findAll(page);
ArrayList<ListOfEmployee> result=new ArrayList<ListOfEmployee>();
for(EmployeeDetail ob:list)
{
	ListOfEmployee object=new ListOfEmployee(ob.getId(),ob.getFirstName(),ob.getEmail(),ob.getDepartment(),ob.getRoll());
 result.add(object);
}
return result;
}

//      EMPLOYEE ADDING AND UPDATING
public ResponseEntity<ResponseAddEmployee> addemployee(EmployeeDetail ob) {
	if(ob.getId() != null)
	{
		if(employeeRepo.existsByEmailAndIdIsNot(ob.getEmail(),ob.getId()))
			return new ResponseEntity<>(new ResponseAddEmployee("Email Already exists!"),HttpStatus.BAD_REQUEST);
		if(employeeRepo.existsByPhoneAndIdIsNot(ob.getPhone(),ob.getId()))
			return new ResponseEntity<>(new ResponseAddEmployee("Phone Number Already exists!"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new ResponseAddEmployee("Employee Detail Update Successfully",employeeRepo.save(ob)),HttpStatus.OK);
	}
	else
	{
	int rand;
	if(employeeRepo.existsByEmail(ob.getEmail()))
		return new ResponseEntity<>(new ResponseAddEmployee("Email Already exists!"),HttpStatus.BAD_REQUEST);
	if(employeeRepo.existsByPhone(ob.getPhone()))
		return new ResponseEntity<>(new ResponseAddEmployee("Phone Number Already exists!"),HttpStatus.BAD_REQUEST);
	do
	{
    rand = random.nextInt(10000);
	ob.setUserName(ob.getFirstName()+rand+"@icanio");
	ob.setPassword(ob.getFirstName()+rand+"@icanio");
	}while(employeeRepo.existsByUserName(ob.getUserName()));
	}
	return new ResponseEntity<>(new ResponseAddEmployee("New Employee's UserId is "+ob.getUserName(),employeeRepo.insert(ob)),HttpStatus.OK);
}

//       VIEW EMPLOYEE FULL DETAIL
public EmployeeDetail viewEmployeeDetail(String id) {
return employeeRepo.findById(id).get();
}

//       DELETE EMPLOYEE DETAIL
public ResponseEntity<ResponseModel> deleteEmployee(String id) {
if(employeeRepo.existsById(id))
{
employeeRepo.deleteById(id);
return new ResponseEntity<>(new ResponseModel("Employee Detail Deleted"),HttpStatus.OK);
}
else
	return new ResponseEntity<>(new ResponseModel("Id is Wrong"),HttpStatus.BAD_REQUEST);
}

//       ADMIN GIVE SOME CANDIDATE TO EMPLOYEE FOR INTERVIEW
public EmployeeCandidate employeeCandidate(EmployeeCandidate ob) {
	return employeeCandidateRepo.save(ob);
}

//       EMPLOYEE VIEWING HIS INTERVIEW CANDIDATE'S DETAILS
public ArrayList<ListOfCandidate> employeeView(String id) {
	EmployeeCandidate object=employeeCandidateRepo.findByEmpId(id);
	ListOfCandidate list=new ListOfCandidate();
	CandidateDetail ob=new CandidateDetail();
	ArrayList<ListOfCandidate> result=new ArrayList<ListOfCandidate>();
	for(int i=0;i<object.getCandidateId().size();i++)
	{
		ob=candidateRepo.findById(object.getCandidateId().get(i)).get();
		list=new ListOfCandidate(ob.getId(),ob.getFirstName(),ob.getEmail()
				,ob.getPhone(),ob.getSkill(),ob.getJob());
	    result.add(list);
	}
	return result;
}

    //    TRAINING PURPOSE

//      GIVE CANDIDATE LIST
public List<CandidateDetail> getalldetail() {
return candidateRepo.findAll();
}


//      USER EMAIL CHECK FOR  UPDATE PASSWORD
public String emailChecking(String name) {
	EmployeeDetail ob=employeeRepo.findByUserName(name);
	if(ob == null)
		return "UserName is incorrect";
	else
	{
		Name=name;
		int otp=random.nextInt(10000);
		ob.setOtp(otp);
		employeeRepo.save(ob);
		return "Access for change password";
	}
}

//       USER PASSSWORD UPDATE USING OTP 
public String updatePassword(EmployeeDetail ob) {
	EmployeeDetail ob1=employeeRepo.findByUserName(Name);
	if(ob.getCpassword().equals(ob.getPassword())) {
		if(ob1.getOtp() == ob.getOtp())
		{
			ob1.setPassword(ob.getPassword());
			ob1.setOtp(0);
			employeeRepo.save(ob1);
			return "Password Update Successfully ";
		}
		else return "OTP is wrong";
	}
	return "confirm Password is wrong";
}
}