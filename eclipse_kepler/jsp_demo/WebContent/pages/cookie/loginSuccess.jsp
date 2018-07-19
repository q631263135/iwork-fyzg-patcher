<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<title>login success,welcome!</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
	%>

	<jsp:useBean id="loginUser" class="javabean.User"></jsp:useBean>
	
	<jsp:setProperty property="*" name="loginUser" />
	<jsp:setProperty property="passWord" name="loginUser" param="userPassWord"/>
	
	<p>欢迎!</p>
	<p><%=loginUser.getUserName() %>，你的密码被我获取了：<%=request.getParameter("userPassWord") %></p>
</body>

</html>
