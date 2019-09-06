package com.safiri.store.service.Impl;

import java.util.List;

import com.safiri.store.dao.CategoryDao;
import com.safiri.store.dao.Impl.CategoryDaoImpl;
import com.safiri.store.domain.Category;
import com.safiri.store.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	public List<Category> findAllCategory() throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.findAllCategory();
	}
	
}
