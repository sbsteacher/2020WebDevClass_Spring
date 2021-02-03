package com.koreait.sboard.common;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	private String userId;
	private String userPw;	
	private String host;
	private String port;
	private String fromEmail;
	
	public int sendMail(final String toEmail) {
		try {
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.port", port);
			prop.put("mail.smtp.host", host);
			prop.put("mail.smtp.ssl.trust", host);
			
			//Session 생성 
			Session session = Session.getDefaultInstance(prop, new Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() { 
					return new javax.mail.PasswordAuthentication(userId, userPw); 
				}			
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	
	
	
	
	
	
	
	
	
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	
}






