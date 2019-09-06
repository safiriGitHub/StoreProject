package com.safiri.store.service;

import java.util.List;

import com.safiri.store.domain.Category;

public interface CategoryService {

	List<Category> findAllCategory() throws Exception;


}
