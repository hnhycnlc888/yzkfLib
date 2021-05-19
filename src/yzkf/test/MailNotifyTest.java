package yzkf.test;

import yzkf.api.Mail;
import yzkf.exception.ApiException;
import yzkf.exception.ParserConfigException;

public class MailNotifyTest {
	public static void main(String[] args) throws ParserConfigException{
		Mail api = new Mail();
		try {
			api.queryMailNotify("15920101177");
			//api.queryMailNotify("13760709457");
			//api.OpenMailNotify("15920101177");
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
