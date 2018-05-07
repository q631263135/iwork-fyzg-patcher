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
		request.setCharacterEncoding("utf-8");
	%>

	名字:
	<%=request.getParameter("userName") %>
	<br />
	
	兴趣爱好:
	
	<%
		request.setCharacterEncoding("utf-8");
	
		String[] interest = request.getParameterValues("interest"); // 如何没取到则为null
		
		if (interest != null) {
			for (String s : interest) {
				out.print(s + "&nbsp;&nbsp;");
			}
		} else {
			
			out.print("无");
		}
		
		
	%>
	
	<p>
	getContentType：	
	<%=request.getContentType() %>
	</p>
	<p>
	getProtocol：	
	<%=request.getProtocol() %>
	</p>
	<p>
	getServerName：	
	<%=request.getServerName() %>
	</p>
	<p>
	getServletContext：	
	<%=request.getServletContext() %>
	</p>
	<p>
	getServletPath：	
	<%=request.getServletPath() %>
	</p>
	<p>
	getRealPath：	
	<%=request.getRealPath("") %>
	</p>
	<p>
	getContextPath：	
	<%=request.getContextPath() %>
	</p>
	<p>
	getLocalAddr：	
	<%=request.getLocalAddr() %>
	</p>
	<p>
	getPathInfo：	
	<%=request.getPathInfo() %>
	</p>
</body>
</html>