<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Dao.MemberDao"%>
<%@ page import="java.io.PrintWriter"%>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="member" class="Vo.MemberVo" scope="page" />
<jsp:setProperty name="member" property="*" />
<%
	String ID = null;
	if (session.getAttribute("ID") != null) {
		ID = (String) session.getAttribute("ID");
	}

	MemberDao memberDao = new MemberDao();
	int result = memberDao.login(member.getID(), member.getPW());
	PrintWriter script = response.getWriter();
	script.println("<script>");
	if (ID != null) {
		script.println("alert('이미 로그인이 되어있습니다.')");
		script.println("location.href='main.jsp'");
	}
	switch (result) {
	case 1:
		session.setAttribute("ID", member.getID());
		script.println("location.href='main.jsp'");
		break;
	case 0:
		script.println("alert('올바르지 않은 정보입니다.')");
		script.println("history.back()");
		break;
	case -1:
		script.println("alert('error')");
		script.println("history.back()");
		break;
	}
	script.println("</script>");
%>