package yzkf.test;

import java.io.IOException;

import yzkf.app.ActiveLog;
import yzkf.enums.ActiveFunction;
import yzkf.enums.ActiveOperation;
import yzkf.exception.ParserConfigException;
import yzkf.utils.HttpClient;

public class ActiveLogTest {
	public static void main(String[] args) throws  IOException{
		HttpClient.get("http://test.zone139.com:8001/2011/moclicklog/molog.aspx?mobile=13760709457&seid=5679193942A205AD2AB8B978D1157D52&prj=cn201301b1&vt=12&purl=http%3A%2F%2Flocal.zone139.com%3A8080%2Fxunlei%2Fshare.do&ua=Mozilla/5.0&cip=127.0.0.1&sip=192.168.14.104&pa=");
		//ActiveLog.getInstance().WapClickTwice(mobile, request)
		//ActiveLog.getInstance().WriteBehaviorLog("13760709457", "1", "22", "127.0.0.1", ActiveFunction.SendMMS, ActiveOperation.GetLottery, "", "");
	}
}
