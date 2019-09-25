<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="Dao.BookDao"%>
<%@ page import="Vo.BookVo"%>
<%@ page import="java.util.ArrayList"%>

<%request.setCharacterEncoding("UTF-8");%>
<%
	String ID = null;
	BookDao bookDao = new BookDao();
	if (session.getAttribute("ID") != null) {
		ID = (String) session.getAttribute("ID");
	}
	PrintWriter script = response.getWriter();
	script.println("<script>");
	if(ID==null){
		script.println("alert('로그인을 해야합니다.')");
		script.println("location.href='login.jsp'");
	} else {
		String Book_ID = request.getParameter("Book_ID");
		if (Book_ID != null) {
			switch(bookDao.deleteBook(ID,Book_ID)){
			case 1: script.println("alert('도서 예약이 취소되었습니다.')"); break;
			case 0: script.println("alert('DB오류!')"); break;
			default : script.println("alert('입력 오류!')"); break;
			}
			script.println("location.href='mypage.jsp'");
		}
	}
	script.println("</script>");
%>