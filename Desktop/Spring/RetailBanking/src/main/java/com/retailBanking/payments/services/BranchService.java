package com.retailBanking.payments.services;

import java.util.List;

import com.retailBanking.payments.domain.Branch;

public interface BranchService {
	
	public Branch findBranchDetails(long branch_Id);
	public List<Branch> findAllBranchDetails();
	public List<String> findAllBranchNames();
	public long findBranchId(String branch_Name);	

}
