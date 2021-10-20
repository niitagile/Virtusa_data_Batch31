package com.bank.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.beans.LoanQuery;
import com.bank.beans.RaiseTicket;
import com.bank.beans.User;
import com.bank.dao.AdminDAO;

@Controller
@ComponentScan("com.bank.dao")
public class AdminController {
	
	@Autowired
	private AdminDAO adminDAO;
	
	@GetMapping("/showall")
	public String showAllUser(HttpServletRequest request) {
		request.setAttribute("allUser", adminDAO.showAllUserDetails()) ;
		return "adminshowusers";
	}

	
	@GetMapping("/viewdetail")
	public String showTransactOfUser(@RequestParam("id") int id, HttpServletRequest request) {
		User user= adminDAO.getUser(id);
		request.setAttribute("userdetail", user);
		request.setAttribute("usertransac", adminDAO.fetchTransactionOfUser(user.getAccount().getAccno()));
		return "viewdetail";
	}
	
	@GetMapping("/close")
	public String closeAccount(@RequestParam("id") int  id) {
		adminDAO.closeAcccount(id);
		return "redirect:/showall";
	}
	
	@GetMapping("/adminprofile")
	public String getAdminProfileDetails(HttpSession session, HttpServletRequest request) {
		request.setAttribute("adminmodel", adminDAO.getUser(Integer.parseInt(String.valueOf(session.getAttribute("userid")))));
		return "adminprofile";
	}
	
	@GetMapping("/search")
	public String searchAccount(@RequestParam("account") int account, HttpServletRequest request) {
		request.setAttribute("allUser", adminDAO.searchByAccount(account)) ;
		return "adminshowusers";
	}
	
	@GetMapping("/getallquery")
	public String fetchAllQuery(HttpServletRequest request) {
		List<RaiseTicket> tickets= adminDAO.getAllQuery();
		request.setAttribute("querylist", tickets);
		return "adminquery";
	}
	
	@GetMapping("/status")
	public String resolveissue(@RequestParam("id") int id) {
		RaiseTicket ticket= adminDAO.getQueryById(id);
		ticket.setStatus("complete");
		adminDAO.updateQueryStatus(ticket);
		return "redirect:/getallquery";
	}
	@GetMapping("/adminloan")
	public String getLoanApplications(HttpServletRequest request) {
		List<LoanQuery> list= adminDAO.getLoanApplicationdata();
		request.setAttribute("loanquery", list);
		return "adminloan";
	}
}
