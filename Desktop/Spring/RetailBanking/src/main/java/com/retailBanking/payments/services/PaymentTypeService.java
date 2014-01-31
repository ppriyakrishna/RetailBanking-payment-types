package com.retailBanking.payments.services;

import java.util.List;

import com.retailBanking.payments.domain.PaymentType;

public interface PaymentTypeService {
	public List<PaymentType> findAllPaymentTypes();
	public PaymentType findPaymentTypeByCode(String type_Code);
	public PaymentType findPaymentType(String type_Name);
	public List<String> findAllPaymentTypesCodes();
}
