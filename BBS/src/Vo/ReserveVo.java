package Vo;

public class ReserveVo {
	private String ID;
	private String person_ID;
	private String seat_ID;
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
	public String getSeat_ID() {
		return seat_ID;
	}
	public void setSeat_ID(String seat_ID) {
		this.seat_ID = seat_ID;
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
