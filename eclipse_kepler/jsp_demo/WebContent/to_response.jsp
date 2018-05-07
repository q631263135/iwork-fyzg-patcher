<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		response.getCharacterEncoding() 读取的是page指令contentType属性中的charset：<%=response.getCharacterEncoding()%>
	</p>
	
	<%--
		response.sendRedirect(request.getContextPath() + "/sendRedirect.jsp");
	--%>
	
	<%
		request.getRequestDispatcher("/forward.jsp").forward(request, response);
	%>
</body>	
</html>