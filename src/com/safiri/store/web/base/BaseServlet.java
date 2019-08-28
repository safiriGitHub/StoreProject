package com.safiri.store.web.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet基类，反射机制实现动态调用子类方法
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//1.获得请求参数method
			String methodName = request.getParameter("method");
			//默认方法名
			if (methodName == null || "".equals(methodName) || "".equals(methodName.trim())) methodName = "execute";
			//2.获得当前运行类，需要制定的方法
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			//3.执行方法
			String jspPath = (String)method.invoke(this, request, response);
			//4.如果子类有返回值，将请求到指定的jsp页面
			if (jspPath != null) {
				request.getRequestDispatcher(jspPath).forward(request, response);
			}
		} catch (Exception e) {
			//异常处理：根据不同的异常，进行不同的处理
			// 跳转到项目特定的异常页面？？？
			throw new RuntimeException(e);
		}
		
    }
	
	/**
	 * 默认方法，用于子类复写
	 */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//NOOP
    	return null;
    }

}
