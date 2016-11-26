package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = df.format(new Date());
		return time;
	}
	
	public static String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String date = df.format(new Date());
		return date;
	}

	public static String getCurrentYear() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(new Date());
		return year;
	}

	public static String getCurrentMonth() {
		SimpleDateFormat df = new SimpleDateFormat("MM");
		String month = df.format(new Date());
		return month;
	}

	public static String getCurrentDay() {
		SimpleDateFormat df = new SimpleDateFormat("dd");
		String date = df.format(new Date());
		return date;
	}

	// 若传入的time1为null或者""，返回“1970/01/01 0:0:0”
	public static String jdugeTime1(String time1) {
		if (time1 != null && !time1.equals("")) {
			return time1;
		} else {
			return "1970/01/01 00:00:00";
		}
	}

	// 若传入的time2为null或者""，返回现在的时间
	public static String jdugeTime2(String time2) {
		if (time2 != null && !time2.equals("")) {
			return time2;
		} else {
			return getCurrentTime();
		}
	}
}
