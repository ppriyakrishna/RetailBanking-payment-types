package com.retailBanking.payments.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailBanking.payments.dao.BranchDao;
import com.retailBanking.payments.domain.Branch;

@Service("branchService")
public class BranchServiceImpl implements BranchService{
	
	@Autowired
	private BranchDao branchDao;

	
	
	public Branch findBranchDetails(long branch_Id) {
		return branchDao.findBranchDetails(branch_Id);
	}

	
	public List<Branch> findAllBranchDetails() {
		return branchDao.findAllBranchDetails();
	}

	
	public long findBranchId(String branch_Name) {
		return branchDao.findBranchId(branch_Name);
	}

	

	public List<String> findAllBranchNames() {
		List<Branch> branchList=findAllBranchDetails();
		List<String>branchNameList=new ArrayList<String>();
		
		for(Branch branch:branchList){
			branchNameList.add(branch.getBranch_Name());
			
		}
		
		return branchNameList;
	}
	

}
