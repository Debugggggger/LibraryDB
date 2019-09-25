<%@page import="org.json.simple.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="Vo.BookVo"%>
<%@ page import="Dao.BookDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<%
BookDao bookDao = new BookDao();
ArrayList<BookVo> list = bookDao.SelectBook("");

JSONArray obj = new JSONArray();
String Book_ID = "blah";
if (request.getParameter("Book_ID") != null) Book_ID = request.getParameter("Book_ID");
for(int i=0; i<list.size(); i++) {
	JSONObject obj2 = new JSONObject();
	
	obj2.put("book_ID", list.get(i).getBook_ID());
	obj2.put("name", list.get(i).getName());
	obj2.put("writer", list.get(i).getWriter());
	obj2.put("brand", list.get(i).getBrand());
	obj2.put("status", list.get(i).getStatus());
	obj2.put("person_ID", list.get(i).getPerson_ID());
	obj2.put("end", list.get(i).getEnd());
	obj.add(obj2);
	
}


String book = obj.toString();
%>

<%=obj %>
	
</body>
</html>