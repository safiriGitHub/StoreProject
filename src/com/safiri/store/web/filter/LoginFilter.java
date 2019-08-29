package com.safiri.store.web.filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.safiri.store.domain.User;
import com.safiri.store.service.UserService;
import com.safiri.store.service.Impl.UserServiceImpl;
import com.safiri.store.utils.CookieUtils;

public class LoginFilter implements Filter {

    public LoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		//0 强转
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		//0如果是登录页就直接放行
		String servletPath = request.getServletPath();
		if (servletPath.startsWith("/UserServlet")) {
			String method = request.getMethod();
			if ("loginUI".equals(method)) {
				chain.doFilter(request, response);
				return;
			}
		}
		
		//1用户登录信息
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		
		//2如果已登录，不需要自动登录 放行
		if (loginUser != null) {
			chain.doFilter(request, response);
			return;
		}
		
		//未登录
		//3获得自动登录所需的cookie信息
		Cookie userCookie = CookieUtils.getCookieByName("autoLoginCookie", request.getCookies());
		
		//4判断自动登录cookie是否存在，如果没有cookie，无需自动登录
		if (userCookie == null) {
			chain.doFilter(request, response);
			return;
		}
		
		//5存在
		
		try {
			//通过cookie查询用户
			String[] u = userCookie.getValue().split("@");
			String username = u[0];
			String password = u[1];
			UserService userService = new UserServiceImpl();
			Map<String, Object> map = userService.loginUser(username, password);
			String code = (String)map.get("code");
			if ("1".equals(code)) {
				User user = (User)map.get("user");
				request.getSession().setAttribute("loginUser", user);
				chain.doFilter(request, response);
			}else {
				chain.doFilter(request, response);
				return;
			}
			
		} catch (SQLException e) {
			System.out.println("自动登录异常，自动忽略");
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
