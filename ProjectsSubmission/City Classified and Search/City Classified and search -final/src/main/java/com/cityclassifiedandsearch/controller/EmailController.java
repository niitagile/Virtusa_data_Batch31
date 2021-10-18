package com.cityclassifiedandsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cityclassifiedandsearch.bean.Classified;
import com.cityclassifiedandsearch.bean.User;
import com.cityclassifiedandsearch.repo.ClassifiedRepository;
import com.cityclassifiedandsearch.repo.UserRepository;
import com.cityclassifiedandsearch.service.ClassifiedService;

import java.util.*;

import javax.mail.MessagingException;

@Controller
public class EmailController {
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private ClassifiedRepository classifiedrepo;
	
	@Autowired
	private SmtpMailSender smtpMailSender;
	
	public boolean EmailSubscription(String cityname, String address) {
		String subject = cityname + " added";
		String body = "Address: "+ address;
		List<User> test = userrepo.findAll();
		for(User u: test) {
			System.out.println("USER EMAIL HERE : "+ u.getUserEmail() + "\n");
			try {
				smtpMailSender.send(u.getUserEmail(), subject, body);
			} catch (MessagingException e) {
				System.out.println("MAIL NOT SENT");
				return false;
			}
		}
		return true;
	}
public boolean approvalMail(int classifiedId) {
		
		Classified test = classifiedrepo.findByClassifiedId(classifiedId);
		User user=userrepo.findByUserId(test.getUserId());
		String subject = "Your classified "+test.getClassifiedTitle()+" is approved.";
		String body = "Your classified "+test.getClassifiedTitle()+" is approved. You classified has been successfully posted.";
		System.out.println("USER EMAIL HERE : "+ user.getUserEmail() + "\n");
			try {
				smtpMailSender.send(user.getUserEmail(), subject, body);
			} catch (MessagingException e) {
				System.out.println("MAIL NOT SENT");
				return false;
			}
		
		return true;
	}
	
public boolean rejectMail(String ClassifiedTitle,int UserId) {
		User user=userrepo.findByUserId(UserId);
		String subject = "Your classified "+ClassifiedTitle+" is rejected.";
		String body = "Your classified "+ClassifiedTitle+" is rejected. Please add classifieds with appropriate details.";
		System.out.println("USER EMAIL HERE : "+ user.getUserEmail() + "\n");
			try {
				smtpMailSender.send(user.getUserEmail(), subject, body);
				
			} catch (MessagingException e) {
				System.out.println("MAIL NOT SENT");
				return false;
			}
		
		return true;
	}
	
}
