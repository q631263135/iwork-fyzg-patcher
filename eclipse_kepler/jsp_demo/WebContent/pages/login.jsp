<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="../index.jsp" name="logonForm" method="post">
		<p>页面位于上一层文件夹</p>
		<input type="submit" value="相对路径跳转" />
	</form>
	<br />
	<form action="/index.jsp" name="logonForm">
		<input type="submit" value="跳转路径前有/" />
	</form>
</body>
</html>