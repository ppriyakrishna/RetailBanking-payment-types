package com.retailBanking.payments.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;




public class User {
	
	private int user_Id;
	@Size(min=2, max=30)
	private String lastName;
	@NotEmpty
	private String firstName;
	@Range(min=18, max=90)
	int age;
	
	@NotEmpty
	private String userName;
	private Gender gender;
	@Size(min=8, max=15)
	private String password;
	private String confirmPassword;
	
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public User() {
	}
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public Gender getGender() {
		return gender;
	}

	public String toString() {
		String studStr = "User[" + firstName + " " + lastName + "  gender: " + gender + "]";
		return studStr;
	}
	
	public boolean equals(Object otherObj) {
		User otherStud;
		
		if (!(otherObj instanceof User)) return false;
		otherStud = (User) otherObj;
		return (userName.equalsIgnoreCase(otherStud.userName) && password.equals(otherStud.password) );
	}

}
