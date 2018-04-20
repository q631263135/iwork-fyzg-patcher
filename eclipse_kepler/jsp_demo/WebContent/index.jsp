<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>九九乘法表</title>
</head>
<body>
	<h1>九九乘法表</h1>

	<%--
		for (int i = 0; i <=9; i++) {
			for (int j = i; j > 0; j--) {
				out.write(j + "*" + i + "=" + j * i + " ");
			}
			out.write("<br />");
		}
	
	--%>


	<%
		String outputStr = "";
	%>

	<%
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
				outputStr += j + "*" + i + "=" + j * i + "&nbsp;&nbsp;&nbsp;";
			}
			
			outputStr += "<br />";
		}
	%>
	
	<%=outputStr %>



	<%
		request.getRequestDispatcher("WEB-INF/pages/out.jsp").forward(request, response); 
	%>

</body>
</html>