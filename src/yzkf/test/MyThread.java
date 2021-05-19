package yzkf.test;

import java.sql.SQLException;

import yzkf.db.DBAccess;

public class MyThread extends Thread {
	private String name;
	private int runtimes = 0;
	public MyThread(String name,int runtimes){
		this.name = name;
		this.runtimes = runtimes;
	}
	public void run(){
		
//		try {
//			this.sleep(1000);
//		} catch (InterruptedException e1) {
//			System.out.println(this.name + "第"+runtimes+ "次："+e1.getMessage());
//		}
		DBAccess dba = new DBAccess();
		Object ret;
		try {
			ret = dba.queryObject("select count(1) from v$session v where username = 'P_YZKF' and machine = 'qiuliwen'");
			dba.queryObject("select sum(scid) from t_y_cn20110107_analytics");
			System.out.println(this.name +"第"+runtimes+ "次："+ret);
		} catch (SQLException e) {	
			System.out.println(this.name + "第"+runtimes+ "次："+e.getMessage());
			//e.printStackTrace();
		}
		runtimes ++;
	}
}
