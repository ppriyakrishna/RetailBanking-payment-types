package com.retailBanking.payments.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.retailBanking.payments.domain.Account;

public class AccountValidator  implements Validator{
	
	private static final String NAME_PATTERN = 
			"(^[a-zA-Z /s]{3,15}$)";
	
	public boolean supports(Class<?> arg0) {
		return String.class.equals(arg0);
	}
	
	public void validate(Object arg0, Errors errors) {
		
		Account account=(Account)arg0;
		String accountName=account.getAccount_Name();
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branch_Name","Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currency","Required");
		
		if(!accountName.matches(NAME_PATTERN)){
			errors.rejectValue("account_Name", "InvalidAccountName");	
			return;
		}
		
		if(account.getAmount()<100 ){
			errors.rejectValue("amount", "mininumAmount");
			return;
			
		}

		
	}

}
