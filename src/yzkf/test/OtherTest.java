package yzkf.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.helpers.LogLog;
import org.json.JSONObject;

import yzkf.app.Pattern;
import yzkf.config.ConfigFactory;
import yzkf.db.DBAccess;
import yzkf.exception.ParserConfigException;
import yzkf.utils.HttpClient;
import yzkf.utils.MD5;
import yzkf.utils.TryParse;
import yzkf.utils.Utility;

public class OtherTest {
	private static String version="1.0";
	private static String app_id="mail139";	
	private static String prikey="9D68814673F1BEC93CC547FAF73CC5B9";
	private static String baseUrl = "http://210.21.96.189:18080/cmcc-jifen-webgate";
	private static String encoding = "utf-8";
	//固定值
		private static int point_type=2;
		//规则ID，一期暂时为0
		private static int rule_id = 0;
	public static void query(String user_name){
		String sendtime = "20140521214303";//Utility.formatDate(new Date(), "yyyyMMddHHmmss");
		String sign = MD5.encode((prikey+ user_name + sendtime));
		JSONObject json = new JSONObject();
		json.put("version",version);
		json.put("sendtime",sendtime);//当前时间
		json.put("user_name",user_name);
		json.put("app_id",app_id);
		json.put("sign",sign);
		System.out.println(json.toString());
	}
	public static void add(String user_name){
		ActiveID actid = ActiveID.sendmail;
		String seq = null;
		String opt_time = Utility.formatDate(new Date(), "yyyyMMddHHmmss");
		String sign = MD5.encode(prikey+ user_name + point_type+actid.getValue()+ rule_id+opt_time);
		JSONObject json = new JSONObject();
		json.put("version",version);
		json.put("sendtime",opt_time);//当前时间
		json.put("sendSeq",actid.name()+(Utility.isEmptyOrNull(seq)?UUID.randomUUID():seq));
		json.put("user_name",user_name);
		json.put("app_id",app_id);
		json.put("rule_id",rule_id);
		json.put("active_id",actid.name());
		json.put("point_type",point_type);
		json.put("point_add",actid.getValue());
		json.put("opt_time",opt_time);//获取积分时间
		json.put("sign",sign);
		System.out.println(json.toString());
	}
	public static void main(String[] args){
		PostData();
		//boolean m = java.util.regex.Pattern.matches("(.|\\r|\\n)*", "<html>\r\n");
		//System.out.println(m);
		//System.out.println(UUID.randomUUID());
		//query("498344019");
		//query("498344019");
		//System.out.println((",11,").contains(",1,"));
		//System.out.println((",11,").contains(",81,"));
			
		//System.out.print(getServerIP());
		//xunleiTest();
		//pathTest();
		//tempTest();
		//month();
//		String sql = "select count(*) c from T_Y_CN20120207_RESOURCE where RESNO = ? and rownum <= 1";
//		Object[] ret = null;
//		try {
//			ret = new DBAccess().queryArray(sql,"cs2342");
//		} catch (ParserConfigException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		if(ret != null && ((BigDecimal) ret[0]).intValue() == 0){
//			System.out.println("---"+ret[0]);
//		}
//		System.out.println(ret[0]);
		
		//formatter.parse(source)
//		long time = new Date().getTime();
//		System.out.print("time:");
//		System.out.println(time);
//		long hour = time/1000/60/60;
//		System.out.print("hour:");
//		System.out.println(hour);
//		System.out.println(new Date(hour*60*60*1000));
//		long day = hour/23;
//		System.out.print("day:");
//		System.out.println(day);
//		System.out.println(new Date(day*60*60*23*1000));
		//System.out.println(formatter.format(getYesterday()));
	}
	public static void PostData(){
		try {
			String responseText = HttpClient.post("http://localhost:8080/iptv/upload.do", "[{\"UserId\":\"076900467827\",\"ProductId\":\"10002\",\"ProductName\":\"产品名称\",\"ProductType\":\"3\",\"OrderValid\":\"1\",\"PayType\":\"1\",\"Fee\":\"2000\",\"PurchaseTime\":\"20140911215438\"}]");
			System.out.print(responseText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getServerIP() {
		String serverIP = "";
			try{
		    	serverIP = InetAddress.getLocalHost().getHostAddress();
		     }catch(UnknownHostException t){
		         LogLog.debug("获取服务器IP地址异常", t);
		     }
		return serverIP;
	}
	public static void xunleiTest(){
		String mobile = "13760709457";	
		String partner = "xunlei";
		String uid = "32817";
		int orderid = 10102;
		int score = 3;//TryParse.toInt(request.getParameter("score"));
		Long timestamp = Utility.getTimeSpan(0) ;//TryParse.toLong(request.getParameter("timestamp"));
		//String sign = "";//request.getParameter("sign");		
		String MD5_KEY = "f286Zb93s33c0fAe";
		
		String sign = MD5.encode(mobile+orderid+partner+score+timestamp+uid+MD5_KEY);
		
		String params = "mobile="+mobile+"&partner="+partner+"&uid="+uid+"&orderid="+orderid+"&score="+score+"&timestamp="+timestamp+"&sign="+sign;
		System.out.println(params);
		String out = "";
		try {
			out = HttpClient.get("http://local.zone139.com:8080/xunlei/exchange.do?"+params);
			//out = HttpClient.post("http://local.zone139.com:8080/xunlei/exchange.do", params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("out="+out);
	}
	public static void month(){
		int startMonth = 201206;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		Calendar cal  = Calendar.getInstance(); 
		cal.set(startMonth/100, startMonth%100-1, 1,0,0,0);
		System.out.println(formatter.format(cal.getTime()));
	}
	private static Date getYesterday(){
		Calendar cal  = Calendar.getInstance(); 
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		return cal.getTime();
		//return new Date((new Date().getTime()/1000/60/60/23-3)*60*60*23*1000);
	}
	private static void tempTest(){
		
		Long ts = Utility.getTimeSpan(200);
		String ky = MD5.encode(ts+"fb924247466baf7e");
		System.out.println("?ts="+ts+"&ky="+ky);
		ky = MD5.encode("test"+ts+"fb924247466baf7e");
		System.out.println("?sno=test&ts="+ts+"&ky="+ky);
	}
	
	private static void pathTest(){
		String ReusltPath = "/project.xml";
		ClassLoader standardClassloader = Thread.currentThread().getContextClassLoader();
		URL url = standardClassloader.getResource(ReusltPath);
		
		if (url == null) {
            url = OtherTest.class.getResource(ReusltPath);
            System.out.println("2:"+url.getFile());
        }else{
        	System.out.println("1:"+url.getFile());
        }
	}
	
}
