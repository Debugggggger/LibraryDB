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
		String Book_ID = "blah";
		if (request.getParameter("Book_ID") != null) {
			Book_ID = request.getParameter("Book_ID");
			switch(bookDao.borrow(ID, Book_ID)){
			case -1: script.println("alert('대출이 불가능합니다.')"); break;
			case 0: script.println("alert('빌릴수 없는 책입니다.')"); break;
			case 1: script.println("alert('책의 예약이 완료되었습니다.')"); break;
			case 5: script.println("alert('5권까지 대여가 가능합니다.')"); break;
			default : script.println("alert('오류!')"); break;
			}
			script.println("location.href='book.jsp'");
		}
	}
	script.println("</script>");
%>