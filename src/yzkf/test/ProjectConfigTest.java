package yzkf.test;

public class ProjectConfigTest {
	public static void main(String[] args){
		
		String appsetting = yzkf.config.ProjectConfig.getInstance().getAppSetting("test");
		System.out.println(appsetting);
	}
}
