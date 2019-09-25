package Vo;

public class BookVo {
	private String book_ID;
	private String name;
	private String writer;
	private String brand;
	private String status;
	private String person_ID;
	private String end;

	public String getBook_ID() {
		return book_ID;
	}

	public void setBook_ID(String iD) {
		book_ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public void setPerson_ID(String person_ID) {
		this.person_ID = person_ID;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}
