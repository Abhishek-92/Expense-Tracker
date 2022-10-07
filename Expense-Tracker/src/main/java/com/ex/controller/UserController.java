package com.ex.controller;

import java.util.List;

import com.ex.model.User;
import com.ex.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = this.service.getUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> loginUser(@RequestBody User user){
		int res = this.service.login(user);
		if(res == 1)
			return new ResponseEntity<Object>("Welcome Manager", HttpStatus.OK);
		else if(res == 2)
			return new ResponseEntity<Object>("Welcome HR", HttpStatus.OK);
		else if(res == 3)
			return new ResponseEntity<Object>("Welcome admin", HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Invalid Username or Password", HttpStatus.UNAUTHORIZED);
		
	}
	
	@PatchMapping("{userId}/updatePassword")
	public ResponseEntity<Object> updatePassword(@PathVariable int userId, @RequestBody User user){
		String res = this.service.updatePassword(userId, user.getPassword());
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
}
