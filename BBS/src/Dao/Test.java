package Dao;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Test {
	// 현재의 날짜와 시간 + d입력시 7일후의 날짜/t입력시 2시간 후의 시간 반환
	public String[] getDate(String d_t) {
		String date[] = {"",""};
		int plus = 60*60*1000;// 한시간 단위
		SimpleDateFormat dayTime = null;
		switch(d_t) {
		case "d":
			plus*=24*7; // 일주일 뒤
			dayTime = new SimpleDateFormat("yyyy.MM.dd");
			System.out.println(date[0]=dayTime.format(new Date(System.currentTimeMillis())));
			System.out.println(date[1]=dayTime.format(new Date(System.currentTimeMillis()+plus)));
			break;
		case "t":
			plus*=2; // 두시간 뒤
			dayTime = new SimpleDateFormat("kk:mm");
			System.out.println(date[0]=dayTime.format(new Date(System.currentTimeMillis())));
			System.out.println(date[1]=dayTime.format(new Date(System.currentTimeMillis()+plus)));
			break;
		default:
			System.out.println("시간 설정 오류");
		}
		
		
		return date;
	}
	public static void main(String[] args) {
		new Test().getDate("d");
		new Test().getDate("t");
	}
}
