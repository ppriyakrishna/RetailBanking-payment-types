package com.retailBanking.payments.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.retailBanking.payments.domain.Branch;

public class BranchRowMapper implements RowMapper<Branch> {

	public Branch mapRow(ResultSet resultSet, int row) throws SQLException {
		int branch_Id;
		String branch_Name;
		int bank_Id;
		String state_Name;
		int zipCode;
		
		Branch branch=new Branch();
		
		branch_Id=resultSet.getInt("branch_Id");
		bank_Id=resultSet.getInt("bank_Id");
		branch_Name = resultSet.getString("branch_Name");
		state_Name = resultSet.getString("state_Name");
		zipCode=resultSet.getInt("zipCode");
		
		branch.setBranch_Id(branch_Id);
		branch.setBranch_Name(branch_Name);
		branch.setBank_Id(bank_Id);
		branch.setState_Name(state_Name);
		branch.setZipCode(zipCode);
		
		return branch;
	}  

}
