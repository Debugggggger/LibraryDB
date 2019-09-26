package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import Vo.SeatVo;

public class SeatDao extends DBConnection {
	
	// 자리를 출력하기 위한 함수
	public int[][] place = { {1, 2, 3, 0, 0, 0,
							  4, 5, 6, 0, 0, 0,
							  0, 0, 0, 0,19,20,
							  7, 8, 9, 0,21,22,
							 10,11,12, 0,23,24,
							  0, 0, 0, 0,25,26,
							 13,14,15, 0, 0, 0,
							 16,17,18, 0, 0, 0},
			
							 {1, 0, 9,10, 0,23,
							  2, 0,11,12, 0,24,
							  3, 0,13,14, 0,25,
							  4, 0, 0, 0, 0,26,
							  5, 0,15,16, 0,27,
							  6, 0,17,18, 0,28,
							  7, 0,19,20, 0,29,
							  8, 0,21,22, 0,30,
							  } };
	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 자리예약을 위한 함수
	public int reserve(String ID, String seat) {
		try {
			// 사용이 가능한 자리인지 판별
			ArrayList<SeatVo> list = SelectSeat("");
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getSeat_ID() == seat && list.get(i).getStatus() != "사용가능") {
					System.out.println("이미잇음");
					return 0;
				}
			}
			// 자리 대여가 가능한 회원인지 판별
			list = SelectSeat(ID);
			System.out.println(list.get(0));
			System.out.println("here?");
			if(list.size()==0) {
				
			pstmt = conn.prepareStatement(
					"INSERT INTO `library_s`.`reserve` (`person_ID`, `seat_ID`, `start`, `end`, `status`)"
							+ "VALUES (?, ?, ?, ?, '사용중');");
			pstmt.setString(1, ID);
			pstmt.setString(2, seat);
			String[] time = getDate("t");
			pstmt.setString(3, time[0]);
			pstmt.setString(4, time[1]);
			pstmt.executeUpdate();
			new BatchDao().addBatch("자리", seat, ID, "자리 예약");
			return 1;
			}else {
				return 2;
			}
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
			new BatchDao().addBatch("자리", seat, ID, "자리 취소");
			return 1;
		} catch (SQLException e) {
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
	
	// 작업효율을 높이기 위해 뻘짓하는 함수
	public String time_is_gold(int floor, ArrayList<SeatVo> list, int a[]) {
		
		return "";
	}
}