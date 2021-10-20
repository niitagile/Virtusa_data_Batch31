package com.virtusa.batch31.paymentbillingsystem.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.virtusa.batch31.paymentbillingsystem.entities.PaymentDetail;
import com.virtusa.batch31.paymentbillingsystem.entities.Student;

import org.springframework.stereotype.Service;

@Service
public class FeeReceiptPDFGeneration {

    public String generatePdf(String fileName, Student student, PaymentDetail pd) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        String filePath = "/home/ritik/Desktop/fee-receipts/"+fileName;
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        Font font1 = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, BaseColor.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        document.add(new Paragraph("FEE RECEIPT", font1));
        document.add(new Paragraph(student.getName(),font2));
        document.add(new Paragraph(student.getPhone(), font2));
        document.add(new Paragraph(student.getEmail(), font2));
        document.add(new Paragraph("PAYMENT DETAIL", font1));
        document.add(new Paragraph(pd.getDate(), font2));
        document.add(new Paragraph(pd.getAmount(), font2));
        document.close();
        writer.close();
        return filePath;
    }
}
