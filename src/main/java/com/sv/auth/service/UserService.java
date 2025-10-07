package com.sv.auth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//import com.sv.auth.entity.User;
import com.sv.auth.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserService implements UserDetailsService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;
	public boolean saveUser( com.sv.auth.entity.User user) {
		  log.info("User data", user);
		String encodePwd=passwordEncoder.encode(user.getPassword());
		log.info("encoded password>>>>>"+encodePwd);
		user.setPassword(encodePwd);
		com.sv.auth.entity.User savedUser=userRepo.save(user);
		  log.info("User saved successfully with ID: {}"+ savedUser.getId());
		return savedUser.getId()!=null;
		
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 com.sv.auth.entity.User user=userRepo.findByEmail(email);
		return new User(user.getEmail(),user.getPassword(),Collections.emptyList());
	}

}
