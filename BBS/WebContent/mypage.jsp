<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="Vo.*"%>
<%@ page import="Dao.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<title>Library</title>
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<style type="text/css">
</style>
</head>
<body>
	<%
		String ID = null;
		if (session.getAttribute("ID") != null) {
			ID = (String) session.getAttribute("ID");
		}
		if (ID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해야 이용하실 수 있습니다.')");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}
		MemberDao memberDao = new MemberDao();
		String name = memberDao.getName(ID);
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

			<ul class="nav navbar-nav navbar-right">
				<li><a href="logoutAction.jsp">로그아웃</a></li>
			</ul>

		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<%
					SeatDao seatDao = new SeatDao();
					ArrayList<SeatVo> list2 = seatDao.SelectSeat(ID);
				%>
				<div style="border: 2px solid #dddddd; padding: 30px;">
					<h3 style="text-align: center;"><%=name%> 회원님의 정보입니다.
					</h3>
					<%
						if (list2.size() != 0) {
					%>
					<div style="border: 1px solid #dddddd;">
						&nbsp;사용중인 자리는
						<%=list2.get(0).getFloor()%>층
						<%=list2.get(0).getSeatnumber()%>번 좌석입니다.
						<a href="Unreserve.jsp?Seat_ID=<%=list2.get(0).getSeat_ID()%>">
							<Button class="button">사용 취소</Button>
						</a>
					</div>
					<%
						} else {
					%>
					<div style="border: 1px solid #dddddd;">&nbsp;사용중인 자리가 없습니다.</div>
					<%
						}
					%>
					<br />
					<table class="table table-hover"
						style="text-align: center; border: 1px solid #dddddd">
						<thead>
							<tr>
								<th style="background-color: #eeeeee; text-align: center">제목</th>
								<th style="background-color: #eeeeee; text-align: center">저자</th>
								<th style="background-color: #eeeeee; text-align: center">반납예정일</th>
								<th style="background-color: #eeeeee; text-align: center">대출상태</th>
							</tr>
						</thead>
						<tbody>
							<%
								BookDao bookDao = new BookDao();
								ArrayList<BookVo> list = bookDao.SelectBook(ID);
								for (int i = 0; i < list.size(); i++) {
							%>
							<tr>
								<td><%=list.get(i).getName()%></td>
								<td><%=list.get(i).getWriter()%></td>
								<td><%=list.get(i).getEnd()%></td>
								<td><%=list.get(i).getStatus()%>
								<%
								if(list.get(i).getStatus() == "예약중"){
								%>
								<a href="Unborrow.jsp?Book_ID=<%=list.get(i).getBook_ID()%>">
								<Button class="button">취소</Button></a>
								<%
								}
								%>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>