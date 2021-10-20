package com.virtusa.export;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import com.virtusa.model.User;
public class UserExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<User> list;
	
	
	public UserExcelExporter(List<User> list) {
		this.list=list;
		workbook = new XSSFWorkbook();
		
	}
	
	private void createCell(Row row,int columnCount, Object value,CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if(value instanceof Long) {
			cell.setCellValue((Long) value);
		}else if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	private void writeHeaderLine() {
		sheet=workbook.createSheet("Users");
		
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);

		sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "User Id", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "User name", style);
        createCell(row, 3, "Email", style);
        
	}
	
	private void writeDataLines() {
		int rowCount=2;
		
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		
		for(User u:list) {
			Row row=sheet.createRow(rowCount++);
			int columnCount=0;
			createCell(row, columnCount++, u.getId(), style);
			createCell(row, columnCount++, u.getName(), style);
			createCell(row, columnCount++, u.getUserName(),style);
			createCell(row, columnCount++, u.getEmail(), style);
					}
	}
	
	public void export(HttpServletResponse response) throws IOException{
		writeHeaderLine();
		writeDataLines();
		
		ServletOutputStream outputStream=response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	
}
