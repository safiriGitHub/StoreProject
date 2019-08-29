package com.safiri.store.web.servlet;

import com.safiri.store.domain.User;
import com.safiri.store.service.UserService;
import com.safiri.store.service.Impl.UserServiceImpl;
import com.safiri.store.utils.CookieUtils;
import com.safiri.store.utils.StringUtils;
import com.safiri.store.web.base.BaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;

	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String autoLogin = request.getParameter("autoLogin");
		
		if (!StringUtils.areNotEmpty(username, password)) {
			request.setAttribute("msg", "用户名或密码不能为空");
			return "/jsp/login.jsp";
		}
		
		UserService service = new UserServiceImpl();
		Map<String, Object> map = service.loginUser(username, password);
		String code = (String)map.get("code");
		if ("1".equals(code)) {
			User user = (User)map.get("user");
			request.getSession().setAttribute("loginUser", user);
			if ("1".equals(autoLogin)) { //如果选择了自动登录，则发送cookie
				Cookie autoLoginCookie = CookieUtils.createCustomizeCookie("autoLoginCookie", user.getName()+"@"+user.getPassword());
				response.addCookie(autoLoginCookie);
			}else {
				Cookie autoLoginCookie = CookieUtils.createCustomizeCookie("autoLoginCookie", "");
				response.addCookie(autoLoginCookie);
			}
			response.sendRedirect(request.getContextPath() + "/");
		}else {
			String msg = (String)map.get("msg");
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
		
		return null;
		
	}
	
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		request.getSession().removeAttribute("loginUser");
		response.sendRedirect(request.getContextPath() + "/RegistServlet?method=loginUI");
		return null;
	}
}
