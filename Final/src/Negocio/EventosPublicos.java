package Negocio;

public class EventosPublicos {
	
	private String tituloEPu;
	private String descricaoEPu;
	private String dataEPu;
	private String horaEPu;
	private String duracaoEPu;
	private int id;
	

	public EventosPublicos(String tituloEPu, String dataEPu, int id) {
		super();
		this.tituloEPu = tituloEPu;
		this.dataEPu = dataEPu;
		this.id = id;
	}

	public EventosPublicos() {
		super();
	}

	public EventosPublicos(String tituloEPu, String descricaoEPu,
			String dataEPu, String horaEPu, String duracaoEPu) {
		super();
		this.tituloEPu = tituloEPu;
		this.descricaoEPu = descricaoEPu;
		this.dataEPu = dataEPu;
		this.horaEPu = horaEPu;
		this.duracaoEPu = duracaoEPu;
	}
	
	public String getTituloEPu() {
		return tituloEPu;
	}
	public void setTituloEPu(String tituloEPu) {
		this.tituloEPu = tituloEPu;
	}
	public String getDescricaoEPu() {
		return descricaoEPu;
	}
	public void setDescricaoEPu(String descricaoEPu) {
		this.descricaoEPu = descricaoEPu;
	}
	public String getDataEPu() {
		return dataEPu;
	}
	public void setDataEPu(String dataEPu) {
		this.dataEPu = dataEPu;
	}
	public String getHoraEPu() {
		return horaEPu;
	}
	public void setHoraEPu(String horaEPu) {
		this.horaEPu = horaEPu;
	}
	public String getDuracaoEPu() {
		return duracaoEPu;
	}
	public void setDuracaoEPu(String duracaoEPu) {
		this.duracaoEPu = duracaoEPu;
	}
//	public String getConvidados() {
//		return convidados;
//	}
//	public void setConvidados(String convidados) {
//		this.convidados = convidados;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
