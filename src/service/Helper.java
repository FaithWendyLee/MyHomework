package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helper {

	public static Calendar str_d (String date){//�ַ�ת��Ϊ����
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		try {
			c.setTime(sdf.parse(date));
			return c;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String d_str(Calendar c){//����ת��Ϊ�ַ�
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
	
}
