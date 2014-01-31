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

import com.retailBanking.payments.dao.PaymentTypeDao;
import com.retailBanking.payments.domain.PaymentType;

@Repository("paymetTypeDao")
public class PaymentTypeDaoImpl implements PaymentTypeDao{

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;

	private PaymentTypeRowMapper paymentTypeRowMapper;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		paymentTypeRowMapper = new PaymentTypeRowMapper();		
	}




	public List<PaymentType> findAllPaymentTypes() {
		String sql = "SELECT * FROM PaymentType";
		List<PaymentType> accountList = jdbcTemplate.query(sql, paymentTypeRowMapper);
		return accountList;
	}


	public PaymentType findPaymentTypeByCode(String type_Code) {
		String sql = "SELECT * FROM PaymentType WHERE type_Code = :type_Code";
		MapSqlParameterSource params = new MapSqlParameterSource("type_Code", type_Code);
		
		try{
			return dbTemplate.queryForObject(sql, params, paymentTypeRowMapper);
		}catch(EmptyResultDataAccessException e){
			return null;
		}

		
	}


	public PaymentType findPaymentType(String type_Name) {
		String sql = "SELECT * FROM PaymentType WHERE type_Name= :type_Name";
		MapSqlParameterSource params = new MapSqlParameterSource("type_Name", type_Name);
		PaymentType paymentType = dbTemplate.queryForObject(sql, params, paymentTypeRowMapper);

		return paymentType;
	}


}
