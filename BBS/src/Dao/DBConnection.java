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
	
	// ȣ��Ǹ� DB�� ������
	public DBConnection() {
		try {
			Class.forName(driverClass);
			this.conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
		}

	}

	// DB�� �ִ� ������ �ѱ۷� ����
	public String translation(String str) {
		switch (str) {
		case "book_ID": return "�ڵ�";
		case "name": return "�̸�";
		case "writer": return "����";
		case "brand": return "���ǻ�";
		case "end": return "������";
		case "status": return "����";
		case "phone": return "�޴�����ȣ";
		case "birth": return "�������";
		case "seat_ID": return "�ڵ�";
		case "floor": return "��";
		case "seatnumber": return "�ڸ���ȣ";
		case "book" : return "å";
		case "seat" : return "�ڸ�";
		}
		return str;
	}
	
	// select�� �Ҷ� ����� ID�� ������ ����
	public String is_ID(String ID) {
		if(ID!="") return "where person_ID = \""+ID+"\"";
		return "";
	}
	
	// d�Է½� [������ ��¥]+[7������ ��¥] && t�Է½�  [������ �ð�]+[2�ð� ���� �ð�] ��ȯ
		public String[] getDate(String d_t) {
			String date[] = {"",""};
			int plus = 1000*60*60;// �ѽð� ����
			SimpleDateFormat dayTime = null;
			switch(d_t) {
			case "d":
				plus*=24*7; // ������ ��
				dayTime = new SimpleDateFormat("yyyy.MM.dd");
				break;
			case "t":
				plus*=2; // �νð� ��
				dayTime = new SimpleDateFormat("kk:mm");
				break;
			case "dt":
				plus=0;
				dayTime = new SimpleDateFormat("yyyy.MM.dd kk:mm");
				break;
			default:
				System.out.println("�ð� ���� ����");
				return null;
			}
			date[0]=dayTime.format(new Date(System.currentTimeMillis()));
			date[1]=dayTime.format(new Date(System.currentTimeMillis()+plus));
			return date;
		}
	
	// DB������ ���� �Լ�
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(this.driverClass);
			connection = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
		}
		return connection;
	}
	
	// DB�� �ݱ� ���� �Լ�
	public void closeDatabase() {
		try {
			if (conn != null) {
				// connection �ݱ�
				conn.close();
			}
			if (statement != null) {
				// statement �ݱ�
				statement.close();
			}
			if (resultSet != null) {
				// resultSet �ݱ�
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("[�ݱ� ����]\n" + e.getStackTrace());
		}
	}
}
