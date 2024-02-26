package taito.com.calorie.utils;

import static java.util.stream.Collectors.toList;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.type.LocalDateType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

public class ApplicationHelper {

	public static Object getDaoObject(Class<?> obj) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Object o = context.getBean(obj);
		return o;
	}
	
	public static void prt(Object obj) {
		System.out.println(obj);
	}

	
	public static List<LocalDate> getDaysOfCurrentWeek() {
	    LocalDate currDate = LocalDate.now();
	    List<LocalDate> weekData = Arrays.asList(DayOfWeek.values()).stream().map(currDate::with).collect(toList());
	    return weekData;
	}

	public static List<LocalDate> getDaysOfCurrentMonth() {
		List<LocalDate> monthdays = new ArrayList<>(); 
		Calendar c = Calendar.getInstance();
		Month month = LocalDate.now().getMonth();
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
        for(int i=1;i<=monthMaxDays;i++) {
        	LocalDate d = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),i);
        	monthdays.add(d);
        }
		
		
		return monthdays;
	}
	
	public static Double fixValue(String value) {
		Double ret = null;
		if(null == value  || value.equals("")) {
			return 0.0;
		}
		value = value.replace(',', '.');
		ret = Double.parseDouble(value);
		return ret;
	}
	
}
