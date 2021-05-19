package yzkf.test;

import org.json.JSONObject;

import yzkf.api.Subscribe;
import yzkf.api.result.SubscribeResult;
import yzkf.exception.ApiException;

public class SubscribeTest {
	public static void main(String[] args){
		jsonTest();
//		sub("13730124054", "19497");
//		unsub("13730124054", "19497");
//		feeSub("13730124054", "38596");
//		feeUnsub("13730124054", "38596");
	}
	public static void jsonTest(){
		String out = "{\"Message\":{\"body\":{\"columnId\":0,\"returnCode\":10,\"wabpInfo\":null},\"header\":{\"description\":\"操作成功\",\"resultCode\":\"0\",\"verify\":\"\"}}}";
		JSONObject outJson = new JSONObject(out);
		
		JSONObject message = outJson.getJSONObject("Message");
		JSONObject header = message.getJSONObject("header");
		String outRetCode = header.getString("resultCode");
		String outRetMsg = header.getString("description");
//		String outVerify = header.getString("verify");
//		if(!outVerify.equalsIgnoreCase(verifyID)){			
//			return SubscribeResult.ApiBusy;
//		}

		if(outRetCode.equalsIgnoreCase("0")){
			JSONObject body = message.getJSONObject("body");
			int outSubResult = body.getInt("returnCode");
			if(outSubResult == 10 || outSubResult == 11)
				System.out.print("ok");
			else
				System.out.print("failed");
		}else{
			//返回未知结果
			System.out.print(outRetMsg);
		}
	}
	private static void sub(String mobile,String columnId){
		Subscribe api = new Subscribe();
		SubscribeResult result = null;
		try {
			result = api.subscribe(mobile,columnId);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			System.out.println("订阅成功");
		}else if(result==SubscribeResult.Failed){
			System.out.println("订阅失败");
		}else{
			System.out.println("接口执行失败："+result.getDescr());
		}
	}
	private static void unsub(String mobile,String columnId){
		Subscribe api = new Subscribe();
		SubscribeResult result = null;
		try {
			result = api.unsubscribe(mobile,columnId);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			System.out.println("退订成功");
		}else if(result==SubscribeResult.Failed){
			System.out.println("退订失败");
		}else{
			System.out.println("接口执行失败："+result.getDescr());
		}
	}
	private static void feeSub(String mobile,String columnId){
		Subscribe api = new Subscribe();
		SubscribeResult result = null;
		try {
			result = api.feeSubscribe(mobile,columnId);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			System.out.println("订阅成功");
		}else if(result==SubscribeResult.Failed){
			System.out.println("订阅失败");
		}else{
			System.out.println("接口执行失败："+result.getDescr());
		}
	}
	private static void feeUnsub(String mobile,String columnId){
		Subscribe api = new Subscribe();
		SubscribeResult result = null;
		try {
			result = api.feeUnsubscribe(mobile,columnId);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if(result.isOK()){
			System.out.println("退订成功");
		}else if(result==SubscribeResult.Failed){
			System.out.println("退订失败");
		}else{
			System.out.println("接口执行失败："+result.getDescr());
		}
	}
}
