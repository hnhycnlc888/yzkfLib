package yzkf.test;

import java.io.File;
import java.io.IOException;

import sun.security.krb5.internal.HostAddress;

import yzkf.app.Mail;
import yzkf.config.ConfigFactory;
import yzkf.config.MailConfig;
import yzkf.exception.MailException;
import yzkf.exception.ParserConfigException;
import yzkf.utils.FileRW;

public class MailTest {
	public static void main(String[] args) throws ParserConfigException, IOException{
		
		
		try {
			ConfigFactory factory = ConfigFactory.getInstance();
			MailConfig config = factory.newMailConfig();
			//使用config配置对象 创建 计算活跃 的邮件对象
			Mail mail = new Mail(true,config);
			//设置发件人
			//mail.setFrom("13760709457@139.com");
			mail.setFrom("13418161431@139.com");
			//设置发件人名称
			mail.setFromname("Leo");
			//设置收件人，多个收件人逗号隔开
			//mail.setTo("13760709457@139.com");
			mail.setTo("13418161431@139.com");	
			//设置抄送
			//mail.setCc("13987654321@139.com,alias2@139.com");
			//设置密送
			//mail.setBcc("13912341234@139.com,alias3@139.com");
			mail.setSubject("测试邮件适配");//设置邮件标题
			//设置html正文
			
//			String ct = FileRW.readString("F:\\testmail.html","gb2312");
//			mail.setHtml(ct);
			mail.setHtml("<html><head><title>Hello 139 Mail!</title></head><body><a href=\" http://mail.10086.cn?id=calendar\" id=\"139Command_LinksShow\" rel=\"calendar\" params=\"addnew=6\" target=\"_blank\">生日提醒</a></body></html>");
			//设置wap简版正文，注意：仅限139邮箱，如果发送外遇，请不要同时设置 setHtml 和 setText，外遇邮箱将同时显示2种内容
			//mail.setText("Hello,I'm content of wap!我是中文，我乱码");
			//增加附件
			//mail.addAttachments("E:\\attachment1.txt");
			//增加多个附件
			//mail.addAttachments("E:\\attachment2.txt");
			//发送邮件
			mail.Send();			
			System.out.println("发送成功");
		} catch (MailException e) {
			// 邮件发送异常
			e.printStackTrace();
		} 
	}
}
