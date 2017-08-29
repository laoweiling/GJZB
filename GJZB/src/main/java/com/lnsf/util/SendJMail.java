package com.lnsf.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendJMail {

	/**
	 * @Description: 发送验证码去邮箱
	 * @param args
	 * @throws MessagingException 
	 * 接口
	 * 
	 * SendJMail sendl=new SendJMail();
		sendl.chooseEMail("用户邮箱");
		匹配验证码：SendJMail.testCode直接获取
		
	 */
	public static String testCode;
	
	
	public static void chooseEMail(String eMail) {
		SendJMail sendl=new SendJMail();
		testCode=SendJMail.RandomUtils();
		System.out.println("testCode:"+testCode);
		System.out.println("eMail:"+eMail);
		try {
			sendl.sendEMail(eMail, testCode);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) throws MessagingException {
		/*SendJMail sendl=new SendJMail();
		 testCode=SendJMail.RandomUtils();
		System.out.println(testCode);
		try {
			sendl.sendEMail("820354102@qq.com",testCode);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		*/
		SendJMail sendl=new SendJMail();
		sendl.chooseEMail("1218623113@qq.com");
		System.out.println("main函数里面的数字："+SendJMail.testCode);
	}
	
	public static String RandomUtils(){
		StringBuilder code=new StringBuilder();
		Random random = new Random();
		for(int i=0;i<7;i++){
			int num=(int)((Math.random())*10);
			//System.out.println(num);
			code.append(num);
		}
		
		return code.toString();
	}
	
	

	public  void sendEMail(String eMail,String testCode) throws MessagingException, IOException {
		Properties prop =  new  Properties();    
		InputStream in=this.getClass().getClassLoader().getResourceAsStream("fileconfig.properties");
		prop.load(in);
		String  baseEMail = prop.getProperty("baseEMail").trim(); 	

	//	System.out.println("提取到的邮箱是："+baseEMail+" ,"+"存储路径："+imgRealPath);
		
		String  baseSMTFCode = prop.getProperty("baseSMTFCode").trim(); 	
		System.out.println("提取到的邮箱是："+baseSMTFCode);
		
		// 创建Properties 类用于记录邮箱的一些属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
        props.put("mail.smtp.port", "587");
        // 此处填写你的账号
      //  props.put("mail.user", "发送的邮箱@qq.com");
        props.put("mail.user", baseEMail);
        // 此处的密码就是前面说的16位STMP口令,
      //  props.put("mail.password", "jxprkpxrwzfubbgg");
        props.put("mail.password", baseSMTFCode);
        
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人的邮箱
     //   InternetAddress to = new InternetAddress("laoweiling@qq.com");
        InternetAddress to = new InternetAddress(eMail);//根据传参接受收件人
        message.setRecipient(RecipientType.TO, to);
        // 设置邮件标题
        message.setSubject("欢迎国际招标系统！");
        
        // 设置邮件的内容体
        message.setContent("亲爱的用户： " +
        		"您好！感谢您使用国际招标系统，您正在进行邮箱验证，本次请求的验证码为："+testCode+"(为了保障您帐号的安全性，请在1小时内完成验证。)", "text/html;charset=UTF-8");

        // 最后当然就是发送邮件啦
        Transport.send(message);
	}
}
