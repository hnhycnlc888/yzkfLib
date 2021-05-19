package yzkf.test;

import yzkf.api.Disk;
import yzkf.api.result.DiskResult;
import yzkf.exception.ApiException;
import yzkf.exception.ParserConfigException;
import yzkf.model.DiskInfo;
import yzkf.model.UserInfo;

public class DiskApiTest {
	public static void main(String[] args) throws ParserConfigException{
		DiskInit();
	}
	public static void login(){
		Disk api = new Disk();
		try {
			DiskResult res = api.validateLogin("lwqiu", "qiuliwen", "121.8.124.244");
			if(res.isOK())
				System.out.println(res.getValue(UserInfo.class).getSid());
			System.out.println(res.getDescr());
		} catch (ParserConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInfo info = api.getUserInfo();
		System.out.print( info.getRemkey() );
	}
	public static void DiskInit(){
		Disk api = new Disk();
		try{
			DiskResult result = api.init("13632599099");
			if(result.isOK()){
				System.out.println("接口执行成功");
				DiskInfo info = result.getValue(DiskInfo.class);
				System.out.println("rootid = "+info.getRootId());
			}else{
				
				System.out.println("接口执行失败："+result.getDescr());
			}
		}catch (ApiException e) {
			e.printStackTrace();
		}
		
	}
}
