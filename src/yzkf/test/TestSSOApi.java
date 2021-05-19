package yzkf.test;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;
import yzkf.api.SSO;
import yzkf.api.result.SSOResult;
import yzkf.app.Log;
import yzkf.config.ApiConfig;
import yzkf.config.ConfigFactory;
import yzkf.enums.SSOFlag;
import yzkf.exception.ApiException;
import yzkf.model.UserInfo;
import yzkf.utils.MD5;
import yzkf.utils.Security;
import yzkf.utils.TryParse;
import yzkf.utils.Utility;

public class TestSSOApi {
	public static void main(String[] args) throws Exception{
		//regSid();
		ssoLogin();
		//validateWap();
	}
	public static void regSid(){
		yzkf.api.SSO api = new yzkf.api.SSO();
		String mobile = "13900001234";
		SSOResult apiResult = SSOResult.OK;
		try {
			apiResult = api.registerSID(mobile);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(apiResult.isOK()){
			String sid = (String) apiResult.getValue();
			System.out.println(sid);
		}		
	}
	public static void ssoLogin(){
		yzkf.api.SSO api = new yzkf.api.SSO();
		String mobile = "13418161431";
		SSOResult apiResult = SSOResult.OK;
		try {
			apiResult = api.registerSID(mobile);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(apiResult.isOK()){
			String sid = (String) apiResult.getValue();;
			apiResult = api.getWebLoginUrl(mobile, sid, SSOFlag.Other.setValue("15"), "", "");
			if(apiResult.isOK())
				System.out.println(apiResult.getValue());
			else
				System.out.println(apiResult.getDescr());
		}else{
			System.out.println(apiResult.getDescr());
		}
	}
	public static void ssoLogin2(){
		yzkf.api.SSO api = new yzkf.api.SSO();
		String mobile = "13760709457";
		SSOResult apiResult = null;
		try {
			apiResult = api.getWebLoginUrl(mobile, SSOFlag.Inbox, "", "");
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(apiResult.isOK())
			System.out.println(apiResult.getValue());
	}
	public static void validate(HttpServletRequest request){
		yzkf.api.SSO api = new yzkf.api.SSO();
		SSOResult apiResult = api.validate(request);
		if(apiResult.isOK()){
			UserInfo userInfo = (UserInfo)apiResult.getValue();
		}
	}
	public static void validateWeb(HttpServletRequest request){
		String mobile = request.getParameter("Mobile");
		String sid = request.getParameter("Sid");
		long timestamp = TryParse.toLong(request.getParameter("Timestamp"));
		String from = request.getParameter("From");
		String to = request.getParameter("To");
		String skey = request.getParameter("Skey");
		String outflag = request.getParameter("Flag");

		yzkf.api.SSO api = new yzkf.api.SSO();
		SSOResult apiResult = api.validateWeb(mobile,sid,timestamp,from,to,skey,outflag);
		if(apiResult.isOK()){
			UserInfo ui = (UserInfo)apiResult.getValue();
		}
	}
	public static void validateWap(){
		
		String mo="13760709457";
		String ua="-1";
		String retcode="000";
		long timestamp=455393770;
		String skey="3dbd7d461469b233345989636c8becdb";
		String ct="1";
		String aid="ztk";
		yzkf.api.SSO api = new yzkf.api.SSO();
		SSOResult apiResult = api.validateWap(mo, ua, retcode, timestamp, skey);
		if(apiResult.isOK()){
			UserInfo ui = (UserInfo)apiResult.getValue();
		}
		System.out.println(apiResult.getDescr());
	}
	public static String createWapJumpUrl(String mo,String ua){
		long timestamp = Utility.getTimeSpan(6000);
		ConfigFactory factory = ConfigFactory.getInstance();
		ApiConfig config = factory.newApiConfig();
		String signed = MD5.encode(mo+ua+"000"+timestamp+config.Wap_Login_MD5Key);
		
		StringBuilder sb = new StringBuilder();
		sb.append("?mo="+mo);
		sb.append("&ua="+ua);
		sb.append("&retcode=000");
		sb.append("&timestamp="+timestamp);		
		sb.append("&skey="+signed);
		
		return sb.toString();
	}
}
