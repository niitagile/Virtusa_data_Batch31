package com.virtusa.batch31.paymentbillingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.batch31.paymentbillingsystem.entities.PaymentDetail;
import com.virtusa.batch31.paymentbillingsystem.entities.Student;
import com.virtusa.batch31.paymentbillingsystem.repository.PaymentDetailRepository;
import com.virtusa.batch31.paymentbillingsystem.repository.StudentRepository;

@Service
public class PaymentDetailService {
	@Autowired
	private PaymentDetailRepository paymentDetailRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private FeeReceiptPDFGeneration feeReceiptPDFGeneration;
	
	public PaymentDetail createPaymentDetail(int rollNumber, PaymentDetail paymentDetail) {
		try {
			PaymentDetail pd = paymentDetailRepository.save(paymentDetail);
			Student student = studentRepository.getById(rollNumber);
			student.getPaymentDetails().add(pd);
			studentRepository.save(student);
			String emailBody = "Amount " + paymentDetail.getAmount() + " paid successfully";
			String emailSubject = "Fee Notification!! Roll Number " + student.getRollNumber();
			String fileName = student.getRollNumber() + "-" + pd.getId();
			String filePath = feeReceiptPDFGeneration.generatePdf(fileName, student, pd);
			emailSenderService.sendEmailWithAttachment(student.getEmail(), emailBody, emailSubject, filePath);
			return pd;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	public PaymentDetail getPaymentDetail(int id) {
		return paymentDetailRepository.findById(id).orElse(null);
	}
	
	public List<PaymentDetail> getAllPaymentDetails(){
		return paymentDetailRepository.findAll();
	}
	
	public PaymentDetail updatePaymentDetail(PaymentDetail paymentDetail) {
		PaymentDetail pd = paymentDetailRepository.getById(paymentDetail.getId());
		pd.setAmount(paymentDetail.getAmount());
		pd.setDate(paymentDetail.getDate());
		return paymentDetailRepository.save(pd);
	}
	
	public void deletePaymentDetail(int id) {
		paymentDetailRepository.deleteById(id);
	}
}
