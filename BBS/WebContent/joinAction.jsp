<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Dao.MemberDao"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="member" class="Vo.MemberVo" scope="page" />
<jsp:setProperty name="member" property="*" />
<%
	PrintWriter script = response.getWriter();
	script.println("<script>");
	if (member.getID() == null || member.getPW() == null | member.getName() == null
			|| member.getPhone() == null) {
		script.println("alert('아이디, 비밀번호, 이름, 전화번호는 반드시 입력해야 합니다.')");
		script.println("history.back()");
	} else {
		MemberDao memberDao = new MemberDao();
		boolean result = memberDao.join(member.getID(), member.getPW(), member.getName(), member.getPhone(),
				member.getBirth());
		if (result) {
			script.println("alert('회원가입에 성공하였습니다.')");
			script.println("location.href='login.jsp'");
		} else {
			script.println("alert('이미 존재하는 아이디입니다.')");
			script.println("history.back()");
		}
		
	}
	script.println("</script>");
%>