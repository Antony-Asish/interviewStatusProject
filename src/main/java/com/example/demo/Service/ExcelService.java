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

public class ExcelService {
	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	private List<CandidateDetail> litOfCandidate;
	
	public ExcelService(List<CandidateDetail> litOfCandidate)
	{
		this.litOfCandidate=litOfCandidate;
		workBook=new XSSFWorkbook();	
	}
	
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
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row,0,"Id",style);
		createCell(row,1,"Name",style);
		createCell(row,2,"Email",style);
		createCell(row,3,"Gender",style);
		createCell(row,4,"Phone",style);
	}
	private void writeDataLines()
	{
		int rowCount=2;
		 CellStyle style=workBook.createCellStyle();
		 XSSFFont font=workBook.createFont();
		 font.setFontHeight(14);
		 style.setFont(font);
		 
		 for(CandidateDetail candidate :litOfCandidate )
		 {
			 Row row=sheet.createRow(rowCount++);
			 int columnCount=0;
			 createCell(row,columnCount++,candidate.getId(),style);
			 createCell(row,columnCount++,candidate.getFirstName(),style);
			 createCell(row,columnCount++,candidate.getEmail(),style);
			 createCell(row,columnCount++,candidate.getGender(),style);
			 createCell(row,columnCount++,candidate.getPhone(),style);
		 }
	}
	
	public void export(HttpServletResponse response) throws IOException
	{
		writeHeaderLine();
		writeDataLines();
		
		ServletOutputStream outPutStream=response.getOutputStream();
		workBook.write(outPutStream);
		workBook.close();
		outPutStream.close();
	}
}
