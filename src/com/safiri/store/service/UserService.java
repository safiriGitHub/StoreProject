package com.safiri.store.service;

import java.sql.SQLException;
import java.util.Map;

import com.safiri.store.domain.User;

public interface UserService {

	boolean registUser(User user) throws SQLException;
	
	/**
	 * 登录用户
	 * @return code-1,msg-登录成功 ; code-2,msg-用户名或密码错误
	 * @throws SQLException
	 */
	Map<String, Object> loginUser(String username,  String password) throws SQLException;

}
