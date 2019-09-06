package com.safiri.store.dao;

import java.sql.SQLException;

import com.safiri.store.domain.User;

public interface UserDao {

	int registUser(User user) throws SQLException;
	
	User loginUser(String username, String password) throws SQLException;
	
	long findUserExist(String username) throws SQLException;
}
