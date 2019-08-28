package com.safiri.store.dao.Impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.safiri.store.dao.UserDao;
import com.safiri.store.domain.User;
import com.safiri.store.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	public int registUser(User user) throws SQLException {
		
		String sql = "INSERT INTO user (uid,username,password,name,email,telephone,birthday,sex,state,code) VALUES (?,?,?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {
				user.getUid(),user.getUsername(),user.getPassword(),
				user.getName(),user.getEmail(),user.getTelephone(),
				user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		return runner.update(sql, params);
	}

	public User loginUser(String username, String password) throws SQLException {
		
		String sql = "select * from user where username=? and password=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), username, password);
		
	}

}
