package Vo;

public class SeatVo {
	private String seat_ID;
	private int floor;
	private int seatnumber;
	private String status;
	private String person_ID;
	private String color;
	private String end;
	
	public String getSeat_ID() {
		return seat_ID;
	}
	public void setSeat_ID(String iD) {
		seat_ID = iD;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getSeatnumber() {
		return seatnumber;
	}
	public void setSeatnumber(int seatnumber) {
		this.seatnumber = seatnumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPerson_ID() {
		return person_ID;
	}
	public void setPerson_ID(String person_seat_ID) {
		this.person_ID = person_seat_ID;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
	public String toString() {
		return "seat_ID : " + seat_ID;
	}
	
}
