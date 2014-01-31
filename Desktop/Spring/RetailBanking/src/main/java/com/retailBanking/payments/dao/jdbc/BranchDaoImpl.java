package com.retailBanking.payments.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.retailBanking.payments.dao.BankDao;
import com.retailBanking.payments.dao.BranchDao;
import com.retailBanking.payments.domain.Branch;

@Repository("branchDao")
public class BranchDaoImpl implements BranchDao{

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private BranchRowMapper branchRowMapper;

	
	@Autowired
	@Qualifier("bankDao")
	private BankDao bankDao;


	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		branchRowMapper = new BranchRowMapper();
		
	}


	
	
	/** 
	 *  find the Branch with the branch_Id  
	 */

	
	public Branch findBranchDetails(long branch_Id) {
		String sql = "SELECT * FROM Branch WHERE branch_Id = :branch_Id";
		MapSqlParameterSource params = new MapSqlParameterSource("branch_Id", branch_Id);
		Branch branch = dbTemplate.queryForObject(sql, params, branchRowMapper);

		return branch;
	}


	/**
	 * find  the branch_id from the branch_name. 
	 */

	public long findBranchId(String branch_Name) {
		
		
		String sql = "SELECT branch_Id FROM Branch WHERE branch_Name = :branch_Name";
		MapSqlParameterSource params = new MapSqlParameterSource("branch_Name", branch_Name);
		try{
			return dbTemplate.queryForLong(sql, params);
		}catch(EmptyResultDataAccessException e){
			return 0;
		}

		
	}


	/**
	 * find all the Branch details
	 */
	
	public List<Branch> findAllBranchDetails() {
		String sql = "SELECT * FROM Branch";
		List<Branch> branchList = jdbcTemplate.query(sql, branchRowMapper);
		return branchList;
	}


	

	
}
