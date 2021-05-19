package yzkf.test;

import yzkf.api.Mail;
import yzkf.api.result.MailResult;
import yzkf.exception.ApiException;
import yzkf.exception.ParserConfigException;

public class MailBillTest {
	public static void main(String[] args) throws ParserConfigException{
		Mail api = new Mail();
		MailResult result = null;
		try {
			
			result = api.queryBillStatus("13951643481");
			System.out.println(result.toString());
			result = api.openBill("13951643481", true);
			System.out.println(result.toString());
			result =  api.cancelBill("13951643481", true);
			System.out.println(result.toString());
			//result = api.queryBillStatus("13760709457");
			//System.out.println(result.toString());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result.getDescr());
	}
}
