package yzkf.test;

import yzkf.config.ProjectConfig;


public class ProjectTest {
	public static void main(String[] args){
		ProjectConfig config = ProjectConfig.getInstance();
		System.out.println("项目编号："+config.getCode());
		System.out.println("项目名称："+config.getName());
		System.out.println("开始时间："+config.getStart());
		System.out.println("结束时间："+config.getEnd());
		System.out.println("是否开始："+config.isStart());
		System.out.println("是否结束："+config.isEnd());
		System.out.println("是否在线："+config.isOnline());
		
		System.out.println("广东："+config.checkProv(1));
		System.out.println("广州："+config.checkArea(22));
		System.out.println("广东广州："+config.checkProvArea(1, 22));
		
		System.out.println("省份10："+config.checkProv(10));
		System.out.println("地区18："+config.checkArea(18));
		System.out.println("省份10地区18："+config.checkProvArea(10, 18));
	}
}
