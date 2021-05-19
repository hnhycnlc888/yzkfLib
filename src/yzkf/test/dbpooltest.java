package yzkf.test;

import java.sql.SQLException;

import yzkf.db.DBAccess;

public class dbpooltest  {
	public static MyThread[] treads = new MyThread[15];
	public static void main(String[] args) {
		
		DBAccess dba = new DBAccess();
		Object ret;
		try {
			ret = dba.queryObject("select count(1) from v$session v where username = 'P_YZKF' and machine = 'qiuliwen'");
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		  for(int i=0;i<15;i++){
//			  treads[i] = new MyThread("线程"+i,0);
//			  treads[i].start();
			  new MyThread("线程"+i,0).start();
		  }
		  System.out.println("=========== 0 执行完毕，休眠1:30秒===================");
		  try {
			Thread.sleep(1000 * 70);
			for(int i=0;i<50;i++){
				new MyThread("线程"+i,1).start();
			}
			 System.out.println("=========== 0 执行完毕，休眠60秒===================");
			Thread.sleep(1000 * 60);
			for(int i=0;i<15;i++){
				new MyThread("线程"+i,2).start();
			}
//			System.out.println("=============== 1 执行完毕，休眠60 * 2秒=================");
//			Thread.sleep(1000 * 60 * 2);
//			for(int i=0;i<15;i++){
//				new MyThread("线程"+i,2).start();
//			}
//			System.out.println("====================== 2 执行完毕，休眠60 * 2秒===========");
//			Thread.sleep(1000 * 60 * 2);
//			for(int i=0;i<10;i++){
//				new MyThread("线程"+i,3).start();
//			 }
//			System.out.println("=========================== 3 执行完毕，休眠60秒========");
//			Thread.sleep(1000 * 60 * 1);
//			for(int i=0;i<10;i++){
//				new MyThread("线程"+i,4).start();
//			 }
//			System.out.println("================================ 4 执行完毕，休眠60 * 5秒======");
//			Thread.sleep(1000 * 60 * 5);
//			for(int i=0;i<10;i++){
//				new MyThread("线程"+i,5).start();
//			 }
//			System.out.println("====================================== 5 执行完毕，休眠60 * 5秒======");
//			Thread.sleep(1000 * 60 * 5);
//			for(int i=0;i<10;i++){
//				new MyThread("线程"+i,6).start();
//			 }
//			System.out.println("=========================================== 6 执行完毕，休眠60 * 5秒======");
//			Thread.sleep(1000 * 60 * 5);
//			for(int i=0;i<15;i++){
//				new MyThread("线程"+i,7).start();
//			 }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("所有线程执行完毕");
	}
}
