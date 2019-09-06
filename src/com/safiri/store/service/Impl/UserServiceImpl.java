package com.safiri.store.service.Impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.safiri.store.dao.UserDao;
import com.safiri.store.dao.Impl.UserDaoImpl;
import com.safiri.store.domain.User;
import com.safiri.store.service.UserService;

public class UserServiceImpl implements UserService{

	public boolean registUser(User user) throws SQLException {
		
		UserDao dao = new UserDaoImpl();
		return dao.registUser(user) == 1;
	}

	public Map<String, Object> loginUser(String username, String password) throws SQLException {
		
		UserDao dao = new UserDaoImpl();
		User user = dao.loginUser(username, password);
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == user) {
			map.put("code", "2");
			map.put("msg", "用户名或密码错误,请重试");
		} else {
			map.put("code", "1");
			map.put("msg", "登录成功！");
			map.put("user", user);
		}
		return map;
	}

	public long findUserExist(String username) throws SQLException {
		UserDao dao = new UserDaoImpl();
		
		return dao.findUserExist(username);
	}
	
	
}
