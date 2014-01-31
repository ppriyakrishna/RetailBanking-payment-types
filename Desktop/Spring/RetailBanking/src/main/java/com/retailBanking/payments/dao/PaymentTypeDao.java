package com.retailBanking.payments.dao;

import java.util.List;

import com.retailBanking.payments.domain.PaymentType;

public interface PaymentTypeDao {
	
	public List<PaymentType> findAllPaymentTypes();
	public PaymentType findPaymentTypeByCode(String type_Code) ;
	public PaymentType findPaymentType(String type_Name);

}
