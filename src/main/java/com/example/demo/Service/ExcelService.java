package com.example.demo.Service;

import java.io.IOException;
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

public class ExcelService {
	
	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	private List<CandidateDetail> listOfCandidate;
	private List<ClientDetail> listOfClient;
	
	
	public ExcelService() {
		super();
	}

	public ExcelService(List<CandidateDetail> listOfCandidate)
	{
		this.listOfCandidate=listOfCandidate;
		workBook=new XSSFWorkbook();	
	}
	
	public void assignClientList(List<ClientDetail> listOfClient)
	{
		this.listOfClient=listOfClient;
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
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row,0,"FirstName",style);
		createCell(row,1,"LastName",style);
		createCell(row,2,"Applied Job",style);
		createCell(row,3,"Status",style);
		createCell(row,4,"Email",style);
		createCell(row,5,"Phone",style);
		createCell(row,6,"linkedIn",style);
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
			 createCell(row,columnCount++,candidate.getStatus(),style);
			 createCell(row,columnCount++,candidate.getEmail(),style);
			 createCell(row,columnCount++,candidate.getPhone(),style);
			 createCell(row,columnCount++,candidate.getLinkedIn(),style);
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
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
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
	
}
