package Negocio;

import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Reminder extends Thread {
	Timer timer;
	String login;
	String senha;
	String to;
	String subject;
	String content;
	Date date;

	@Override
	public void run() {
		timer = new Timer();
		timer.schedule(new RemindTask(login, senha, to, subject, content), date);
	}

	public Reminder(String login, String senha, String to, String subject,
			String content, Date date) {
		this.login = login;
		this.senha = senha;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.date = date;		
	}

	class RemindTask extends TimerTask {
		String login;
		String senha;
		String to;
		String subject;
		String content;

		public RemindTask(String login, String senha, String to, String subject,
				String content) {
			this.login = login;
			this.senha = senha;
			this.to = to;
			this.subject = subject;
			this.content = content;
		}

		public void run() {

			try {
				/**
				 * Carregando propriedades
				 */
				Properties props = new Properties();
				props.setProperty("mail.host", "smtp.gmail.com");
				props.setProperty("mail.transport.protocol", "smtp");
				// Comente essas 4 linhas abaixo se deseja usar o SSL
				props.setProperty("mail.smtp.auth", "true");
				props.setProperty("mail.smtp.port", "587");
				props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
				props.setProperty("mail.smtp.starttls.enable", "true");
				//
				//
				Authenticator auth = new SMTPAuthenticator(login, senha);
				Session session = Session.getInstance(props, auth);

				MimeMessage msg = new MimeMessage(session);
				msg.setText(content);
				msg.setSubject(subject);
				msg.setFrom(new InternetAddress(to));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						to));
				msg.setContent(content, "text/html");
				msg.setSentDate(new Date());

				Transport.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (timer != null)
				timer.cancel();
		}
	}

	}