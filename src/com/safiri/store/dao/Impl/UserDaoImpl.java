package com.safiri.store.dao.Impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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

	public long findUserExist(String username) throws SQLException {
		String sql = "select count(*) from user where username=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Object query = runner.query(sql, new ScalarHandler(), username);
		return (Long)query;
				
//				 object类型转换为int类型：
//				 1.如果object是byte,short,int,char类型生成的，那么不用转换直接赋值就ok了。
//				 2.如果object是字符串类型生成的，先把object转换为String类型的，再把String类型转换为int类型。
//				 例如.
//				 String myInt="123";
//				  Object os=myInt;
//				  int b=Integer.parseInt((String)os);//还可以os.toString()
//				 3.如果object是float,double,long类型生成的，思路和上面一样，先把object转换为相应的数据类型，然后再转换为int类型。
	}

}
