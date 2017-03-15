package Negocio;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarMail extends Thread{

	String login;
	String senha;
	String to;
	String subject;
	String content;
		
	public EnviarMail(String login, String senha, String to, String subject,
			String content) {
		super();
		this.login = login;
		this.senha = senha;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	public void run() {
		/**
		 * Carregando propriedades
		 */
		try {
		//	System.out.println("passo");
			
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.gmail.com");
		props.setProperty("mail.transport.protocol", "smtp");
		// Comente essas 4 linhas abaixo se deseja usar o SSL
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		
		Authenticator auth = new SMTPAuthenticator(login, senha);
		Session session = Session.getInstance(props, auth);

		MimeMessage msg = new MimeMessage(session);
		msg.setText("Testando email");
		msg.setSubject(subject);
		msg.setFrom(new InternetAddress(to));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setContent(content, "text/html");
		msg.setSentDate(new Date());
		
		Transport.send(msg);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro");
		}

	}
}
