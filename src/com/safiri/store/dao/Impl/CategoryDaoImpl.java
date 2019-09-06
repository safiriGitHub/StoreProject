package com.safiri.store.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.safiri.store.dao.CategoryDao;
import com.safiri.store.domain.Category;
import com.safiri.store.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	public List<Category> findAllCategory() throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}

}
