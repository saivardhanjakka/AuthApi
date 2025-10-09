package com.sv.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.auth.entity.User;
import com.sv.auth.service.JwtService;
import com.sv.auth.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
@Autowired
	private UserService userService;
@Autowired
private AuthenticationManager authManager;

@Autowired
private JwtService jwt;
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


@GetMapping("/welcome")
public String welcome() {
	return "welcome";
}

@PostMapping("/login")
public ResponseEntity<String> loginCheck(@RequestBody User user) {
	
	UsernamePasswordAuthenticationToken token = 
			new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

	try {
		Authentication authenticate = authManager.authenticate(token);

		if (authenticate.isAuthenticated()) {
			String jwtToken = jwt.generateToken(user.getEmail());
			return new ResponseEntity<>(jwtToken, HttpStatus.OK);
		}

	} catch (Exception e) {
		//logger
	}

	return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
}


}
