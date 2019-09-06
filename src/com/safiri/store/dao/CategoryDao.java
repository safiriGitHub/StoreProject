package com.safiri.store.dao;

import java.util.List;

import com.safiri.store.domain.Category;

public interface CategoryDao {

	List<Category> findAllCategory() throws Exception;

}
