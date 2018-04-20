<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		out.println("床前明月光");
		out.println("地下鞋两双");
		out.println("床上狗男女");
		out.println("其中就有你");
	%>
	
	<%=out.getBufferSize() %>
	<%=out.getRemaining() %>
	
	<%
		out.flush();
	%>
	
	<%=out.getBufferSize() %>
	<%=out.getRemaining() %>
	
	<%
		out.write(97);
	
		out.print("<br />");
	
		out.print(97);
	%>
</body>
</html>