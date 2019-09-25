<%@page import="org.json.simple.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="Vo.SeatVo"%>
<%@ page import="Dao.SeatDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<%
SeatDao seatDao = new SeatDao();
ArrayList<SeatVo> list = seatDao.SelectSeat("");

JSONArray obj = new JSONArray();
for(int i=0; i<list.size(); i++) {
	JSONObject obj2 = new JSONObject();
	
	obj2.put("seat_ID", list.get(i).getSeat_ID());
	obj2.put("name", list.get(i).getFloor());
	obj2.put("writer", list.get(i).getSeatnumber());
	obj2.put("status", list.get(i).getStatus());
	obj2.put("person_ID", list.get(i).getPerson_ID());
	obj2.put("brand", list.get(i).getColor());
	obj2.put("end", list.get(i).getEnd());
	obj.add(obj2);
}


String seat = obj.toString();
%>

<%=obj %>
	
</body>
</html>