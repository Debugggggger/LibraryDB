package Dao;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class BatchDao extends DBConnection {
	public boolean run = false;
	public void runBatch() {
		if (!run) {
			Timer ti = new Timer();
			TimerTask bat = new TimerTask() {
				public void run() {
					selectBatch();
					refresh();
				}
			};
			int time = 1000;
			ti.schedule(bat, 1, time*60);
		}
	}
	public void selectBatch() {
		String query = "SELECT * FROM library_s.batch;";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			System.out.println("======================"+getDate("d")[0]+" ������Ʈ����=======================");
			while (rs.next()) {
				System.out.print(" �ð� : "+rs.getString(2));
				System.out.print(" / ���� : "+rs.getString(3));
				System.out.print(" / ID : "+rs.getString(4));
				System.out.print(" / ȸ�� : "+rs.getString(5));
				System.out.print(" / ������� : "+rs.getString(6));
				System.out.println();
			}
			System.out.println("================================================================");
			System.out.println();
		} catch (Exception e) {
		}
	}
	public void addBatch(String division, String ID,String person_ID, String note) {
		try {
			pstmt = conn.prepareStatement(
					"INSERT INTO `library_s`.`batch` (`time`, `division`, `ID`,`person_ID`, `note`) VALUES (?, ?, ?, ?, ?);");
			pstmt.setString(1,getDate("dt")[0]);
			pstmt.setString(2, division);
			pstmt.setString(3, ID);
			pstmt.setString(4, person_ID);
			pstmt.setString(5, note);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void refresh() {
		try {
			pstmt = conn.prepareStatement("DELETE FROM library_s.batch;");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
