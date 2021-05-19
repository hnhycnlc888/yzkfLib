package yzkf.test;

import yzkf.app.Memcached;
import yzkf.com.Login;

public class LoginTest {
public static void main(String[] args){
	Memcached.getInstance().delete("login_failed_times_lwqiu");
}
public static void login(){
	//Login.getInstance().Web(request);
	//com.Web(request, "", password, verifyCode, agentId)
}
}
