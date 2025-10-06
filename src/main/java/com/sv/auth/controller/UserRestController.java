package com.sv.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sv.auth.entity.User;
import com.sv.auth.service.UserService;

@RestController
public class UserRestController {
@Autowired
	private UserService userService;
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
}
