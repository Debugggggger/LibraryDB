<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="Vo.BookVo"%>
<%@ page import="Dao.BookDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<title>Library</title>
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<style type="text/css">
#content {
	width: 350px;
	height: 350px;
	background: #ededed;
	padding: 10px;
	margin: 300px;
}
table{
	text-align: center;
	border: 1px solid #dddddd;
}
th {
	background-color: #eeeeee;
	text-align: center;
}
a, a:hover {
	text-decoration:none;
}
</style>
</head>
<body>
	<%
		JSONObject obj = new JSONObject();
	
		String ID = null;
		if (session.getAttribute("ID") != null) {
			ID = (String) session.getAttribute("ID");
		}
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">Library</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="book.jsp">도서 예약</a></li>
				<li><a href="seat.jsp">자리 예약</a></li>
			</ul>
			<%
				if (ID != null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expended="false">사용자<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="mypage.jsp">마이페이지</a></li>
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul></li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login.jsp">로그인</a></li>
			</ul>
			<%
				}
			%>

		</div>
	</nav>
	<div class="container">
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>제목</th>
						<th>저자</th>
						<th>출판사</th>
						<th>대출상태</th>
						<th style="background-color: #eeeeee; text-align: center">반납예정일</th>
					</tr>
				</thead>
				<tbody>
					<%
						BookDao bookDao = new BookDao();
						ArrayList<BookVo> list = bookDao.SelectBook("");
					
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('"+ list.get(0).getBook_ID() + "');");
						script.println("</script>");
						for (int i = 0; i < list.size(); i++) {
					%>
					
					<tr id="move">
						<td><%=list.get(i).getBook_ID()%></td>
						<td><a href="borrow.jsp?Book_ID=<%=list.get(i).getBook_ID()%>">
						<%=list.get(i).getName()%></a></td>
						<td><%=list.get(i).getWriter()%></td>
						<td><%=list.get(i).getBrand()%></td>
						<td><%=list.get(i).getStatus()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>