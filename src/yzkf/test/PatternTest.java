package yzkf.test;

import java.io.IOException;


import yzkf.app.Pattern;
import yzkf.exception.ParserConfigException;
import yzkf.utils.HttpClient;
import yzkf.utils.LocalCache;
import yzkf.utils.Utility;

public class PatternTest {
	public static void main(String[] args) throws ParserConfigException, IOException{
//		String substr = Pattern.getFirstMatch("[a-z]+", "1234a2b3d4e3456hijklms134234");
//		System.out.println(substr);
//		
//		String html = HttpClient.get("http://fun.mail.10086.cn/qg/1205/0881/index.html","gb2312");
//		String text = Pattern.getFirstMatch("<!--([^<]+?)-->$", html);
//		//String text = Pattern.getFirstMatch("<.+>", html);
//		if(!Utility.isEmptyOrNull(text)){
//			//text = text.substring(4, text.length()-3);
//			System.out.println(text);
//		}
//		text = Pattern.getMatchString("<!--.+-->", html,-1);
//		if(!Utility.isEmptyOrNull(text)){
//			text = text.substring(4, text.length()-3);
//			System.out.println(text);
//		}
		
//		String html = "尊敬的%{RECEIVER_ADDRESS}客户：    恭喜您参加中国移动“绿色手机，魅力生活”活动获赠";//HttpClient.get("http://res.zone139.com/cn/120201/mail/prize_virtual.html");
//		html = html.replaceAll("%\\{RECEIVER_ADDRESS\\}", "13900001111");
//		System.out.println(html);
		
		//System.out.println(isConcatContains("0, 19, 17, 18, 15, 16, 13, 14, 11, 12, 21, 20, 22, 23, 24, 25, 26, 27, 28, 29, 3, 2, 10, 1, 7, 30, 6, 5, 4, 31, 9, 8","19"));
		//System.out.println(isContains("0, 19, 17, 18, 15, 16, 13, 14, 11, 12, 21, 20, 22, 23, 24, 25, 26, 27, 28, 29, 3, 2, 10, 1, 7, 30, 6, 5, 4, 31, 9, 8","10"));
		
		testP();
	}
	public static boolean isConcatContains(String concat,String child){
		//System.out.println(concat+"\r\n^^^^\r\n"+child);
		String reg = "0,.*";//child;
		System.out.println(reg);
		return concat.matches(reg);
		//return concat.matches("((^"+child+"[,\\s]+)|([,\\s]+"+child+"[,\\s]+)|([,\\s]+"+child+"$)|(^"+child+"$))");
	}
	public static boolean isContains(String concat,String child){
		return (","+concat.replace(" ", "")+",").contains(child);
	}
	public static void testP(){
		String mailhtml = getMailTemp("http://dev.zone139.com:8001/jsp/third/bo/index.htm");
		System.out.println(mailhtml);
		String sender = Pattern.getFirstMatch("<!--<from>.+?<(.+?)></from>-->", mailhtml);//发件人地址
		String senderName = Pattern.getFirstMatch("<!--<from>(.+?)<.+?></from>-->", mailhtml);//发件人名称
		System.out.println(sender);
		System.out.println(senderName);
	}
	
	public static String getMailTemp(String url){
		if(Utility.isEmptyOrNull(url))
			return null;
		String html = (String) LocalCache.getCache(url);
		if(Utility.isEmptyOrNull(html)){
			try {
				html = HttpClient.get(url,"gb2312");
			} catch (IOException e) {
				//Utils.logger.error("获取邮件模板异常模板地址:"+url, e);
			}
			if(Utility.isEmptyOrNull(html)){
				return null;
			}
			LocalCache.setCache(url, html, 10 * 60);
		}
		return html;		
	}
}
