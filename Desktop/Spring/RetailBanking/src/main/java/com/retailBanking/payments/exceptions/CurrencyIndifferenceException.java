package com.retailBanking.payments.exceptions;

public class CurrencyIndifferenceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CurrencyIndifferenceException(String message){
		super(message);
	}

}
