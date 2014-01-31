package com.retailBanking.payments.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.retailBanking.payments.domain.PaymentType;

public class PaymentTypeRowMapper implements RowMapper<PaymentType> {

	public PaymentType mapRow(ResultSet resultSet, int row) throws SQLException {
		int type_Id;
		String type_Code;
		String type_Name;
		String desc;
		
		PaymentType paymentType=new PaymentType();
		
		type_Id=resultSet.getInt("type_Id");
		type_Name = resultSet.getString("type_Name");
		desc=resultSet.getString("description");
		type_Code=resultSet.getString("type_Code");
		
		
		paymentType.setType_Id(type_Id);
		paymentType.setType_Name(type_Name);
		paymentType.setDescription(desc);
		paymentType.setType_Code(type_Code);
		
		
		return paymentType;
	}  
 

}
