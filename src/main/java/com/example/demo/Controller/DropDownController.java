package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.DropDownService;
import com.example.demo.model.DropDownData;
import com.example.demo.model.RestModel.DropDownEmployeeData;

@RestController
@CrossOrigin
public class DropDownController {

	@Autowired
	DropDownService dropDownService;
	
//  GIVE DROP DOWN DATA 
@GetMapping("dropDown/{name}")
public DropDownData dropDownDetail(@PathVariable("name") String name)
{
return dropDownService.dropDownDetail(name);
}

//  CREATE NEW DROP DOWN DATA
@PostMapping("newDropDown")
public DropDownData newDropDown(@RequestBody DropDownData ob)
{
	return dropDownService.newDropDownCreate(ob);
}

//  DROP DOWN DATA ADDING
@PostMapping("dropDown")
public DropDownData dropDownDataAdd(@RequestBody DropDownData ob)
{
	return dropDownService.dropDownUpdate(ob);
}

//  DROP DOWN DATA DELETEING
@DeleteMapping("dropDown")
public DropDownData dropDownDelete(@RequestBody DropDownData ob)
{
	return dropDownService.dropDownDelete(ob);
}

//  GIVE EMPLOYEELIST 
@GetMapping("dropDown/employee")
public ArrayList<DropDownEmployeeData> dropDownEmployeeList()
{
return dropDownService.dropDownEmployeeList();
}

}
