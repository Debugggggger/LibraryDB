package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import Vo.BookVo;

public class BookDao extends DBConnection {
	
	// Select를 위한 함수
	public ArrayList<BookVo> SelectBook(String person_ID) {
		String query = "SELECT book.*,borrow.status ,borrow.person_ID, borrow.end\r\n" + "FROM book\r\n"
				+ "LEFT outer join borrow\r\n" + "on book.book_ID = borrow.book_ID\r\n" + is_ID(person_ID) + "\r\n"
				+ "order by book.book_ID;";
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookVo book = new BookVo();
				book.setBook_ID(rs.getString(1));
				book.setName(rs.getString(2));
				book.setWriter(rs.getString(3));
				book.setBrand(rs.getString(4));
				if(rs.getString(5)==null) book.setStatus("예약가능");
				else book.setStatus(rs.getString(5));
				book.setPerson_ID(rs.getString(6));
				book.setEnd(rs.getString(7));
				list.add(book);
			}

		} catch (Exception e) {
			System.out.println("도서 디비에러!");
		}
		return list;
	}
	
	
	// 책 예약을 위한 함수
	public int borrow(String ID, String book) {
		try {
			pstmt = conn.prepareStatement(
					"INSERT INTO `library_s`.`borrow` (`person_ID`, `book_ID`, `start`, `end`, `status`)"
							+ " VALUES (?, ?, ?, ?, '예약중');");
			pstmt.setString(1, ID);
			pstmt.setString(2, book);
			String[] date = getDate("d");
			pstmt.setString(3, date[0]);
			pstmt.setString(4, date[1]);
			pstmt.executeUpdate();
			new BatchDao().addBatch("도서", book, ID, "도서 예약");
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	//도서 취소를 위한 함수
	public int deleteBook(String ID,String book){
		try {
			pstmt = conn.prepareStatement("DELETE FROM `library_s`.`borrow` WHERE (`person_ID` = ?) and (`book_ID` = ?);");
			pstmt.setString(1, ID);
			pstmt.setString(2, book);
			pstmt.executeUpdate();
			new BatchDao().addBatch("도서", book, ID, "도서 취소");
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// 입력한 String이 오늘날짜인지 확인하는 함수
	public boolean is_today(String date1) {
		String date2 = getDate("d")[0];
		if(date1.equals(date2)) return true;
		return false;
	}
}
