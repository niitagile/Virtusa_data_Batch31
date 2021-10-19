package com.pharmacy.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pharmacy.model.OrdersBean;
public class OrderPDFExporter {

	private List<OrdersBean> listOrders;
	
	public OrderPDFExporter(List<OrdersBean> listOrders) {
        this.listOrders = listOrders;
    }
	
	public void export(HttpServletResponse response) throws DocumentException, IOException, com.itextpdf.text.DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
         
        Paragraph p = new Paragraph("List of Orders", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
		cell.setPhrase(new Phrase("Order ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Username", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Distributor Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Order Date", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Address", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Phone Number", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Total Quantity", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Price", font));
		table.addCell(cell);
		
	}
	 private void writeTableData(PdfPTable table) {
	        for (OrdersBean order : listOrders) {
	            table.addCell(String.valueOf(order.getOrderId()));
	            table.addCell(order.getUsername());
	            table.addCell(order.getDistributorName());
	            table.addCell(order.getOrderDate());
	            table.addCell(String.valueOf(order.getAddress()));
	            table.addCell(order.getPhoneNumber());
	            table.addCell(String.valueOf(order.getTotalQuantity()));
	            table.addCell(String.valueOf(order.getPrice()));
	        }
	    }
	
}
