package com.bank.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bank.beans.Account;
import com.bank.beans.LoanQuery;
import com.bank.beans.RaiseTicket;
import com.bank.beans.Transaction;
import com.bank.beans.User;
import com.bank.dao.UserDAO;

@Controller
@ComponentScan("com.bank.dao")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}

	@GetMapping("/loan")
	public String loanPage(HttpServletRequest request, HttpSession session) {
		List<Transaction> list = userDAO
				.getAllTransactions(Integer.parseInt(String.valueOf(session.getAttribute("userid"))));
		int maxLoan = 0;
		for (Transaction t : list) {
			maxLoan += t.getAmount();
		}
		request.setAttribute("maxloan", maxLoan);
		return "loan";
	}

	@GetMapping("/fundtransfer")
	public String fundtransfer() {
		return "fundtransfer";
	}

	@GetMapping("/upitransfer")
	public String upitransfer(HttpSession session, HttpServletRequest request) {
		User user = userDAO.getUser(Integer.parseInt(String.valueOf(session.getAttribute("userid"))));
		request.setAttribute("upistatus", user.getAccount().getUpi());
//		System.out.println(user.getAccount().getUpi());
		return "upitransfer";
	}

	@GetMapping("/enableupi")
	public String enableUpi(HttpSession session) {
		userDAO.enableUpi(Integer.parseInt(String.valueOf(session.getAttribute("userid"))));
		return "redirect:/upitransfer";
	}

	@GetMapping("/getBalance")
	@ResponseBody
	public String getBalance(@RequestParam("id") int id, @RequestParam("pin") String pin) {

		return userDAO.getBalance(id, pin);

	}

	@GetMapping("/profile")
	public String getUserProfileDetails(HttpSession session, HttpServletRequest request) {
		request.setAttribute("usermodel",
				userDAO.getUser(Integer.parseInt(String.valueOf(session.getAttribute("userid")))));
		return "profile";
	}

	@PostMapping("/transfer")
	public String transferFund(@RequestParam("id") int id, @RequestParam("accno") int accno,
			@RequestParam("ifsc") String ifsc, @RequestParam("accname") String accname,
			@RequestParam("amount") float amount, @RequestParam("mode") String mode, HttpServletRequest request) {
		if (userDAO.findAccount(accno).isPresent()) {
			User user = userDAO.getUser(id);
			if (user.getAccount().getBal() > 0 && (user.getAccount().getBal() - amount) >= 0) {
				Transaction trans = new Transaction();
				trans.setDate(LocalDateTime.now());
				trans.setAmount(amount);
				trans.setMode(mode);
				trans.setFrom(user.getAccount().getAccno());
				trans.setType("DEBIT");
				trans.setTo(accno);

				userDAO.transferFund(trans);
				request.setAttribute("msg", "Transaction successfull");
				return "transacstatus";
			} else {
				request.setAttribute("msg", "Insufficient Balance");
				return "transacstatus";
			}
		} else {
			request.setAttribute("msg", "Invalid Account");
			return "transacstatus";
		}
	}

	@GetMapping("/passbook")
	public String getAllTransaction(HttpSession session, HttpServletRequest request) {
		request.setAttribute("list",
				userDAO.getAllTransactions(Integer.parseInt(String.valueOf(session.getAttribute("userid")))));
		return "passbook";
	}

	@PostMapping("/editprofile")
	public String editProfile(@RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam("phone") String phone, @RequestParam("address") String address, @RequestParam("id") int id,
			@RequestParam("pin") int pin) {
		User u = userDAO.getUser(id);
		u.setName(name);
		u.setAddress(address);
		u.setPassword(passwordEncoder.encode(password));
		u.setPin(String.valueOf(pin));
		u.setPhone(phone);

		userDAO.updateUser(u);
		if (u.getRole().equals("ADMIN")) {
			return "redirect:/adminprofile";
		} else {
			return "redirect:/profile";
		}

	}

	@PostMapping("/upi")
	public String upitransac(@RequestParam("upi") String upi, @RequestParam("id") int id,
			@RequestParam("amt") float amt, HttpServletRequest request) {
//		System.out.println(request.getSession().getAttribute("userid"));

//		try {
//		Account account = userDAO.findAccountByUPI(upi).get();
//		if (account!=null) {
//			User user = userDAO.getUser(id);
//			if ((user.getAccount().getBal()) > 0 && (user.getAccount().getBal()-amt)>=0) {
//				Transaction trans = new Transaction();
//				trans.setDate(LocalDateTime.now());
//				trans.setAmount(amt);
//				trans.setMode("UPI");
//				trans.setFrom(user.getAccount().getAccno());
//				trans.setType("DEBIT");
//				trans.setTo(account.getAccno());
//
//				userDAO.transferFund(trans);
//				request.setAttribute("msg", "Transaction successfull");
//				return "upitransacstatus";
//			} else {
//				request.setAttribute("msg", "Insufficient Balance");
//				return "upitransacstatus";
//			}
//		} else {
//			request.setAttribute("msg", "Invalid UPI");
//			return "upitransacstatus";
//		}
//		}catch (Exception e) {
//			request.setAttribute("msg", "Invalid UPI");
//			return "upitransacstatus";
//		}

		Optional<Account> accountList = userDAO.findAccountByUPI(upi);
		if (accountList.isPresent()) {
			Account account = accountList.get();
			if (account != null) {
				User user = userDAO.getUser(id);
				if ((user.getAccount().getBal()) > 0 && (user.getAccount().getBal() - amt) >= 0) {
					Transaction trans = new Transaction();
					trans.setDate(LocalDateTime.now());
					trans.setAmount(amt);
					trans.setMode("UPI");
					trans.setFrom(user.getAccount().getAccno());
					trans.setType("DEBIT");
					trans.setTo(account.getAccno());

					userDAO.transferFund(trans);
					request.setAttribute("msg", "Transaction successfull");
					return "upitransacstatus";
				} else {
					request.setAttribute("msg", "Insufficient Balance");
					return "upitransacstatus";
				}
			} else {
				request.setAttribute("msg", "Invalid UPI");
				return "upitransacstatus";
			}
		} else {
			request.setAttribute("msg", "Invalid UPI");
			return "upitransacstatus";
		}

	}

	@PostMapping("/raisequery")
	public String raiseQuery(@RequestParam("id") int id, @RequestParam("query") String query) {
		RaiseTicket r = new RaiseTicket(query, "pending", LocalDateTime.now(), userDAO.getUser(id));
		userDAO.addQuery(r);
		return "redirect:/profile";
	}

	@PostMapping("/loanrequest")
	public String loanRequest(HttpSession session, @RequestParam("loanamt") int loanamt) {
		LoanQuery loanQuery = new LoanQuery(loanamt,
				userDAO.getUser(Integer.parseInt(String.valueOf(session.getAttribute("userid")))), LocalDateTime.now());
		userDAO.addLoanrequest(loanQuery);
		return "redirect:/loan";
	}

}
