package com.retailBanking.payments.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailBanking.payments.dao.PaymentTypeDao;
import com.retailBanking.payments.domain.PaymentType;

@Service("paymentTypeService")
public class PaymentTypeServiceImpl implements PaymentTypeService{
	
	@Autowired
	private PaymentTypeDao paymentTypeDao;
	

	
	public List<PaymentType> findAllPaymentTypes() {
		return paymentTypeDao.findAllPaymentTypes();
	}

	
	public PaymentType findPaymentTypeByCode(String type_Code) {
		return paymentTypeDao.findPaymentTypeByCode(type_Code);
	}

	
	public PaymentType findPaymentType(String type_Name) {
		return paymentTypeDao.findPaymentType(type_Name);
	}


	public List<String> findAllPaymentTypesCodes() {
		List<PaymentType>paymentTypeList=paymentTypeDao.findAllPaymentTypes();
		List<String> typeNameList=new ArrayList<String>();
		
		for(PaymentType paymentType:paymentTypeList){
			typeNameList.add(paymentType.getType_Code());
		}
		
		return typeNameList;
	}


	

}
