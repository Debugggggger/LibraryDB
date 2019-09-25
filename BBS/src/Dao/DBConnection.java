package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {
	Connection conn;
	ResultSet rs;
	PreparedStatement pstmt;
	Statement statement;
	ResultSet resultSet;
	Map<String,Object> result= new HashMap<>();

	String driverClass = "com.mysql.cj.jdbc.Driver";
	String ip = "127.0.0.1";
	String port = "3306";
	String database = "library_s";

	String dbID = "root";
	String dbPW = "root";
	String dbURL = "jdbc:mysql://" + ip + ":" + port + "/" + database
			+ "?serverTimezone=UTC&autoReconnect=true&useSSL=false";
	
	// 호출되면 DB를 연결함
	public DBConnection() {
		try {
			Class.forName(driverClass);
			this.conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
		}

	}

	// DB에 있는 영문을 한글로 번역
	public String translation(String str) {
		switch (str) {
		case "book_ID": return "코드";
		case "name": return "이름";
		case "writer": return "저자";
		case "brand": return "출판사";
		case "end": return "만기일";
		case "status": return "상태";
		case "phone": return "휴대폰번호";
		case "birth": return "생년월일";
		case "seat_ID": return "코드";
		case "floor": return "층";
		case "seatnumber": return "자리번호";
		case "book" : return "책";
		case "seat" : return "자리";
		}
		return str;
	}
	
	// select를 할때 사람의 ID를 넣을지 결정
	public String is_ID(String ID) {
		if(ID!="") return "where person_ID = \""+ID+"\"";
		return "";
	}
	
	// d입력시 [현재의 날짜]+[7일후의 날짜] && t입력시  [현재의 시간]+[2시간 후의 시간] 반환
		public String[] getDate(String d_t) {
			String date[] = {"",""};
			int plus = 1000*60*60;// 한시간 단위
			SimpleDateFormat dayTime = null;
			switch(d_t) {
			case "d":
				plus*=24*7; // 일주일 뒤
				dayTime = new SimpleDateFormat("yyyy.MM.dd");
				break;
			case "t":
				plus*=2; // 두시간 뒤
				dayTime = new SimpleDateFormat("kk:mm");
				break;
			case "dt":
				plus=0;
				dayTime = new SimpleDateFormat("yyyy.MM.dd kk:mm");
				break;
			default:
				System.out.println("시간 설정 오류");
				return null;
			}
			date[0]=dayTime.format(new Date(System.currentTimeMillis()));
			date[1]=dayTime.format(new Date(System.currentTimeMillis()+plus));
			return date;
		}
	
	// DB연결을 위한 함수
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(this.driverClass);
			connection = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
		}
		return connection;
	}
	
	// DB를 닫기 위한 함수
	public void closeDatabase() {
		try {
			if (conn != null) {
				// connection 닫기
				conn.close();
			}
			if (statement != null) {
				// statement 닫기
				statement.close();
			}
			if (resultSet != null) {
				// resultSet 닫기
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("[닫기 오류]\n" + e.getStackTrace());
		}
	}
}
