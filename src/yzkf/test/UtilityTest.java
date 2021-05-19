package yzkf.test;

import java.io.UnsupportedEncodingException;

import yzkf.utils.MD5;
import yzkf.utils.Utility;

public class UtilityTest {
	public static void main(String[] args) throws  UnsupportedEncodingException{
//		System.out.print(Utility.getStrByteLen("123abc你好！２1"));
//		String[] arr = new String[]{"1","2","3"};
//		System.out.print(Utility.getArrayStr(arr,","));
//		System.out.println(Utility.getTimeSpan(0));
//		String outMobile = "8613912345678";
//		if(outMobile.startsWith("86"))
//			outMobile = outMobile.substring(2);
//		System.out.println(outMobile);
		
		System.out.println(MD5.encode("qiuliwen"));
		System.out.println(MD5.encode("admin"));
		System.out.println(MD5.encode("admin139mail"));
	}
}
