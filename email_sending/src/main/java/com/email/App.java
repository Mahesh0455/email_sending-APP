package com.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App {
	public static void main(String[] args) throws Exception {

		System.out.println("Message prepartion...");
		String message = "Hi Mahesh, This is Java Email Test Message";
		String subject = "Java Email Testing";
		String to = "ma6282885@gmail.com";
		String from = "techiemahesh045@gmail.com";

		sendEmail(message, subject, to, from);

	}

	private static void sendEmail(String message, String subject, String to, String from) throws Exception {

		String host = "smtp.gmail.com";//smtp.gmail.com

		// get the system properties

		Properties props = System.getProperties();
		System.out.println("Data in Props: " + props);

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		// Get the session Object

		// Authenticator auth =new Authenticator();
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("techiemahesh045@gmail.com", "gwhdafxyrqipuibu");
			}

		});

		session.setDebug(true);
		// Step 2. Compose the message

		MimeMessage m = new MimeMessage(session);

		try {
			Transport transport = session.getTransport();
			InternetAddress addressFrom = new InternetAddress(from);
			m.addRecipient(Message.RecipientType.TO, addressFrom);
			m.setText(message);
			m.setSubject(subject);

			

			transport.connect();
			Transport.send(m);
			transport.close();
			System.out.println("Sent Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
