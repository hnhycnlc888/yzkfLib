package yzkf.test;

import java.util.UUID;

import yzkf.utils.Security;

public class SecurityTest {
	public static void main(String[] args){
		DESCBC();
		DESEBC();
		

		
		
		//System.out.println(UUID.randomUUID().toString());
//		try {
//			String source = "13570475605";
//			String key = "des139wt";
//			String sign = "";
//			try {
//				sign = Security.encryptDES(source, key);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("进行DES加密后的密文: " + sign);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public static void DESCBC(){
		String en = "";
		try {
			en = Security.encryptDES("13760709457@139.com", "70df8a6ddefs");
			System.out.println(en);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println(Security.decryptDES(en, "70df8a6d123"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void DESEBC(){
		String en = "";
		try {
			en = Security.encryptDESECB("13760709457@139.com", "70df8a6dAAAAABBBB");
			System.out.println(en);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println(Security.decryptDESECB(en, "70df8a6dabcdefghjiaa"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
