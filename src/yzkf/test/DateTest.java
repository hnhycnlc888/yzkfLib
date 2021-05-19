package yzkf.test;

import java.util.Calendar;

public class DateTest {

	public static void main(String[] args){
		Calendar now=Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, 1);
		now.set(Calendar.HOUR_OF_DAY,0);
		now.set(Calendar.MINUTE,0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		//now.set(2014, 4,1);
		System.out.println(now.getTime());
	}
}
