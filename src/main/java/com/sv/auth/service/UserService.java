package com.sv.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sv.auth.entity.User;
import com.sv.auth.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository UserRepo;
	public boolean saveUser(User user) {
		  log.info("User data", user);
		String encodePwd=passwordEncoder.encode(user.getPassword());
		log.info("encoded password>>>>>",encodePwd);
		user.setPassword(encodePwd);
		User savedUser=UserRepo.save(user);
		  log.info("User saved successfully with ID: {}", savedUser.getId());
		return savedUser.getId()!=null;
		
	}

}
