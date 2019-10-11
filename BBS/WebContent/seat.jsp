<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="Vo.SeatVo"%>
<%@ page import="Dao.SeatDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<title>Library</title>
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<style type="text/css">
table {
	text-align: center;
	border: 1px solid #dddddd;
	width: 300px;
}

td {
	border: 1px solid #dddddd;
	padding: 12px;
}
a, a:hover {
	text-decoration:none;
}
</style>
<script type="text/javascript">
	function seat_click(s) {

	}
</script>
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
			<table>
				<tbody>
					<%
						int s = 6;
						int floor = 0;
						SeatDao seatDao = new SeatDao();
						ArrayList<SeatVo> list = seatDao.SelectSeat("");
						int a[] = seatDao.place[0];
						for (int i = 0; i < 8; i++) {
					%>
					<tr>
						<%
							for (int j = 0; j < s; j++) {
								int index = a[i * s + j];
									if (index > 0) {
										if (list.get(--index).getStatus() == "사용가능") {
						%>
						<td style="background-color: #eeeeee">
						<a href="reserve.jsp?Seat_ID=<%=list.get(index).getSeat_ID()%>">
								<%=list.get(index).getSeatnumber()%>
						</a>
						</td>
							<%
								} else {
							%>
						<td style="background-color: #ffaaaa">
						<%=list.get(index).getSeatnumber()%>
						</td>
							<%
								}
							%> 
						<%
									} else {
						%>
						<td style="border: 0px"></td>
						<%
							}

								}
						%>
					</tr>
					<%
						} //end print
					%>
				</tbody>
			</table>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>