package com.bank.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.beans.Account;
import com.bank.beans.LoanQuery;
import com.bank.beans.RaiseTicket;
import com.bank.beans.Transaction;
import com.bank.beans.User;

@Service
public class AdminDAO {

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

	public List<Account> showAllUserDetails() {
		return accRepo.findAll();
	}

	public List<Transaction> fetchTransactionOfUser(int acc) {
		List<Transaction> list = transacRepo.findAllByFrom(acc);
		List<Transaction> list2 = transacRepo.findAllByTo(acc);
		for (Transaction t : list) {
			t.setType("DEBIT");
		}
		for (Transaction t : list2) {
			t.setType("CREDIT");
		}

		list.addAll(list2);
		return list;
	}

	public void closeAcccount(int id) {
		userRepo.deleteById(id);

	}

	public User getUser(int id) {
		Optional<User> list = userRepo.findById(id);
		if (list.isPresent()) {
			return list.get();
		} else {
			return null;
		}
	}

	public List<Account> searchByAccount(int account) {
		return accRepo.findAllById(Arrays.asList(account));
	}

	public List<RaiseTicket> getAllQuery() {
		return queryRepo.findAll();
	}

	public RaiseTicket getQueryById(int id) {
		Optional<RaiseTicket> list = queryRepo.findById(id);
		if (list.isPresent()) {
			return list.get();
		} else {
			return null;
		}
	}

	public void updateQueryStatus(RaiseTicket query) {
		queryRepo.save(query);
	}

	public List<LoanQuery> getLoanApplicationdata() {
		return loanRepo.findAll();
	}
}
