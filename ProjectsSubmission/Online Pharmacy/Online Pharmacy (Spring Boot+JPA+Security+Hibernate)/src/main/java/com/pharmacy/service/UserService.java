package com.pharmacy.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.UserBean;
import com.pharmacy.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public UserBean addUser(UserBean user) {
		return userRepo.save(user);
	}

	public List<UserBean> getUsers() {
		return userRepo.findAll();
	}

	public UserBean getUser(String username) {
		return userRepo.findByUsername(username).get();
	}

	public Object getRole(String username) {
		return userRepo.getRole(username);
	}

	public Object auth(String username, String password) {
		return userRepo.authenticate(username, password);
	}

	public UserBean updateUser(UserBean user, int id) {
		user.setId(id);
		return userRepo.save(user);
	}

	public UserBean updateUser(UserBean user, String username, String password) {
		UserBean olduser = (UserBean) userRepo.authenticate(username, password);
		user.setId(olduser.getId());
		return userRepo.save(user);
	}

	@Transactional
	public String deleteByUsernameAndPass(String username, String password) {
		int res = userRepo.deleteByUsernameAndPassword(username, password);
		if (res == 1)

			return "SUCCESS";
		else
			return "FAILED";
	}

	@Transactional
	public void deleteByUserId(int id) {
		userRepo.deleteById(id);
	}

	public String decodeToken(String token) throws UnsupportedEncodingException {
		String payload = token.split("\\.")[1];
		//String decoded = new String(Base64.decodeBase64(payload), "UTF-8");
		return new String(Base64.decodeBase64(payload), "UTF-8");
	}
}
