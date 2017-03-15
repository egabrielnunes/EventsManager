package Negocio;

public class Convidados {
	private int id;
	private int idP;
	private String emailU;
	private String cpfU;
	
	public Convidados(int id, int idP, String emailU, String cpfU) {
		super();
		this.id = id;
		this.idP = idP;
		this.emailU = emailU;
		this.cpfU = cpfU;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdP() {
		return idP;
	}

	public void setIdP(int idP) {
		this.idP = idP;
	}

	public String getEmailU() {
		return emailU;
	}

	public void setEmailU(String emailU) {
		this.emailU = emailU;
	}

	public String getCpfU() {
		return cpfU;
	}

	public void setCpfU(String cpfU) {
		this.cpfU = cpfU;
	}
	

}
