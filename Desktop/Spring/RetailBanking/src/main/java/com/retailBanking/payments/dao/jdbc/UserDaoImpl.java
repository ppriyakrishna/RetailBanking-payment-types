package com.retailBanking.payments.dao.jdbc;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.retailBanking.payments.dao.UserDao;
import com.retailBanking.payments.domain.User;

@Repository("userDaoeDao")
public class UserDaoImpl implements UserDao{
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private UserRowMapper userRowMapper;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		userRowMapper = new UserRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		.withTableName("User")
		.usingGeneratedKeyColumns("user_Id")
		.usingColumns("userName", "lastName","firstName","password","age","gender");
	}


	public void addNewUser(User user) {
	
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
	
		Number newId = jdbcInsert.executeAndReturnKey(params);
		user.setUser_Id(newId.intValue());
		
	}
	
	public User getUser(String userName) {
		
		
		
		String sql = "SELECT * FROM User WHERE userName = :userName";
		MapSqlParameterSource params = new MapSqlParameterSource("userName", userName);
		try{
			return  dbTemplate.queryForObject(sql, params, userRowMapper);
		}catch(EmptyResultDataAccessException e){
			return null;
		}

		
		
	}


	public User getUser(int user_Id) {
		
		String sql = "SELECT * FROM User WHERE user_Id = :user_Id";
		MapSqlParameterSource params = new MapSqlParameterSource("user_Id", user_Id);
		try{
			return  dbTemplate.queryForObject(sql, params, userRowMapper);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	

}
