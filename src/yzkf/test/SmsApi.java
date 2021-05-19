package yzkf.test;

import yzkf.api.SMS;
import yzkf.api.result.SMSResult;
import yzkf.exception.ApiException;
import yzkf.exception.ParserConfigException;

public class SmsApi {
	public static void main(String[] args){
		SMS api;
		try {
			api = new SMS();
			api.setClientIP("127.0.0.1");
			api.setSendMobile("13760709457");
			api.setSendMsg("测试发送短息java版");
			api.addReceive("13760709457");
			SMSResult result = api.send();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
