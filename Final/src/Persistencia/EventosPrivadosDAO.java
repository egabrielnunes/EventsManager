package Persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Negocio.EventosPrivados;



public class EventosPrivadosDAO {
	
	private Connection con; // representa uma conexão física com o agbd
	private String driver = "com.mysql.jdbc.Driver"; // nome do drive que estou acessando no banco
	private String url = "jdbc:mysql://localhost:3306/projeto1"; // protocolo e endereço sgbd na rede

	private String usuario1 = "root"; // nome do usuario
	private String senha1 = "root"; // senha do usuario

	private static EventosPrivadosDAO instance = null;
	ArrayList<EventosPrivados> eventos = new ArrayList<EventosPrivados>();
	ArrayList<String> eventos1 = new ArrayList<String>();
	

	public static EventosPrivadosDAO getInstance() {

		if (instance == null) {
			instance = new EventosPrivadosDAO();
		}
		return instance;
	}

	private void conectar() { // criando o metodo para abrir conexao
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, usuario1, senha1);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection getConexao() {
		if (con == null) {
			conectar();
		}
		return con;
	}

	public void fechaConexao() {
		try {
			con.close();
			con = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean cadastrarEventosPrivados(EventosPrivados eventosPrivados, String cpf) throws IOException { ; //cadastra evento privado
	
	try {
		String inserir = "insert into privado(titulo, descricao, duracao, hora, data, data_agendamento , hora_agendamento, cpf) values (?,?,?,?,?,?,?,?)";//insere dados
		PreparedStatement stm = getConexao().prepareStatement(inserir);//cria Statment

		stm.setString(1, eventosPrivados.getTituloEPri());
		stm.setString(2, eventosPrivados.getDescricaoEPri());
		stm.setString(3, eventosPrivados.getDuracaoEPri());
		stm.setString(4, eventosPrivados.getHoraEPri());
		stm.setDate(5, new java.sql.Date(eventosPrivados.getDataEPri().getTime()));
		stm.setDate(6, new java.sql.Date(eventosPrivados.getDataAgendamento().getTime()));
		stm.setString(7, eventosPrivados.getHoraAgendamento());
		stm.setString(8, cpf);
		
		stm.executeUpdate();// executa a atualização
		fechaConexao();

		return true;
	} catch (SQLException e) {
		e.printStackTrace();// ver erro que foi gerado
		return false;
	}
}	

	public ArrayList<EventosPrivados> listarEventosPrivadosPorData(java.util.Date d, java.util.Date dd, String cpf) throws IOException { //recupera usuarios por nome
		eventos.removeAll(eventos);
		Date data = null;
		int id = 0;
		String titulo = null;
		
		try{
			String sql = "select id, titulo, data from privado where cpf = ? and data between ? and ? "; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);

			stm.setDate(1, new java.sql.Date(d.getTime()));
			stm.setDate(2, new java.sql.Date(dd.getTime()));
			stm.setString(3, cpf);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
					id = (rs.getInt("id"));
					titulo = (rs.getString("titulo"));
					data = new Date(rs.getDate("data").getTime());
					
					EventosPrivados a = new EventosPrivados(titulo, data, id);
					
				eventos.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return eventos;
	}	


    public boolean atualizarEventosPrivados(int id, EventosPrivados eventosPrivados, String cpf) throws IOException { //atualiza evento privado
		
		try {
			String update = "UPDATE privado SET titulo = ?, descricao = ?, duracao =?, hora =?, data = ?, data_agendamento = ?, " +
					" hora_agendamento = ?,  cpf = ? WHERE id = ?";
			PreparedStatement stm = getConexao().prepareStatement(update);//cria Statment
			
			stm.setString(1, eventosPrivados.getTituloEPri());
			stm.setString(2, eventosPrivados.getDescricaoEPri());
			stm.setString(3, eventosPrivados.getDuracaoEPri());
			stm.setString(4, eventosPrivados.getHoraEPri());
			stm.setDate(5, new java.sql.Date(eventosPrivados.getDataEPri().getTime()));
			stm.setDate(6, new java.sql.Date(eventosPrivados.getDataAgendamento().getTime()));
			stm.setString(7, eventosPrivados.getHoraAgendamento());
			stm.setString(8, cpf);
			stm.setInt(9, id);
			stm.executeUpdate();// executa a atualização
			fechaConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();// ver erro que foi gerado
			return false;
		}
	}

	public ArrayList<EventosPrivados> listarEventosPrivados(String cpf) throws IOException { //recupera lista de eventos privados
		
		eventos.removeAll(eventos);
		String titulo = null;
		String descricao = null;
		Date data = null;
		String hora = null;
		String duracao = null;
		Date dataA = null;
		String horaA = null;
		int valor = 0;
		
		try{ 
			String sql1 = "select count(titulo) from privado where cpf = ?"; // consulta o banco
			PreparedStatement stmt = getConexao().prepareStatement(sql1);

			stmt.setString(1, cpf);
			ResultSet rs1 = stmt.executeQuery();
			
			if (rs1.next()) {
				valor = rs1.getInt("count(titulo)");
			}
		
			String sql = "select titulo,descricao,data,hora,duracao,data_agendamento,hora_agendamento from privado where cpf = ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);
			stm.setString(1, cpf);
			
			ResultSet rs = stm.executeQuery();
				
			for (int i = 0; i < valor; i++) {
				if (rs.next()) {
					titulo = (rs.getString("titulo"));
					descricao = (rs.getString("descricao"));
					data = new Date(rs.getDate("data").getTime());
					hora = (rs.getString("hora"));
					duracao = (rs.getString("duracao"));
					dataA = new Date(rs.getDate("data_agendamento").getTime());
					horaA = (rs.getString("hora_agendamento"));
				}
				EventosPrivados c = new EventosPrivados(titulo, descricao, data, hora, duracao, dataA, horaA);
				eventos.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
		return eventos;
	}

	public ArrayList<EventosPrivados> listarEventosPrivadosPorNome(String nome, String cpf) throws IOException { //recupera lista de eventos por nome
		eventos.removeAll(eventos);
		String id1 = null;
		int id = 0;
		String titulo = null;
		Date data = null;
		
		try{
			String sql = "select id, titulo, data from privado where titulo like ? and cpf = ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);

			stm.setString(1, "%" + nome + "%");
			stm.setString(2, cpf);

			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
					id1 = (rs.getString("id"));
					titulo = (rs.getString("titulo"));
					data = new Date(rs.getDate("data").getTime());
					id = Integer.parseInt(id1);
					EventosPrivados a = new EventosPrivados(titulo, data, id);
		
				eventos.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return eventos;
	}	
    
	public boolean deletarEventosPrivados(EventosPrivados ep) throws IOException { // deleta evento

		try {	
			String deletar = "delete from privado where id = ?";// deleta dados
			PreparedStatement stm = getConexao().prepareStatement(deletar);// cria Statment

			stm.setInt(1, ep.getId());

			stm.executeUpdate();// executa a atualização
			fechaConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();// ver erro que foi gerado
			return false;
		}
	}
}
