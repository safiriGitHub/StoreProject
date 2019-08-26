package com.safiri.store.service.Impl;

import java.sql.SQLException;

import com.safiri.store.dao.UserDao;
import com.safiri.store.dao.Impl.UserDaoImpl;
import com.safiri.store.domain.User;
import com.safiri.store.service.UserService;

public class UserServiceImpl implements UserService{

	public boolean registUser(User user) throws SQLException {
		
		UserDao dao = new UserDaoImpl();
		return dao.registUser(user) == 1;
	}

}
