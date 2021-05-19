package yzkf.test;

import yzkf.utils.MD5;

public class TempTest {

	public static void main(String[] args){
		 String sign = MD5.encode("13570475605"
					+"fengxingoffice"
					+"463304596"
					+"ad1k7ikPZFBn8GYy");
		 System.out.println(sign);
	}
}
