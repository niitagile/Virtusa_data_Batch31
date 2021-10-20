package com.bank.dao;

import java.util.List;
import java.util.Optional;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.beans.Account;
import com.bank.beans.LoanQuery;
import com.bank.beans.RaiseTicket;
import com.bank.beans.Transaction;
import com.bank.beans.User;

@Service
public class UserDAO {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AccountRepository accRepo;
	@Autowired
	private TransactionRepository transacRepo;
	@Autowired
	private RaiseQueryRepository queryRepo;
	@Autowired
	private LoanRepository loanRepo;

	public void addUserToDB(User user, Account account) {
		userRepo.save(user);
		accRepo.save(account);
	}

	public List<Account> fetchUserAndAccount() {
		return accRepo.findAll();
	}

	public User findUserByEmailPassword(String email, String password) {
		return userRepo.findByEmailAndPassword(email, password);
	}

	public String getBalance(int id, String pin) {

		Optional<User> user = userRepo.findByIdAndPin(id, pin);
//		try {
//			Account account = user.get().getAccount();
//			return String.valueOf(account.getBal());
//		} catch (Exception e) {
//			return "Invalid PIN";
//		}
		if (user.isPresent()) {
			Account account = user.get().getAccount();
			return String.valueOf(account.getBal());
		} else {
			return "Invalid PIN";
		}

	}

	public User getUser(int id) {
		Optional<User> list = userRepo.findById(id);

		if (list.isPresent()) {
			return list.get();
		} else {
			return null;
		}
	}

	public Optional<Account> findAccount(int id) {
		return accRepo.findById(id);
	}

	public Optional<Account> findAccountByUPI(String upi) {
		return accRepo.findByUpi(upi);
	}

	public void transferFund(Transaction t) {
		int fromAcc = t.getFrom();
		int toAcc = t.getTo();
		Optional<Account> list1 = accRepo.findById(fromAcc);
		Optional<Account> list2 = accRepo.findById(toAcc);
		if (list1.isPresent() && list2.isPresent()) {
			Account fromList = list1.get();
			Account toList = list2.get();

			fromList.setBal(fromList.getBal() - t.getAmount());
			toList.setBal(toList.getBal() + t.getAmount());

			accRepo.save(fromList);
			accRepo.save(toList);
			transacRepo.save(t);
		}

	}

	public List<Transaction> getAllTransactions(int id) {
		User user = getUser(id);
		List<Transaction> list = transacRepo.findAllByFrom(user.getAccount().getAccno());
		List<Transaction> list2 = transacRepo.findAllByTo(user.getAccount().getAccno());
		for (Transaction t : list) {
			t.setType("DEBIT");
		}
		for (Transaction t : list2) {
			t.setType("CREDIT");
		}

		list.addAll(list2);
		return list;
	}

	public void updateUser(User user) {
		userRepo.save(user);
	}

	public void enableUpi(int id) {
		User user = getUser(id);
		Account account = user.getAccount();
		String str[] = user.getName().split(" ");

		account.setUpi(str[0].toLowerCase() + user.getPhone().substring(7) + new Random().nextInt(20) + "@yesbank");
		user.setAccount(account);
		userRepo.save(user);
	}

	public void addQuery(RaiseTicket ticket) {
		queryRepo.save(ticket);
	}

	public void addLoanrequest(LoanQuery loan) {
		loanRepo.save(loan);
	}

	public Optional<User> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
}
