package com.ex.service;

import java.util.List;

import com.ex.model.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public int login(User user);
	
	public String updatePassword(int userId, String updatePass);
}
