package com.ex.service;

import java.util.List;

import com.ex.dao.UserRepository;
import com.ex.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public List<User> getUsers() {
		return this.repo.findAll();
	}

	@Override
	public int login(User user) {
		List<User> allUser = this.repo.findAll();
		for(User u : allUser) {
			if(u.getUserName().toLowerCase().equals(user.getUserName().toLowerCase())) {
				if(u.getPassword().equals(user.getPassword())) {
					return u.getId();
				}
			}
		}
		return -1;
	}

	@Override
	public String updatePassword(int userId, String updatePass) {
		User user = this.repo.findById(userId).get();
		user.setPassword(updatePass);
		this.repo.save(user);
		return "Password Updated Sucessfully";
	}

	
}
