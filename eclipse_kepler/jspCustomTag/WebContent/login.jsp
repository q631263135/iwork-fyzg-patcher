<%@ page import="java.text.SimpleDateFormat, java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd mm:HH:ss");
		String dateStr = sdf.format(new Date());
	%>
	欢迎，现在时间是<%=dateStr%>
</body>
</html>