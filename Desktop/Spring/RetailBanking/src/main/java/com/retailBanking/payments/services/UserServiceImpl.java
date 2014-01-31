package com.retailBanking.payments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailBanking.payments.dao.UserDao;
import com.retailBanking.payments.domain.User;


@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public void addNewUser(User user) {
		userDao.addNewUser(user);
		
	}
	
	public User getUser(String userName){
		return userDao.getUser(userName);
	}
	
	

}
