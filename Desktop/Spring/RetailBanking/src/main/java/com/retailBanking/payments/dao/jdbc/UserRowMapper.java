package com.retailBanking.payments.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.jdbc.core.RowMapper;

import com.retailBanking.payments.domain.Gender;
import com.retailBanking.payments.domain.User;

public class UserRowMapper  implements RowMapper<User> {

	public User mapRow(ResultSet resultSet, int row) throws SQLException {
		
		int user_Id;
		String lastName;
		String firstName;
		int age;
		String gender;
		String userName;
		String password;
		
		User user=new User();
		
		user_Id=resultSet.getInt("user_Id");
		userName = resultSet.getString("userName");
		password=resultSet.getString("password");
		lastName=resultSet.getString("lastName");
		firstName=resultSet.getString("firstName");
		gender=resultSet.getString("gender");		
		age=resultSet.getInt("age");
		
		user.setUser_Id(user_Id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(age);
		user.setPassword(password);
		user.setUserName(userName);
	
		
		if(gender.equals("Male")){
			user.setGender(Gender.Male);
		}else{
			user.setGender(Gender.Female);
		}
		
		return user;
		
	}

}
