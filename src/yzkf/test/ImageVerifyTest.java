package yzkf.test;

import yzkf.api.VerifyCode;
import yzkf.api.result.VerifyResult;
import yzkf.exception.ApiException;
import yzkf.exception.ParserConfigException;

public class ImageVerifyTest {
 public static void main(String[] args){
	 VerifyResult verifyResult;
	try {
		verifyResult = VerifyCode.validate("01641fe5-fa5f-49bd-82c3-4a67ad64e332", "u", "13760709457", "127.0.0.1");
		System.out.println(verifyResult.getDescr());
	} catch (ParserConfigException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 
 }
}
