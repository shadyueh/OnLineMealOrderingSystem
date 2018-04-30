package com.wangyanci.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {
	// 发送邮件
	public static void sendMail(String email, String emailMsg) throws AddressException, MessagingException {

		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true
		props.setProperty("mail.debug", "true");

		// Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		// props.setProperty("mail.smtp.socketFactory.port", "465");

		// props.setProperty("password", "uyqacnsubzexbifd");
		MailSSLSocketFactory sFactory;
		try {
			sFactory = new MailSSLSocketFactory();
			sFactory.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sFactory);

			// 创建验证器
			Authenticator auth = new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("35167348@qq.com", "ujxtrdfyvlflbggh");
				}
			};

			Session session = Session.getInstance(props);

			// 2.创建一个Message，它相当于是邮件内容
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("35167348@qq.com")); // 设置发送者

			message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者

			message.setSubject("用户激活");
			// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

			message.setContent(emailMsg, "text/html;charset=utf-8");

			// 3.创建 Transport用于将邮件发送
			Transport transport = session.getTransport();
			transport.connect("smtp.qq.com", "35167348@qq.com", "uyqacnsubzexbifd");

			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {

			e.printStackTrace();
		} //

	}
}
