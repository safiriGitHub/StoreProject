package com.safiri.store.web.servlet;

import com.alibaba.fastjson.JSON;
import com.safiri.store.domain.Category;
import com.safiri.store.service.CategoryService;
import com.safiri.store.service.Impl.CategoryServiceImpl;
import com.safiri.store.utils.NetResponse;
import com.safiri.store.web.base.BaseServlet;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoryServlet extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//查询db中的分类数据
		CategoryService service = new CategoryServiceImpl();
		List<Category> list = service.findAllCategory();
		//转成json字符串
		String json = JSON.toJSONString(list);
		NetResponse res = new NetResponse(response);
		res.writeString(json);
	}


}
