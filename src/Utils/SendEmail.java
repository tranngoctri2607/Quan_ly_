package Utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	private static int rand = -1;

	public static int send(String to) {
		try {
			Properties p = new Properties();
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.put("mail.smtp.port", 587);
			String user = "lulovekhe@gmail.com";
			String pass = "iyvvheohwhghpcot";
			Session s = Session.getInstance(p, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, pass);
				}
			});
			rand = (int) (Math.random() * 10000);
			String subject = "Send OTP code!";
			String body = String.valueOf("OTP code: " + rand);
			Message msg = new MimeMessage(s);
			msg.setFrom(new InternetAddress(user));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject(subject);
			msg.setText(body);
			Transport.send(msg);
		} catch (MessagingException ex) {

		}
		return rand;
	}
}
