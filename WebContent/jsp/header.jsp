<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!--
     	描述：菜单栏
     -->
	<c:if test="${empty loginUser }">
		<li><a href="${pageContext.request.contextPath}/RegistServlet?method=loginUI">登录</a></li>
		<li><a href="${pageContext.request.contextPath}/RegistServlet?method=registUI">注册</a></li>
	</c:if>
	<c:if test="${not empty loginUser }">
		欢迎您：${loginUser.username }
	<li><a href="${pageContext.request.contextPath}/jsp/order_list.jsp">我的订单</a></li>
	<li><a href="${pageContext.request.contextPath}/UserServlet?method=logout">退出</a></li>
	</c:if>
	<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
	
			
</body>
</html>