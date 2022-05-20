package com.example.demo.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.model.CandidateDetail;
import com.example.demo.model.ClientDetail;
import com.example.demo.model.EmployeeDetail;
import com.example.demo.model.RestModel.ListOfEmployee;

public class ExcelService {
	
	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	private List<CandidateDetail> listOfCandidate;
	private List<ClientDetail> listOfClient;
	private List<EmployeeDetail> listOfEmployee;
	private ArrayList<ListOfEmployee> listOfPanel;
	
	public ExcelService() {
		super();
	}

	//   ASSIGN LISTOFCANDIDATE DATA
	public ExcelService(List<CandidateDetail> listOfCandidate)
	{
		this.listOfCandidate=listOfCandidate;
		workBook=new XSSFWorkbook();	
	}
	
    //  ASSIGN LISTOFCLIENT DATA
	public void assignClientList(List<ClientDetail> listOfClient)
	{
		this.listOfClient=listOfClient;
		workBook=new XSSFWorkbook();	
	}
	
    //  ASSIGN LISTOFEMPLOYEE DATA
	public void assignEmployeeList(List<EmployeeDetail> listOfEmployee)
	{
		this.listOfEmployee=listOfEmployee;
		workBook=new XSSFWorkbook();	
	}
	
   //  ASSIGN LISTOFPANEL DATA
	public void assignPanelList(ArrayList<ListOfEmployee> listOfPanel) {
		this.listOfPanel=listOfPanel;
		workBook=new XSSFWorkbook();	
	}
	
    //    GET ALL CANDIDATE REPORT
	public void allCandidateReport(HttpServletResponse response) throws IOException
	{
		writeHeaderLine();
		writeDataLines();
		
		ServletOutputStream servletOutPutStream=response.getOutputStream();
		workBook.write(servletOutPutStream);
		workBook.close();
		servletOutPutStream.close();
	}
	
	//   CREATE NEW CELL AND PUT VALUE INTO CELL
	public void createCell(Row row,int columnCount,Object value,CellStyle style)
	{
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if(value instanceof Long)
		{
			cell.setCellValue((Long) value);
		}
		else if(value instanceof Date)
		{
			cell.setCellValue((Date) value);
		}
		else if(value instanceof Integer)
		{
			cell.setCellValue((Integer) value);
		}
		else if(value instanceof Boolean)
		{
			cell.setCellValue((Boolean) value);
		}
		else
		{
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	
	//  HEADER LINES WRITING
	public void writeHeaderLine() {
		sheet=workBook.createSheet("CandidateDetail");
		Row row=sheet.createRow(0);
		CellStyle style=workBook.createCellStyle();
		XSSFFont font=workBook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row,0,"Candidate Detail",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row,0,"FirstName",style);
		createCell(row,1,"LastName",style);
		createCell(row,2,"Applied Job",style);
		createCell(row,3,"DOB",style);
		createCell(row,4,"Status",style);
		createCell(row,5,"Skill",style);
		createCell(row,6,"Company",style);
		createCell(row,7,"Qualification",style);
		createCell(row,8,"Email",style);
		createCell(row,9,"Phone",style);
		createCell(row,10,"linkedIn",style);
		createCell(row,11,"Address",style);	
	}
	
	//    BODY DATA LINES WRITING
	private void writeDataLines()
	{
		int rowCount=2;
		 CellStyle style=workBook.createCellStyle();
		 XSSFFont font=workBook.createFont();
		 font.setFontHeight(14);
		 style.setFont(font);
		 
		 for(CandidateDetail candidate :listOfCandidate )
		 {
			 Row row=sheet.createRow(rowCount++);
			 int columnCount=0;
			 createCell(row,columnCount++,candidate.getFirstName(),style);
			 createCell(row,columnCount++,candidate.getLastName(),style);
			 createCell(row,columnCount++,candidate.getJob(),style);
			 if(candidate.getDob() != null)
			     createCell(row,columnCount++,candidate.getDob().toString(),style);
			 else
				 createCell(row,columnCount++,"",style);
			 createCell(row,columnCount++,candidate.getStatus(),style);
			 createCell(row,columnCount++,candidate.getSkill().toString(),style);
			 createCell(row,columnCount++,candidate.getCompany().toString(),style);
			 createCell(row,columnCount++,candidate.getQualification().toString(),style);;
			 createCell(row,columnCount++,candidate.getEmail(),style);
			 createCell(row,columnCount++,candidate.getPhone(),style);
			 createCell(row,columnCount++,candidate.getLinkedIn(),style);
			 createCell(row,columnCount++,candidate.getAddress().toString(),style);
		 }
	}
	
    //   GET HIRED CANDIDATE REPORT
	public void hiredCandidateReport(HttpServletResponse response) throws IOException
	{
		writeHeaderLine();
		writeDataLines();
		
		ServletOutputStream servletOutPutStream=response.getOutputStream();
		workBook.write(servletOutPutStream);
		workBook.close();
		servletOutPutStream.close();
	}
	
    //   GET HIRED CANDIDATE REPORT
	public void clientReport(HttpServletResponse response) throws IOException
	{
		writeHeaderLineForClient();
		writeDataLinesForClient();
		
		ServletOutputStream servletOutPutStream=response.getOutputStream();
		workBook.write(servletOutPutStream);
		workBook.close();
		servletOutPutStream.close();
	} 
	
    //    HEADER LINES WRITING FOR CLIENT DETAIL
	public void writeHeaderLineForClient() {
		sheet=workBook.createSheet("Client_Detail");
		Row row=sheet.createRow(0);
		CellStyle style=workBook.createCellStyle();
		XSSFFont font=workBook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row,0,"Candidate Detail",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row,0,"FirstName",style);
		createCell(row,1,"LastName",style);
		createCell(row,2,"Email",style);
		createCell(row,3,"Phone",style);
	}
	
    //    BODY DATA LINES WRITING FOR CLIENT DETAILS
	private void writeDataLinesForClient()
	{
		int rowCount=2;
		 CellStyle style=workBook.createCellStyle();
		 XSSFFont font=workBook.createFont();
		 font.setFontHeight(14);
		 style.setFont(font);
		 
		 for(ClientDetail client : listOfClient )
		 {
			 Row row=sheet.createRow(rowCount++);
			 int columnCount=0;
			 createCell(row,columnCount++,client.getFirstName(),style);
			 createCell(row,columnCount++,client.getLastName(),style);
			 createCell(row,columnCount++,client.getEmail(),style);
			 createCell(row,columnCount++,client.getPhone(),style);
		 }
	}

	//  GET EMPLOYEE DETAIL REPORT
	public void employeeReport(HttpServletResponse response) throws IOException {
		writeHeaderLineForEmployee();
		writeDataLinesForEmployee();
		
		ServletOutputStream servletOutPutStream=response.getOutputStream();
		workBook.write(servletOutPutStream);
		workBook.close();
		servletOutPutStream.close();
	}

    //  HEADER LINES WRITING FOR CLIENT DETAIL
	private void writeHeaderLineForEmployee() {
		sheet=workBook.createSheet("Employee_Detail");
		Row row=sheet.createRow(0);
		CellStyle style=workBook.createCellStyle();
		XSSFFont font=workBook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row,0,"Employee Detail",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row,0,"FirstName",style);
		createCell(row,1,"LastName",style);
		createCell(row,2,"UserName",style);
		createCell(row,3,"DOB",style);
		createCell(row,4,"Skill",style);
		createCell(row,5,"Role",style);
		createCell(row,6,"Department",style);
		createCell(row,7,"Company",style);
		createCell(row,8,"Qualification",style);
		createCell(row,9,"Email",style);
		createCell(row,10,"Phone",style);
		createCell(row,11,"linkedIn",style);
		createCell(row,12,"Address",style);	
	}
	
	//   BODY DATA LINES WRITING FOR EMPLOYEE DETAIL
	private void writeDataLinesForEmployee() {
		 int rowCount=2;
		 CellStyle style=workBook.createCellStyle();
		 XSSFFont font=workBook.createFont();
		 font.setFontHeight(14);
		 style.setFont(font);
		 
		 for(EmployeeDetail employee : listOfEmployee )
		 {
			 Row row=sheet.createRow(rowCount++);
			 int columnCount=0;
			 createCell(row,columnCount++,employee.getFirstName(),style);
			 createCell(row,columnCount++,employee.getLastName(),style);
			 createCell(row,columnCount++,employee.getUserName(),style);
			 if(employee.getDob() != null)
			     createCell(row,columnCount++,employee.getDob().toString(),style);
			 else
				 createCell(row,columnCount++,"",style);
			 createCell(row,columnCount++,employee.getSkill().toString(),style);
			 if(employee.getRole() != null)
				 createCell(row,columnCount++,employee.getRole().toString(),style);
			 else
				 createCell(row,columnCount++,"",style);
			 createCell(row,columnCount++,employee.getDepartment().toString(),style);
			 createCell(row,columnCount++,employee.getCompany().toString(),style);
			 if(employee.getDegree() != null)
				 createCell(row,columnCount++,employee.getDegree().toString(),style);
			 else
				 createCell(row,columnCount++,"",style);
			 createCell(row,columnCount++,employee.getEmail(),style);
			 createCell(row,columnCount++,employee.getPhone(),style);
			 createCell(row,columnCount++,employee.getLinkedIn(),style);
			 createCell(row,columnCount++,employee.getAddress().toString(),style);
		 }
	}

    //   GET EMPLOYEE DETAIL REPORT
	public void panelReport(HttpServletResponse response) throws IOException {
		writeHeaderLinesForPanel();
		writeDataLinesForPanel();
		
		ServletOutputStream servletOutPutStream=response.getOutputStream();
		workBook.write(servletOutPutStream);
		workBook.close();
		servletOutPutStream.close();
	}

    //   HEADER LINES WRITING FOR PANEL DETAIL
	private void writeHeaderLinesForPanel() {
		sheet=workBook.createSheet("Panel_Detail");
		Row row=sheet.createRow(0);
		CellStyle style=workBook.createCellStyle();
		XSSFFont font=workBook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row,0,"Panel Detail",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row,0,"FirstName",style);
		createCell(row,1,"LastName",style);
		createCell(row,2,"Gender",style);
		createCell(row,3,"Linked-In",style);
		createCell(row,4,"Department",style);
		createCell(row,5,"Email",style);
		createCell(row,6,"Phone",style);
		
	}

    //   BODY DATA LINES WRITING FOR CLIENT DETAIL
	private void writeDataLinesForPanel() {
		 int rowCount=2;
		 CellStyle style=workBook.createCellStyle();
		 XSSFFont font=workBook.createFont();
		 font.setFontHeight(14);
		 style.setFont(font);
		 
		 for(ListOfEmployee listOfPanel: listOfPanel )
		 {
			 Row row=sheet.createRow(rowCount++);
			 int columnCount=0;
			 createCell(row,columnCount++,listOfPanel.getFirstName(),style);
			 createCell(row,columnCount++,listOfPanel.getLastName(),style);
			 createCell(row,columnCount++,listOfPanel.getGender(),style);
			 createCell(row,columnCount++,listOfPanel.getLinkedIn(),style);
			 createCell(row,columnCount++,listOfPanel.getDepartment(),style);
			 createCell(row,columnCount++,listOfPanel.getEmail(),style);
			 createCell(row,columnCount++,listOfPanel.getPhone(),style);
		 }
	}
}
