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
import com.example.demo.Response.JobDescriptionResponse;
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
		public ResponseEntity<ResponseModel> employeeLogin(EmployeeDetail employeeDetail) {
			EmployeeDetail DBemployeeDetail=employeeRepo.findByUserName(employeeDetail.getUserName());
			BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
			if(DBemployeeDetail == null)
				return new ResponseEntity<>(new ResponseModel("UserName Incorrect"),HttpStatus.UNAUTHORIZED);
			boolean check=true;
			for(String role : DBemployeeDetail.getRole())
			{
			   if(role.equals(employeeDetail.getPosition()))
				 check=false;
			}
			if(check)
				return new ResponseEntity<>(new ResponseModel("You are not "+employeeDetail.getPosition()),HttpStatus.UNAUTHORIZED);
			if(passwordEncoder.matches(employeeDetail.getPassword(),DBemployeeDetail.getPassword()))
			{
				ReturnData data=new ReturnData(DBemployeeDetail.getId(),DBemployeeDetail.getUserName(),DBemployeeDetail.getEmail(),DBemployeeDetail.getFirstName(),DBemployeeDetail.getPhone());
				return new ResponseEntity<>(new ResponseModel(employeeDetail.getPosition(),data),HttpStatus.OK);
		    }
			else
			return new ResponseEntity<>(new ResponseModel("PassWord Incorrect"),HttpStatus.UNAUTHORIZED);
		}

//     DASHBORD CALCULATION 
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
			
//       CANDIDATE DETAIL COUNT
		public CandidateCount countCandidateDetail() {
			List<CandidateDetail> ob=candidateRepo.findAll();
			CandidateCount ob1=new CandidateCount(ob.size());
			return ob1;
		}
		
 //      DISPLAY CANDIDATE LIST DETAIL BY PAGE
		public ArrayList<ListOfCandidate> page(Pageable page) {
			Page<CandidateDetail> allCandidateList=candidateRepo.findAll(page);
			ArrayList<ListOfCandidate> pageByCandidateList=new ArrayList<ListOfCandidate>();
			for(CandidateDetail ob:allCandidateList)
				pageByCandidateList.add(new ListOfCandidate(ob.getId(),ob.getFirstName(),ob.getEmail()
						,ob.getPhone(),ob.getSkill(),ob.getJob()));
			return pageByCandidateList;
		}
		
		
//	    CANDIDATE STATUS UPDATE
		public ResponseEntity<ResponseAddCandidate> candidateStatusUpdate(String id, String status) {
			CandidateDetail candidateDetail=candidateRepo.findById(id).get();
			if(candidateDetail==null)
				return new ResponseEntity<>(new ResponseAddCandidate("You give Wrong ID"),HttpStatus.BAD_REQUEST);
			else
			{
				candidateDetail.setStatus(status);
				return new ResponseEntity<>(new ResponseAddCandidate("Candidate was "+status,candidateRepo.save(candidateDetail)),HttpStatus.OK);
			}		
		}
		
		
//      CANDIDATE ADDING AND CANDIDATE UPDATE
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
         Page<EmployeeDetail> employeeList=employeeRepo.findAll(page);
         ArrayList<ListOfEmployee> pageByEmployeeList=new ArrayList<ListOfEmployee>();
         for(EmployeeDetail ob:employeeList)
	         pageByEmployeeList.add(new ListOfEmployee(ob.getId(),ob.getFirstName(),ob.getEmail(),ob.getDepartment(),ob.getRole()));
         return pageByEmployeeList;
    }

//      EMPLOYEE ADDING AND UPDATING
public ResponseEntity<ResponseAddEmployee> addemployee(EmployeeDetail employeeDetail) {
    if(employeeDetail.getId() != null)
    {
	  if(employeeDetail.getPassword().equals(employeeDetail.getCpassword()))
		{
		if(employeeRepo.existsByEmailAndIdIsNot(employeeDetail.getEmail(),employeeDetail.getId()))
			return new ResponseEntity<>(new ResponseAddEmployee("Email Already exists!"),HttpStatus.BAD_REQUEST);
		if(employeeRepo.existsByPhoneAndIdIsNot(employeeDetail.getPhone(),employeeDetail.getId()))
			return new ResponseEntity<>(new ResponseAddEmployee("Phone Number Already exists!"),HttpStatus.BAD_REQUEST);
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
	   if(employeeRepo.existsByEmail(employeeDetail.getEmail()))
		 return new ResponseEntity<>(new ResponseAddEmployee("Email Already exists!"),HttpStatus.BAD_REQUEST);
	   if(employeeRepo.existsByPhone(employeeDetail.getPhone()))
		 return new ResponseEntity<>(new ResponseAddEmployee("Phone Number Already exists!"),HttpStatus.BAD_REQUEST);
	do
	{
    rand = random.nextInt(10000);
    employeeDetail.setUserName(employeeDetail.getFirstName()+rand+"@icanio");
	}while(employeeRepo.existsByUserName(employeeDetail.getUserName()));
	}
	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	String password = passwordEncoder.encode(employeeDetail.getFirstName()+rand+"@icanio");
	employeeDetail.setPassword(password);
	return new ResponseEntity<>(new ResponseAddEmployee("New Employee's UserId is "+employeeDetail.getUserName(),employeeRepo.insert(employeeDetail)),HttpStatus.OK);
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

//    GIVE DATA FOR DROP DOWN
public DropDownData dropDownDetail(String name) {	
    return dropDownRepo.findById(name).get();
}

//    CREATE NEW DROP DOWN
public DropDownData newDropDownCreate(DropDownData newDropDown) {
     return dropDownRepo.save(newDropDown);
}

//    DROP DOWN DATA UPDATE
public DropDownData dropDownUpdate(String dropDownName, ArrayList<String> newData) {
	DropDownData ob =dropDownRepo.findById(dropDownName).get();
	ArrayList<String> dataBaseData=new ArrayList<String>(ob.getData());
	for(String data: newData)
		dataBaseData.add(data);
	ob.setData(dataBaseData);
	return dropDownRepo.save(ob);
}

//      DROP DOWN DATA DELETE
public DropDownData dropDownDelete(String dropDownName, ArrayList<String> removeData) {
	DropDownData ob =dropDownRepo.findById(dropDownName).get();
	ArrayList<String> dataBaseData=new ArrayList<String>(ob.getData());
	int i=0;
	for(String role : removeData)
		for(String DBrole : dataBaseData)
			{
			if(role.equals(DBrole))
				dataBaseData.remove(i);
			i++;
			}			
	ob.setData(dataBaseData);
	return dropDownRepo.save(ob);
}

//     ADD JOBDESCRIPTION AND UPDATE
   public ResponseEntity<JobDescriptionResponse> createJobDescription(JobDescription ob) {
        if(ob.getId() == null)
            return new ResponseEntity<>(new JobDescriptionResponse("New JobDescription Was Created",jobDescriptionRepo.insert(ob)),HttpStatus.OK);
        else
           return new ResponseEntity<>(new JobDescriptionResponse("JobDescription Was Updated",jobDescriptionRepo.save(ob)),HttpStatus.OK);
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


///      MY ADDDITIONAL WORK 


//      ADMIN GIVE SOME CANDIDATE TO EMPLOYEE FOR INTERVIEW
public EmployeeCandidate employeeCandidate(EmployeeCandidate ob) {
return employeeCandidateRepo.save(ob);
}

//      EMPLOYEE VIEWING HIS INTERVIEW CANDIDATE'S DETAILS
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

    //    TRAINING PURPOSE
}