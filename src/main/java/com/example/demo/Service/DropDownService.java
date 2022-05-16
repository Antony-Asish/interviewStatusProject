package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DropDownData;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.RestModel.DropDownEmployeeData;
import com.example.demo.repository.DropDownRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class DropDownService {

	@Autowired
	private DropDownRepository dropDownRepo;
	@Autowired
	private EmployeeRepository employeeRepo;

//  GIVE DATA FOR DROP DOWN
public DropDownData dropDownDetail(String name) {
  return dropDownRepo.findById(name).get();
}

//  CREATE NEW DROP DOWN
public DropDownData newDropDownCreate(DropDownData newDropDown) {
   return dropDownRepo.save(newDropDown);
}

//  DROP DOWN DATA UPDATE
public DropDownData dropDownUpdate(DropDownData dropDownData) {
	DropDownData ob =dropDownRepo.findById(dropDownData.getDropDownName()).get();
	ArrayList<String> dataBaseData=new ArrayList<String>(ob.getData());
	for(String data: dropDownData.getData())
		dataBaseData.add(data);
	ob.setData(dataBaseData);
	return dropDownRepo.save(ob);
}

//    DROP DOWN DATA DELETE
public DropDownData dropDownDelete(DropDownData dropDownData) {
	DropDownData ob =dropDownRepo.findById(dropDownData.getDropDownName()).get();
	ArrayList<String> dataBaseData=new ArrayList<String>(ob.getData());
	int i,j;
	String data,DBdata;
	for(i=0;i<dropDownData.getData().size();i++)
	{
		data=dropDownData.getData().get(i);
		for(j=0;j<dataBaseData.size();j++)
		{
			DBdata=dataBaseData.get(j);
			if(data.equals(DBdata))
				dataBaseData.remove(j);
		}
	}
	ob.setData(dataBaseData);
	return dropDownRepo.save(ob);
}

//    EMPLOYEE LIST DROP DOWN
public ArrayList<DropDownEmployeeData> dropDownEmployeeList() {
	List<EmployeeDetail> employeeList=employeeRepo.findAll();
	ArrayList<DropDownEmployeeData> returnEmployeeListData=new ArrayList<DropDownEmployeeData>();
	for(EmployeeDetail employee : employeeList)
	{
		returnEmployeeListData.add(new DropDownEmployeeData(employee.getId(),employee.getFirstName()));
	}
	return returnEmployeeListData;
}
}
