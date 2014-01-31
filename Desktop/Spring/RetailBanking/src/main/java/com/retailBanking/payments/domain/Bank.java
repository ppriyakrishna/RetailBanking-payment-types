package com.retailBanking.payments.domain;

public class Bank {
	private int bank_Id;
	private String bank_Name;
	private String country_Name;
	private String currency;


	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCountry_Name() {
		return country_Name;
	}
	public void setCountry_Name(String country_Name) {
		this.country_Name = country_Name;
	}
	public int getBank_Id() {
		return bank_Id;
	}
	public void setBank_Id(int bank_Id) {
		this.bank_Id = bank_Id;
	}
	public String getBank_Name() {
		return bank_Name;
	}
	public void setBank_Name(String bank_Name) {
		this.bank_Name = bank_Name;
	}

	public boolean equals(Object obj){
		if(obj instanceof Bank){
			Bank bank=(Bank)obj;
			if(country_Name.equalsIgnoreCase(bank.country_Name) && currency.equalsIgnoreCase(bank.currency)){
				return true;
			}
		}

		return false;
	}
	
	public boolean equalsFromDB(Object obj){
		if(obj instanceof Bank){
			Bank bank=(Bank)obj;
			if(bank_Name.equalsIgnoreCase(bank.bank_Name) && country_Name.equalsIgnoreCase(bank.country_Name) && currency.equalsIgnoreCase(bank.currency )){
				return true;
			}
		}

		return false;
	}


}
