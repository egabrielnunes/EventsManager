package Negocio;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	private String login;
	private String password;

	public SMTPAuthenticator(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(login, password);
	}
}