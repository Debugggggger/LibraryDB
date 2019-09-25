<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Dao.MemberDao"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="member" class="Vo.MemberVo" scope="page" />

<%
	session.invalidate();
%>
<script>
	location.href = 'main.jsp';
</script>