package Dao;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Test {
	// ������ ��¥�� �ð� + d�Է½� 7������ ��¥/t�Է½� 2�ð� ���� �ð� ��ȯ
	public String[] getDate(String d_t) {
		String date[] = {"",""};
		int plus = 60*60*1000;// �ѽð� ����
		SimpleDateFormat dayTime = null;
		switch(d_t) {
		case "d":
			plus*=24*7; // ������ ��
			dayTime = new SimpleDateFormat("yyyy.MM.dd");
			System.out.println(date[0]=dayTime.format(new Date(System.currentTimeMillis())));
			System.out.println(date[1]=dayTime.format(new Date(System.currentTimeMillis()+plus)));
			break;
		case "t":
			plus*=2; // �νð� ��
			dayTime = new SimpleDateFormat("kk:mm");
			System.out.println(date[0]=dayTime.format(new Date(System.currentTimeMillis())));
			System.out.println(date[1]=dayTime.format(new Date(System.currentTimeMillis()+plus)));
			break;
		default:
			System.out.println("�ð� ���� ����");
		}
		
		
		return date;
	}
	public static void main(String[] args) {
		new Test().getDate("d");
		new Test().getDate("t");
	}
}
