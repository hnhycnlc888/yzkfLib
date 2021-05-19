package yzkf.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import yzkf.api.Mail;
import yzkf.api.result.MailResult;
import yzkf.enums.SSOType;
import yzkf.exception.ApiException;
import yzkf.exception.ParserConfigException;
import yzkf.model.UserInfo;
import yzkf.utils.SocketClient;
import yzkf.utils.Utility;

public class MailApi {
	public static void main(String[] args) throws ParserConfigException{
//		IntegralAddTest();
//		IntegralSubTest();
//		
//		integralAddDetail();
//		integralSubDetail();
//		integralDaySum();
		//integralCount();
		//queryMailNum();
		//jfTest();
		//getAttr();
		//getCarType();
		//getAttr();
		//getMobileBySid(args[0]);
		//getMobileBySid("MTM5OTE4NDg2MDAwMDA5Njc5MDgwNAAA000005");
		getAttr();
		//getMobileBySid("MTQwMDc0MzI3NTAwMTYxNjY3MTgwOAAA000002");
		//MTQwMDIxMDM2ODAwMDk1NDAyNzg4NQAA000007
		//MTQwMDIxMDM2ODAwMDk1NDAyNzg4NQAA000007
		//MTQwMDIxMDM2ODAwMDk1NDAyNzg4NQAA000007
		//getMobileAndNewSidBySid("MTM5OTU1NzMyNTAwMDEwNzc1NjA1MAAA000005");
//		MTM5OTU1NzQwNzAwMDI5NTg4OTQ0NQAA000005
//		MTM5OTU1NzMyNTAwMDEwNzc1NjA1MAAA000005
//		MTM5OTU1NzMyNTAwMDEwNzc1NjA1MAAA000005
	}
	public static void getMobileBySid(String sid){
		Mail api = new Mail();
		MailResult result;
		try {
			result = api.getMobileBySid(sid);
			if(result.isOK()){
				System.out.println("用户在线，号码是："+result.getValue(String.class));
			}else if(result == MailResult.OffLine){
				System.out.println("用户不在线");
			}else{
				System.out.println("接口返回异常："+result.getDescr());				
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void getMobileAndMailSidBySid(String sid){
		Mail api = new Mail();
		MailResult result;
		try {
			result = api.getMobileAndMailSidBySid(sid);
			if(result.isOK()){
				String[] mobileAndSid = result.getValue(String[].class);
				System.out.println(sid+"用户在线，号码是："+mobileAndSid[0]+"，邮箱sid是："+mobileAndSid[1]);
			}else if(result == MailResult.OffLine){
				System.out.println("用户不在线");
			}else{
				System.out.println("接口返回异常："+result.getDescr());				
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void test(){
//		Mail api = new Mail();
//		try {
//			MailResult rs = api.validateLogin("wu-bingying", "mail139");
//			DateFormat df = new SimpleDateFormat("M/d/yyyy hh:mm:ss a",Locale.US);
//			Date d = df.parse("8/1/2009 1:23:05 PM");
//			System.out.println(d);
			//MailEnum rs = api.getAttribut("13760709457");
			//MailEnum rs = api.getCardTypeInfo("13760709457");
			//MailEnum rs = api.register("13760709457");//("13760709457");
			//MailResult rs = api.getMobileBySid("MTMyODAwNTQ0NzAwMTA0NDQ2NDY3MQAA000005");
//			System.out.println(rs.getDescr());
//			if(rs.isOK()){
//				String ssourl = api.getSSOUrl("wu-bingying", api.getUserInfo().getSid(), SSOType.Mail, null);
//				System.out.println(ssourl);
//			}else{
//				rs.getDescr();
//			}
			
//			String data = "<?xml version=\"1.0\" encoding=\"GB2312\"?>"
//				+"<RequestData>"
//				+"<CityID>xxxx</CityID>"
//				+"<Mobile>xxxx</Mobile>"
//				+"<ServiceCode>xxxx</ServiceCode>"
//				+"<BindTypeId>xxxx</BindTypeId>"
//				+"<ComeFrom>xxxx</ComeFrom>"
//				+"<TimeStamp>xxxx</TimeStamp>"
//				+"<Skey>xxxx</Skey>"
//				+"</RequestData>";
//			//data = "command=vote&photoid=1";
//			String outget =  HttpClient.get("http://localhost/photo/Handler/Post.ashx");
//			System.out.println(outget);
//			String out = HttpClient.post("http://localhost/photo/Handler/Post.ashx", data);			
//			System.out.println(out);
//
//			rs = api.getAttribut("13760709457");
//			if(rs.isOK()){				
//				System.out.println(api.getUserInfo());
//			}else{
//				rs.getDescr();
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public static void queryMailNum(){
		yzkf.api.Mail api = new yzkf.api.Mail();
		MailResult result = null;
		UserInfo userInfo = new UserInfo();
		userInfo.setAlias("lwqiu");
		//userInfo.setMobile("13760709457");
		try {
			result = api.getMailNum(userInfo);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			//UserInfo userInfo = result.getValue(UserInfo.class);
			System.out.println("账号："+userInfo.getAccount());
			System.out.println("未读邮件数："+userInfo.getUnreadMailNum());
			System.out.println("邮件总数："+userInfo.getTotalMailNum());
		}else{
			System.out.println("接口执行不成功，返回码："+result.getValue());
		}
	}
	public static void integralAddDetail(){
		yzkf.api.Mail api = new yzkf.api.Mail();
		MailResult result = null;
		try {
			result = api.getIntegralAddDetail("13760709457","20140301", "20140320");
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			ArrayList<String[]> list = (ArrayList<String[]>) result.getValue();
			Iterator<String[]> itr = list.iterator();
			while(itr.hasNext()){
				String[] array = itr.next();
				System.out.println("日期(格式：yyyyMMdd)："+array[0]+"，规则编号："+array[1]+"，规则名称："+array[2]+"，增加积分数："+array[3]);
			}
		}else{
			System.out.println("接口执行不成功，返回码："+result.getValue());
		}
	}
	public static void integralSubDetail(){
		yzkf.api.Mail api = new yzkf.api.Mail();
		MailResult result = null;
		try {
			result = api.getIntegralSubDetail("13760709457","20140301", "20140320");
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			ArrayList<String[]> list = (ArrayList<String[]>) result.getValue();
			Iterator<String[]> itr = list.iterator();
			while(itr.hasNext()){
				String[] array = itr.next();
				System.out.println("日期(格式：yyyyMMdd)："+array[0]+"，规则编号："+array[1]+"，规则名称："+array[2]+"，消耗积分数："+array[3]);
			}
		}else{
			System.out.println("接口执行不成功，返回码："+result.getValue());
		}
	}
	public static void integralDaySum(){
		yzkf.api.Mail api = new yzkf.api.Mail();
		MailResult result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date begin = null;
			Date end = null;
			try {
				begin = sdf.parse("20140301");
				end = sdf.parse("20140320");	
			} catch (ParseException e) {
				e.printStackTrace();
			}
			result = api.getIntegralDaySum("13760709457",begin, end);
			//result = api.getIntegralDaySum("13760709457","20140301", "20140320");
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			ArrayList<String[]> list = (ArrayList<String[]>) result.getValue();
			Iterator<String[]> itr = list.iterator();
			while(itr.hasNext()){
				String[] array = itr.next();
				System.out.println("日期(格式：yyyyMMdd)："+array[0]+"，增加积分："+array[1]+"，消耗："+array[2]);
			}
		}else{
			System.out.println("接口执行不成功，返回码："+result.getValue());
		}
	}
	public static void integralCount(){
		yzkf.api.Mail api = new yzkf.api.Mail();
		MailResult result = null;
		try {
			result = api.getIntegralTotal("15014172051");
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			//也可以写成：UserInfo userInfo = api.userInfo;
			UserInfo userInfo = result.getValue(UserInfo.class);
			
			System.out.println("用户当前可以使用的活动积分(产品为特定活动定义)："+userInfo.getIntegralActive());			
			System.out.println("用户已使用积分："+userInfo.getIntegralUse());
			System.out.println("用户可用积分（运营活动中使用该字段）："+userInfo.getIntegralEffect());
			System.out.println("用户等级："+userInfo.getUserLevel());
		}else{
			System.out.println("接口执行不成功，返回码："+result.getValue());
		}
	}
	
	public static void IntegralAddTest(){
		yzkf.api.Mail api = new yzkf.api.Mail();
		MailResult result = null;
		try {
			result = api.integralAdd("13760709457","21");
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			System.out.println("增加积分成功");
		}else{
			System.out.println("接口执行不成功，返回码："+result.getValue());
		}
	}
	public static void IntegralSubTest(){
		yzkf.api.Mail api = new yzkf.api.Mail();
		MailResult result = null;
		try {
			result = api.integralSub("13760709457","1");
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			System.out.println("消费积分成功");
		}else{
			System.out.println("接口执行不成功，返回码："+result.getValue());
		}
	}
	
	public static void jfTest(){
		
		//192.168.9.197
		String out = null;
		String data = "<root><check>qwer1234</check><method>GetIntegralRev</method><param><usernumber>13760709457</usernumber></param></root>";
		try {
			out = SocketClient.SendString("192.168.9.197", 9099, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(out);	
		

//		String charsetName = "utf-8";
//		String defaultChareset = Charset.defaultCharset().displayName();
//		System.out.println(defaultChareset);
//		String str1 = new String(data.getBytes(charsetName),charsetName);
//		System.out.println(str1);
//		String str2 = new String(str1.getBytes(charsetName),"GBK");
//		System.out.println(str2);
	}
	public static void getAttr(){
		Mail api = new Mail();
		MailResult res = null;
		try {
			res = api.getAttribute("13903078035");
			//res = api.getAttribute("lwqiu");
			System.out.println(res.getDescr());
		} catch (ParserConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res.isOK()){
			
			UserInfo info = api.getUserInfo();
			System.out.print( info.getMobile());
			System.out.print( info.getAlias());
			//UserInfo info = res.getValue(UserInfo.class);
			System.out.print( info.getProvCode() );
		}
		
	}
	public static void getCarType(){
		Mail api = new Mail();
		try {
			MailResult result = api.getCardTypeInfo("15978053984");
			if(result.isOK()){
				UserInfo info = result.getValue(UserInfo.class);
				System.out.print("用户省份编号："+ info.getProvCode() );
			}else{
				System.out.println("查询用户号码属性失败："+result.getDescr());
			}
		} catch (ApiException e) {
			System.out.println("接口返回异常："+e.getMessage());
		}

	}
}
