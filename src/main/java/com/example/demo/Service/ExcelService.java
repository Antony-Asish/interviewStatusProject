package com.example.demo.Service;

import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.model.CandidateDetail;

public class ExcelService {
	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	private List<CandidateDetail> candidateDetail;
	
	public ExcelService(List<CandidateDetail> candidateDetail)
	{
		this.candidateDetail=candidateDetail;
		workBook=new XSSFWorkbook();	
	}
	
	public void createCell(Row row,int columnCount,Object value,CellStyle style)
	{
		sheet.autoSizeColumn(columnCount);
		//Cell
	}
}
