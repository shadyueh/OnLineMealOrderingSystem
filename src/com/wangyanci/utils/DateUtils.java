package com.wangyanci.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date GetDate(int num, String type) {
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, num);
		String date = format.format(calendar.getTime());
		Date date2 = new Date();

		try {
			date2 = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2;

	}

}
