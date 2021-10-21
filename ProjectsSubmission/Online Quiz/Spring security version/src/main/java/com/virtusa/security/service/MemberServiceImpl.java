package com.virtusa.security.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.virtusa.security.model.Member;
import com.virtusa.security.repository.RoleRepository;
import com.virtusa.security.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(Member member) {
		member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
		member.setRoles(new HashSet<>(roleRepository.findAll()));
		memRepository.save(member);
	}

	@Override
	public Member findByUsername(String username) {
		return memRepository.findByUsername(username);
	}
}
