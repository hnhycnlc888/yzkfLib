package yzkf.test;

import yzkf.api.Calendar;
import yzkf.api.result.CalendarResult;
import yzkf.exception.ApiException;

public class CalendarTest {
	public static void main(String[] args){
		Calendar api = new Calendar();
		try {
			CalendarResult result = api.subscribeLabel("13418161431", 5380);
			if(result.isOK()){
				System.out.println("订阅成功");
			}else{
				System.out.println("订阅成失败:"+result.getDescr());
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
