package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helper {

	public static Calendar str_d (String date){//字符转化为日期
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
	
	public static String d_str(Calendar c){//日期转化为字符
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
	
}
