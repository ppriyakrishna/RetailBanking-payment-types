package com.retailBanking.payments.dao;

import java.util.List;

import com.retailBanking.payments.domain.Branch;

public interface BranchDao {
	
	
	public Branch findBranchDetails(long branch_Id);
	public List<Branch> findAllBranchDetails();
	public long findBranchId(String branch_Name);

	

}
