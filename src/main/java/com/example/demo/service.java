package com.example.demo;

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

import com.example.demo.Response.CandidateCount;
import com.example.demo.Response.DashBoardReturn;
import com.example.demo.Response.ListOfCandidate;
import com.example.demo.Response.ListOfEmployee;
import com.example.demo.Response.ResponseAddCandidate;
import com.example.demo.Response.ResponseAddEmployee;
import com.example.demo.Response.ResponseAddPanel;
import com.example.demo.Response.ResponseModel;
import com.example.demo.dummyClass.ReturnData;
import com.example.demo.model.CandidateDetail;
import com.example.demo.model.DropDownData;
import com.example.demo.model.JobDescription;
import com.example.demo.model.EmployeeCandidate;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.PanelDetail;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.DropDownRepository;
import com.example.demo.repository.EmployeeCandidateRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.JobDescriptionRepository;
import com.example.demo.repository.PanelRepository;

@Service
public class service {
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private CandidateRepository candidateRepo;
	@Autowired
	private EmployeeCandidateRepository employeeCandidateRepo;
	@Autowired
	private PanelRepository panelRepo;
	@Autowired
	private JobDescriptionRepository jobDescriptionRepo;
	@Autowired
	private DropDownRepository dropDownRepo;
	
	public String Name;
	int rand;
	
	Random random=new Random();
	
//     EMPLOYEES LOGIN VALIDATION
		public ResponseEntity<ResponseModel> employeeLogin(EmployeeDetail ob) {
			EmployeeDetail ob1=employeeRepo.findByUserName(ob.getUserName());
			BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
			if(ob1 == null)
				return new ResponseEntity<>(new ResponseModel("UserName Incorrect"),HttpStatus.UNAUTHORIZED);
			boolean check=true;
			for(String role : ob1.getRole())
			{
			   if(role.equals(ob.getPosition()))
				 check=false;
			}
			if(check)
				return new ResponseEntity<>(new ResponseModel("You are not "+ob.getPosition()),HttpStatus.UNAUTHORIZED);
			if(passwordEncoder.matches(ob.getPassword(),ob1.getPassword()))
			{
				ReturnData data=new ReturnData(ob1.getId(),ob1.getUserName(),ob1.getEmail(),ob1.getFirstName(),ob1.getPhone());
				return new ResponseEntity<>(new ResponseModel(ob.getPosition(),data),HttpStatus.OK);
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
		
		
//	    CANDIDATE STATUS UPDATE
		public ResponseEntity<ResponseAddCandidate> candidateStatusUpdate(String id, String status) {
			CandidateDetail ob=candidateRepo.findById(id).get();
			if(ob==null)
				return new ResponseEntity<>(new ResponseAddCandidate("You give Wrong ID"),HttpStatus.BAD_REQUEST);
			else
			{
				ob.setStatus(status);
				return new ResponseEntity<>(new ResponseAddCandidate("Candidate was "+status,candidateRepo.save(ob)),HttpStatus.OK);
			}		
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
	 ListOfEmployee object=new ListOfEmployee(ob.getId(),ob.getFirstName(),ob.getEmail(),ob.getDepartment(),ob.getRole());
     result.add(object);
  }
  return result;
}

//      EMPLOYEE ADDING AND UPDATING
public ResponseEntity<ResponseAddEmployee> addemployee(EmployeeDetail ob) {
    if(ob.getId() != null)
    {
	  if(ob.getPassword().equals(ob.getCpassword()))
		{
		if(employeeRepo.existsByEmailAndIdIsNot(ob.getEmail(),ob.getId()))
			return new ResponseEntity<>(new ResponseAddEmployee("Email Already exists!"),HttpStatus.BAD_REQUEST);
		if(employeeRepo.existsByPhoneAndIdIsNot(ob.getPhone(),ob.getId()))
			return new ResponseEntity<>(new ResponseAddEmployee("Phone Number Already exists!"),HttpStatus.BAD_REQUEST);
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		String password=passwordEncoder.encode(ob.getPassword());
		ob.setPassword(password);
		ob.setCpassword("");
		return new ResponseEntity<>(new ResponseAddEmployee("Employee Detail Update Successfully",employeeRepo.save(ob)),HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseAddEmployee("Password And ConfirmPassword not same"),HttpStatus.BAD_REQUEST);
	}
	else
	{
	   if(employeeRepo.existsByEmail(ob.getEmail()))
		 return new ResponseEntity<>(new ResponseAddEmployee("Email Already exists!"),HttpStatus.BAD_REQUEST);
	   if(employeeRepo.existsByPhone(ob.getPhone()))
		 return new ResponseEntity<>(new ResponseAddEmployee("Phone Number Already exists!"),HttpStatus.BAD_REQUEST);
	do
	{
    rand = random.nextInt(10000);
	ob.setUserName(ob.getFirstName()+rand+"@icanio");
	}while(employeeRepo.existsByUserName(ob.getUserName()));
	}
	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	String password = passwordEncoder.encode(ob.getFirstName()+rand+"@icanio");
	ob.setPassword(password);
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
       return new ResponseEntity<>(new ResponseModel("Employee Detail Deleted Successfully"),HttpStatus.OK);
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

//    GIVE DATA FOR DROP DOWN
public DropDownData dropDownDetail(String name) {	
    return dropDownRepo.findById(name).get();
}

//    DROP DOWN DATA UPDATE
public DropDownData dropDownUpdate(String dropDownName, ArrayList<String> newData) {
	DropDownData ob =dropDownRepo.findById(dropDownName).get();
	ArrayList<String> ob1=new ArrayList<String>(ob.getData());
	for(String data: newData)
		ob1.add(data);
	ob.setData(ob1);
	return dropDownRepo.save(ob);
}

//      DROP DOWN DATA DELETE
public DropDownData dropDownDelete(String dropDownName, ArrayList<String> removeData) {
	DropDownData ob =dropDownRepo.findById(dropDownName).get();
	ArrayList<String> ob1=new ArrayList<String>(ob.getData());
	for(int i=0;i<ob1.size();i++)
	   for(int j=0;j<removeData.size();j++)
          if(removeData.get(j).equals(ob1.get(i)))
		     ob1.remove(i);
	ob.setData(ob1);
	return dropDownRepo.save(ob);
}

//     PANEL ADDING AND UPDATING
public ResponseEntity<ResponseAddPanel> panelAdding(PanelDetail ob) {
	if(ob.getId() == null)
	{
	    if(panelRepo.existsByUserName(ob.getUserName()))
	       return new ResponseEntity<>(new ResponseAddPanel("UserName Already Taken!"),HttpStatus.BAD_REQUEST);
	    if(panelRepo.existsByEmail(ob.getEmail()))
		   return new ResponseEntity<>(new ResponseAddPanel("Email Already Taken!"),HttpStatus.BAD_REQUEST);
	    if(panelRepo.existsByPhone(ob.getPhone()))
		   return new ResponseEntity<>(new ResponseAddPanel("Phone Number Already Taken"),HttpStatus.BAD_REQUEST);
	    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	    ob.setPassword(passwordEncoder.encode(ob.getPassword()));
	    return new ResponseEntity<>(new ResponseAddPanel("Panel Create succesfully",panelRepo.insert(ob)),HttpStatus.OK);
	}
	else
	{
		if(panelRepo.existsByUserNameAndIdIsNot(ob.getUserName(),ob.getId()))
		    return new ResponseEntity<>(new ResponseAddPanel("UserName Already Taken!"),HttpStatus.BAD_REQUEST);
		if(panelRepo.existsByEmailAndIdIsNot(ob.getEmail(),ob.getId()))
			return new ResponseEntity<>(new ResponseAddPanel("Email Already Taken!"),HttpStatus.BAD_REQUEST);
		if(panelRepo.existsByPhoneAndIdIsNot(ob.getPhone(),ob.getId()))
			return new ResponseEntity<>(new ResponseAddPanel("Phone Number Already Taken"),HttpStatus.BAD_REQUEST);
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	    ob.setPassword(passwordEncoder.encode(ob.getPassword()));
		return new ResponseEntity<>(new ResponseAddPanel("Panel Deatil Updated succesfully",panelRepo.save(ob)),HttpStatus.OK);
	}
}

//      PANEL DETAIL DELETE
public ResponseEntity<ResponseModel> deletePanel(String id) {
	if(employeeRepo.existsById(id))
	{
	   employeeRepo.deleteById(id);
	   return new ResponseEntity<>(new ResponseModel("Panel Detail Deleted Successfully"),HttpStatus.OK);
	}
	else
	   return new ResponseEntity<>(new ResponseModel("Id is Wrong"),HttpStatus.BAD_REQUEST);
}

//      ADD JOBDESCRIPTION AND UPDATE
public ResponseEntity<ResponseModel> jobDescription(JobDescription ob) {
	if(ob.getId() == null)
		return new ResponseEntity<>(new ResponseModel("New JobDescription Was Created",jobDescriptionRepo.insert(ob)),HttpStatus.OK);
	else
	    return new ResponseEntity<>(new ResponseModel("JobDescription Was Updated",jobDescriptionRepo.save(ob)),HttpStatus.OK);
}

//     DELETE JOBDISCRIPTIION
public ResponseEntity<ResponseModel> deleteJobDescription(String id) {
	if(jobDescriptionRepo.existsById(id))
	{
		jobDescriptionRepo.deleteById(id);
		return new ResponseEntity<>(new ResponseModel("JobDescription was deleted"),HttpStatus.OK);
	}
	else
        return new ResponseEntity<>(new ResponseModel("Id is Wrong"),HttpStatus.BAD_REQUEST);
}


    //    TRAINING PURPOSE
}