package com.sv.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sv.auth.entity.User;
import com.sv.auth.service.UserService;

@RestController
public class UserRestController {
@Autowired
	private UserService userService;
@Autowired
private AuthenticationManager authManager;
@PostMapping("/register")
public ResponseEntity<String> saveUser(@RequestBody User user){
	boolean status=userService.saveUser(user);
	if(status) {
		return new ResponseEntity<String>("success",HttpStatus.CREATED);
	}
	else {
		return new ResponseEntity<String>("failure",HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
		
	}
@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody User user){
	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
	Authentication authenticate=authManager.authenticate(token);
	boolean status=authenticate.isAuthenticated();
	if(status) {
		return new ResponseEntity<String>("loginSuccess",HttpStatus.CREATED);
	}
	else {
		return new ResponseEntity<String>("loginFailure",HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
}

}
