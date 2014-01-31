package com.retailBanking.payments.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.retailBanking.payments.domain.FundsTransfer;

public class FundsTransferValidator  implements Validator{
	
	
	public boolean supports(Class<?> arg0) {
		return String.class.equals(arg0);
	}
	
	public void validate(Object arg0, Errors errors) {

		FundsTransfer fundsTransfer=(FundsTransfer)arg0;
			
		if(fundsTransfer.getToAccount_Id()==0){
			errors.rejectValue("toAccount.account_Id", "Required");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toAccount.account_Name","Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toAccount.branch_Name","Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paymentType.type_Code","Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toBank.bank_Name","Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toBank.country_Name","Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toBank.currency","Required");
		
		if(fundsTransfer.getTransferAmt()<1 ){
			errors.rejectValue("transferAmt", "mininumAmount");
			return;
			
		}

		

		
	}


}
