package com.retailBanking.payments.exceptions;

public class InsufficentFundsException extends RuntimeException {
	public InsufficentFundsException(String msg) {
		super(msg);
	}

}
