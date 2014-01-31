package com.retailBanking.payments.domain;



public class FundsTransfer {
	private Account fromAccount;
	private Account toAccount;
	private Bank  fromBank;
	private Bank toBank;
	private PaymentType paymentType;
	private double transferAmt;




	public double getTransferAmt() {
		return transferAmt;
	}
	public void setTransferAmt(double transferAmt) {
		this.transferAmt = transferAmt;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public long getFromAccount_Id(){
		return fromAccount.getAccount_Id();
	}

	public long getToAccount_Id(){
		return toAccount.getAccount_Id();
	}

	public String getFromBank_Name(){
		return fromBank.getBank_Name();
	}

	public String getToBank_Name(){
		return toBank.getBank_Name();
	}
	public String getToBankCurrency(){
		return toBank.getCurrency();
	}
	
	
	public String getPaymentTypeCode(){
		return paymentType.getType_Code();
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	public Bank getFromBank() {
		return fromBank;
	}
	public void setFromBank(Bank fromBank) {
		this.fromBank = fromBank;
	}
	public Bank getToBank() {
		return toBank;
	}
	public void setToBank(Bank toBank) {
		this.toBank = toBank;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	

	public boolean equals(Object obj){
		if (obj instanceof Account){
			Account account=(Account)obj;
			if(toAccount.equals(account)){
				return true;
			}
		}
		return false;
	}

}
