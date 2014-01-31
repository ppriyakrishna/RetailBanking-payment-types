package com.retailBanking.payments.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Account {
	
	private long account_Id;
	@NotEmpty
	private String account_Name;
	private long branch_Id;
	private double amount;
	private String branch_Name;
	private String currency;
	private int user_Id;
	
	public Account(){}
	
	public Account(String account_Name,String branch_Name){
		this.account_Name=account_Name;
		this.branch_Name=branch_Name;		
	}
	
	
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBranch_Name() {
		return branch_Name;
	}
	public void setBranch_Name(String branch_Name) {
		this.branch_Name = branch_Name;
	}
	public long getAccount_Id() {
		return account_Id;
	}
	public void setAccount_Id(long account_Id) {
		this.account_Id = account_Id;
	}
	public String getAccount_Name() {
		return account_Name;
	}
	public void setAccount_Name(String account_Name) {
		this.account_Name = account_Name;
	}
	public long getBranch_Id() {
		return branch_Id;
	}
	public void setBranch_Id(long branch_Id) {
		this.branch_Id = branch_Id;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String toString(){
		return "Account Id:"+account_Id+"account_Name:"+account_Name+"branch_Id:"+branch_Id
			+"amount:"+amount+"Branch Name:"+branch_Name;
	}
	
	public boolean equals(Object obj){
		if (obj instanceof Account){
			Account account=(Account)obj;
			if(account_Name.equalsIgnoreCase(account.account_Name) && branch_Name.equalsIgnoreCase(account.branch_Name)){
				return true;
			}
		}
		return false;
	}
	
	

}
