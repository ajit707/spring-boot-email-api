package com.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEmail(String to, String subject, String message) {

		boolean f = false;

		String from = "ajitk433Gmail.com";

		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system property
		Properties properties = System.getProperties();

		System.out.println(properties);

		// setting importantat information to properties object
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1 create session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("kajit0579@gmail.com", "gmail@1234");
			}
		});

		session.setDebug(true);

		// step 2 compose the message [text, multimedia]
		MimeMessage m = new MimeMessage(session);

		try {

			m.setFrom(from);

			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			m.setSubject(subject);
			m.setText(message);

			// step 3 send message using Transport class
			Transport.send(m);

			System.out.println("mail send successfully !!!");
			f = true;

		} catch (MessagingException me) {
			me.printStackTrace();
		}
		return f;

	}

}
