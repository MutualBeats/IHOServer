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

	public  static String getYesterdayDate() {
		// TODO
		return null;
	}
}
