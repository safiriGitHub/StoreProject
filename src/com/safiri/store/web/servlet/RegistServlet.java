package com.safiri.store.web.servlet;

import com.safiri.store.domain.User;
import com.safiri.store.utils.MyBeanUtils;
import com.safiri.store.web.base.BaseServlet;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public String registUI(HttpServletRequest request, HttpServletResponse response) {
		return "/jsp/register.jsp";
	}
	
	public String registUser(HttpServletRequest request, HttpServletResponse response) {
		//接收表单参数
		//调用业务层注册功能
		//注册成功，向用户邮箱发送信息，跳转到提示页面
		//注册失败，跳转到提示页面
		
//		Map<String, String[]> map = request.getParameterMap();
//		//1
//		for (Map.Entry<String, String[]> entry : map.entrySet()) {
//			
//		}
//		
//		//2
//		Set<String> keySet = map.keySet();
//		Iterator<String> iterator = keySet.iterator();
//		while (iterator.hasNext()) {
//			String key = iterator.next();
//			String[] values = map.get(key);
//			for (String v : values) {
//				System.out.println(v);
//			}
//		}
		
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		return "";
	}
	

}
