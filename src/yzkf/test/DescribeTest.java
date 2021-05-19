package yzkf.test;

import yzkf.api.result.Result;
import yzkf.config.EnumConfig;

public class DescribeTest {
	public static void main(String[] args) {
		System.out.print(MyResult.OK.getDescr());
		System.out.print(MyResult.FailedLogin.getDescr());
	}
}
