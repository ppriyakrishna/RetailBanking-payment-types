package com.retailBanking.payments.dao;

import com.retailBanking.payments.domain.User;

public interface UserDao {
	
	public void addNewUser(User user) ;
	public User getUser(String userName);
	public User getUser(int user_Id);

}
