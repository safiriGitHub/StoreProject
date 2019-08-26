package com.safiri.store.dao.Impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

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

}
