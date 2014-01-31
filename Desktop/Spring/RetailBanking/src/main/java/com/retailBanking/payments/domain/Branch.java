package com.retailBanking.payments.domain;

public class Branch {
	
	private int branch_Id;
	private String branch_Name;
	private int bank_Id;
	private String state_Name;
	private int zipCode;
	
	
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getState_Name() {
		return state_Name;
	}
	public void setState_Name(String state_Name) {
		this.state_Name = state_Name;
	}
	
	public long getBranch_Id() {
		return branch_Id;
	}
	public void setBranch_Id(int branch_Id) {
		this.branch_Id = branch_Id;
	}
	public String getBranch_Name() {
		return branch_Name;
	}
	public void setBranch_Name(String branch_Name) {
		this.branch_Name = branch_Name;
	}
	public int getBank_Id() {
		return bank_Id;
	}
	public void setBank_Id(int bank_Id) {
		this.bank_Id = bank_Id;
	}
	
	
	
	

}
