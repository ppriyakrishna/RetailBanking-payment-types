package com.retailBanking.payments.domain;

public class PaymentType {
	private int type_Id;
	private String type_Code;
	
	private String type_Name;
	private String description;

	public String getType_Code() {
		return type_Code;
	}
	public void setType_Code(String type_Code) {
		this.type_Code = type_Code;
	} 

	public int getType_Id() {
		return type_Id;
	}
	public void setType_Id(int type_Id) {
		this.type_Id = type_Id;
	}
	public String getType_Name() {
		return type_Name;
	}
	public void setType_Name(String type_Name) {
		this.type_Name = type_Name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	


}
