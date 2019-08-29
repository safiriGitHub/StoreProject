package com.safiri.store.utils;



import javax.servlet.http.Cookie;

public class CookieUtils {
	/**
	 * 通过名称在cookie数组获取指定的cookie
	 * @param name cookie名称
	 * @param cookies  cookie数组
	 * @return
	 */
	public static Cookie getCookieByName(String name, Cookie[] cookies) {
		if(cookies!=null){
			for (Cookie c : cookies) {
				//通过名称获取
				if(name.equals(c.getName())){
					//返回
					return c;
				}
			}
		}
		return null;
	}
	
	/**
	 * 创建cookie，配置了Path=/ 和MaxAge=7天
	 * @param cookieName
	 * @param cookieValue
	 * @return
	 */
	public static Cookie createCustomizeCookie(String cookieName, String cookieValue) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24*7);
		return cookie;
	}
}
