<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="Dao.BatchDao"%>
<!DOCTYPE html>
<html>
<head>
<title>Library</title>
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
</head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
		BatchDao bat = new BatchDao();
		bat.runBatch();
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href='main.jsp'");
		script.println("</script>");
		%>
</body>
</html>