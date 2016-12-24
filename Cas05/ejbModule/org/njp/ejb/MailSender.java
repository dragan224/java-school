package org.njp.ejb;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailSender {
	public MailSender(String to, String subject, String body) throws MessagingException {
		
	    final String from ="dragan2241@gmail.com";
	    final  String password ="dmarkovic13";


	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(from, password);
	                }
	            });
	    session.setDebug(true);
	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(to));
	        message.setSubject(subject);
	        message.setText(body);

	        Transport.send(message);

	        System.out.println("Done");
	} catch (MessagingException e) {
	    System.out.println(e.getMessage());
	    System.out.println("Failed to send email");
	}
	}
}
