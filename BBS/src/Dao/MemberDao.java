package Dao;

public class MemberDao extends DBConnection {
	public String getName(String ID) {
		String SQL = "SELECT name FROM library_s.member WHERE ID = ? ;";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,ID);
			rs=pstmt.executeQuery();
			
			rs.next();
			return rs.getString(1);
				
			
		} catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	// ID와 PW를 받아서 ID에 따른 PW값이 있으면 true, 아니면 false
	public int login(String ID, String PW) {
		String SQL = "SELECT PW FROM library_s.member WHERE ID = ? ";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,ID);
			rs=pstmt.executeQuery();
			
			if(rs.next() && rs.getString(1).equals(PW)) {
				return 1;
			} else {
				return 0;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Boolean join(String ID, String PW, String name, String phone, String birth) {
		try {
			pstmt = conn.prepareStatement(
					"INSERT INTO library_s.member(ID, PW, name, phone, birth) " + "VALUES (?,?,?,?,?)");
			pstmt.setString(1, ID);
			pstmt.setString(2, PW);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			pstmt.setString(5, birth);
			return pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
}
