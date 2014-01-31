package com.retailBanking.payments.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailBanking.payments.domain.User;


public class UserValidator implements Validator{	

	private Pattern pattern;
	private Matcher matcher;
	

	private static final String PASSWORD_PATTERN = 
			"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";

	private static final String NAME_PATTERN = 
			"(^[a-zA-Z /s]{3,15}$)";

	public boolean supports(Class<?> arg0) {
		return String.class.equals(arg0);
	}

	public UserValidator(){
		pattern = Pattern.compile(PASSWORD_PATTERN);
		
	}


	public void validate(Object arg0, Errors errors) {

		User user=(User)arg0;
		String firstName=user.getFirstName();
		String lastName=user.getLastName();
		String userName=user.getUserName();
		String password=user.getPassword();
		String confirmPassword=user.getConfirmPassword();

		matcher = pattern.matcher(password);



		if(!lastName.matches(NAME_PATTERN)){
			errors.rejectValue("lastName", "InvalidLastName");	
			return;
		}else if(!firstName.matches(NAME_PATTERN)){
			errors.rejectValue("firstName", "InvalidFirstName");	
			return;
		}else if(!userName.matches(NAME_PATTERN)){
			errors.rejectValue("userName", "InvalidUserName");
			return;
		}else if(!matcher.matches()){
			errors.rejectValue("password", "InvalidPassword");
			return;
		}else if(!password.equals(confirmPassword)){
			errors.rejectValue("confirmPassword", "passwordEqual");
			return;
		}
	}




}
