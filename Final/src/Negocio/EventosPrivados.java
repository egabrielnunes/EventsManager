package Negocio;

import java.util.Date;

public class EventosPrivados {
	
	private String tituloEPri;
	private String descricaoEPri;
	private Date dataEPri;
	private String horaEPri;
	private String duracaoEPri;
	private Date dataAgendamento;
	private String horaAgendamento;
	private int id;
	
	
	
	public EventosPrivados(String tituloEPri, Date dataEPri, int id) {
		super();
		this.tituloEPri = tituloEPri;
		this.dataEPri = dataEPri;
		this.id = id;
	}
	public EventosPrivados() {
		super();
	}
	public EventosPrivados(String tituloEPri, String descricaoEPri,
			Date dataEPri, String horaEPri, String duracaoEPri) {
		super();
		this.tituloEPri = tituloEPri;
		this.descricaoEPri = descricaoEPri;
		this.dataEPri = dataEPri;
		this.horaEPri = horaEPri;
		this.duracaoEPri = duracaoEPri;
	}
	public EventosPrivados(String tituloEPri, String descricaoEPri,
			Date dataEPri, String horaEPri, String duracaoEPri,
			Date dataAgendamento, String horaAgendamento) {
		super();
		this.tituloEPri = tituloEPri;
		this.descricaoEPri = descricaoEPri;
		this.dataEPri = dataEPri;
		this.horaEPri = horaEPri;
		this.duracaoEPri = duracaoEPri;
		this.dataAgendamento = dataAgendamento;
		this.horaAgendamento = horaAgendamento;
	}
	public String getTituloEPri() {
		return tituloEPri;
	}
	public void setTituloEPri(String tituloEPri) {
		this.tituloEPri = tituloEPri;
	}
	public String getDescricaoEPri() {
		return descricaoEPri;
	}
	public void setDescricaoEPri(String descricaoEPri) {
		this.descricaoEPri = descricaoEPri;
	}
	public Date getDataEPri() {
		return dataEPri;
	}
	public void setDataEPri(Date dataEPri) {
		this.dataEPri = dataEPri;
	}
	public String getHoraEPri() {
		return horaEPri;
	}
	public void setHoraEPri(String horaEPri) {
		this.horaEPri = horaEPri;
	}
	public String getDuracaoEPri() {
		return duracaoEPri;
	}
	public void setDuracaoEPri(String duracaoEPri) {
		this.duracaoEPri = duracaoEPri;
	}
	public Date getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	public String getHoraAgendamento() {
		return horaAgendamento;
	}
	public void setHoraAgendamento(String horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
		
}
