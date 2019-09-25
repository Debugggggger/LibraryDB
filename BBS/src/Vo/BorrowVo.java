package Vo;

public class BorrowVo {
	private String ID;
	private String person_ID;
	private String book_ID;
	private String start;
	private String end;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPerson_ID() {
		return person_ID;
	}
	public void setPerson_ID(String person_ID) {
		this.person_ID = person_ID;
	}
	public String getBook_ID() {
		return book_ID;
	}
	public void setBook_ID(String book_ID) {
		this.book_ID = book_ID;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}

}
