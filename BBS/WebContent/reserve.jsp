<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="Dao.SeatDao"%>
<%@ page import="Vo.SeatVo"%>
<%@ page import="java.util.ArrayList"%>

<%request.setCharacterEncoding("UTF-8");%>
<%
	String ID = null;
	SeatDao seatDao = new SeatDao();
	if (session.getAttribute("ID") != null) {
		ID = (String) session.getAttribute("ID");
	}
	PrintWriter script = response.getWriter();
	script.println("<script>");
	if(ID==null){
		script.println("alert('로그인을 해야합니다.')");
		script.println("location.href='login.jsp'");
	} else {
		String Seat_ID = "blah";
		if (request.getParameter("Seat_ID") != null) {
			Seat_ID = request.getParameter("Seat_ID");
			switch(seatDao.reserve(ID, Seat_ID)){
			case -1: script.println("alert('시스템 오류입니다.')"); break;
			case 0: script.println("alert('사용중인 자리입니다.')"); break;
			case 1: script.println("alert('자리 예약이 완료되었습니다.')"); break;
			case 2: script.println("alert('이미 사용중인 자리가 있습니다.')"); break;
			default : script.println("alert('오류!')"); break;
			}
			script.println("location.href='seat.jsp'");
		}
	}
	script.println("</script>");
%>