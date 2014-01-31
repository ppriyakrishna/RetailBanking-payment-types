package com.retailBanking.payments.services;

import com.retailBanking.payments.domain.User;

public interface UserService {
	public void addNewUser(User user);
	public User getUser(String userName);
}