package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import Vo.SeatVo;

public class SeatDao extends DBConnection {
	
	// 자리를 출력하기 위한 함수
	public int[][] place = { {1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0,
			0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0},{} };

	// Select를 위한 함수
	public ArrayList<SeatVo> SelectSeat(String person_ID) {
		String query = "SELECT s.*, r.status, r.person_ID, r.end\r\n" + "FROM seat as s\r\n"
				+ "LEFT OUTER JOIN reserve as r\r\n" + "on s.seat_ID = r.seat_ID\r\n" + is_ID(person_ID) + "\r\n"
				+ "order by s.seat_ID;";

		ArrayList<SeatVo> list = new ArrayList<SeatVo>();
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SeatVo seat = new SeatVo();
				seat.setSeat_ID(rs.getString(1));
				seat.setFloor(rs.getInt(2));
				seat.setSeatnumber(rs.getInt(3));
				if(rs.getString(4)==null) seat.setStatus("사용가능");
				else seat.setStatus(rs.getString(4));
				seat.setPerson_ID(rs.getString(5));
				if (seat.getStatus() == "사용가능") seat.setColor("black");
				else seat.setColor("red");
				seat.setEnd(rs.getString(6));
				list.add(seat);
			}
//			for(SeatVo s:list) {
//				System.out.println(s.getColor());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 자리예약을 위한 함수
	public int reserve(String ID, String seat) {
		try {
			ArrayList<SeatVo> list = SelectSeat("");
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getSeat_ID() == seat && list.get(i).getStatus() != "사용가능") {
					System.out.println("이미잇음");
					return 0;
				}
			}
			
			pstmt = conn.prepareStatement(
					"INSERT INTO `library_s`.`reserve` (`person_ID`, `seat_ID`, `start`, `end`, `status`)"
					+ "VALUES (?, ?, ?, ?, '사용중');");
			pstmt.setString(1, ID);
			pstmt.setString(2, seat);
			String[] time = getDate("t");
			pstmt.setString(3, time[0]);
			pstmt.setString(4, time[1]);
			pstmt.executeUpdate();
			new BatchDao().addBatch("자리", seat, ID,"자리 예약");
			return 1;
		} catch (Exception e) {
			
			return -1;
		}
	}
	
	//자리 취소를 위한 함수
	public int deleteSeat(String ID,String seat){
		try {
			pstmt = conn.prepareStatement("DELETE FROM `library_s`.`reserve` WHERE (`person_ID` = ?) and (`seat_ID` = ?);");
			pstmt.setString(1, ID);
			pstmt.setString(2, seat);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	// 페이지 변환을 위한 함수
	public ArrayList<SeatVo> PartiSeat(int floor) {
		ArrayList<SeatVo> list = new ArrayList<SeatVo>();
		ArrayList<SeatVo> list2 = new ArrayList<SeatVo>();
		list = SelectSeat("");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFloor() == 1) {
				list2.add(list.get(i));
			}
		}
		return list2;
	}
}