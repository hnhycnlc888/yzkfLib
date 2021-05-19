package yzkf.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import yzkf.exception.ParserConfigException;
import yzkf.utils.LocalCache;

public class cache {
	public static void main(String[] args) throws ParserConfigException{
//		yzkf.utils.LocalCache.setCache("abc", "test abc");
//		System.out.println(yzkf.utils.LocalCache.getCache("abc"));
//		System.out.println(yzkf.utils.LocalCache.getCache("123"));
		testRemove();
	}
	public static void testRemove(){
		try {
			String host = InetAddress.getLocalHost().getHostAddress();
			System.out.println(host);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalCache.setCache("a", "abcdefg");
		LocalCache.setCache("b", "1234567");
		LocalCache.setCache("c", "1234abcd");
		System.out.println("a="+LocalCache.getCache("a"));
		System.out.println("b="+LocalCache.getCache("b"));
		System.out.println("c="+LocalCache.getCache("c"));
		LocalCache.removeCache("b");
		System.out.println("a="+LocalCache.getCache("a"));
		System.out.println("b="+LocalCache.getCache("b"));
		System.out.println("c="+LocalCache.getCache("c"));
		LocalCache.removeAllCache();
		System.out.println("a="+LocalCache.getCache("a"));
		System.out.println("b="+LocalCache.getCache("b"));
		System.out.println("c="+LocalCache.getCache("c"));
	}
}
