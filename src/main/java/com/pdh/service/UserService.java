package com.pdh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdh.model.User;
import com.pdh.repository.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao repository;
	
	public List<User> findAll(){
		return (List<User>) repository.findAll();
	}
	public User findOne(Integer id) {
		return repository.findOne(id);
	}
	public void save(User user) {
		repository.save(user);
	}
	public void delete(Integer id) {
		repository.delete(id);
	}
	public void delete(User user) {
		repository.delete(user);
	}
}
