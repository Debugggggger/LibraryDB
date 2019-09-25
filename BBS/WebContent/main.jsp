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
<body>
	<%
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
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class = "navbar-brand" href="main.jsp">Library</a>
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
				<li class="dropdown"><a herf="#" class="dropdown-toggle"
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
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="loginAction.jsp">
					<h3 style="text-align: center;">이용하실 서비스를 선택하세요.</h3>
					<div class="form-group">
						<a href="book.jsp"> <input type="button"
							class="btn btn-default" value="도서 예약">
						</a>
					</div>
					<div class="form-group">
						<a href="seat.jsp"> <input type="button"
							class="btn btn-default" value="자리 예약">
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>