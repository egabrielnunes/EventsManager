package Persistencia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Negocio.EventosPublicos;

public class EventosPublicosDAO {
	
	private Connection con; // representa uma conexão física com o agbd
	private String driver = "com.mysql.jdbc.Driver"; // nome do drive que estou acessando no banco
	private String url = "jdbc:mysql://localhost:3306/projeto1"; // protocolo e endereço sgbd na rede

	private String usuario1 = "root"; // nome do usuario
	private String senha1 = "root"; // senha do usuario

	static PrintWriter out;
	private static EventosPublicosDAO instance = null;

	ArrayList<EventosPublicos> eventos = new ArrayList<EventosPublicos>();
	
	public static EventosPublicosDAO getInstance() {

		if (instance == null) {
			instance = new EventosPublicosDAO();
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
	
	public boolean cadastrarEventosPublicos(EventosPublicos eventosPublicos, String cpf) throws IOException { ; //cadastra evento publico
	
	try {
		String inserir = "insert into publicos(titulo, descricao, duracao, hora, data, cpf) values (?,?,?,?,?,?)";//insere dados
		PreparedStatement stm = getConexao().prepareStatement(inserir);//cria Statment

		stm.setString(1, eventosPublicos.getTituloEPu());
		stm.setString(2, eventosPublicos.getDescricaoEPu());
		stm.setString(3, eventosPublicos.getDuracaoEPu());
		stm.setString(4, eventosPublicos.getHoraEPu());
		stm.setString(5, eventosPublicos.getDataEPu());
		stm.setString(6, cpf);

		stm.executeUpdate();// executa a atualização
		fechaConexao();

		return true;
	} catch (SQLException e) {
		e.printStackTrace();// ver erro que foi gerado
		return false;
	}
}	
	
	public String RecuperaId(String titulo) throws IOException { //recupera id do evento
		String id = null;
		
		try{ 
			String sql = "select id from publicos where titulo = ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);

			stm.setString(1, titulo);

			ResultSet rs = stm.executeQuery();
			
			if (rs.next()) {
				id = (rs.getString("id"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return id;
	}

	public boolean atualizarEventosPublicos(int id, String cpf, EventosPublicos eventosPublicos) throws IOException { //atualiza evento publico
		
		try {
			String update = "UPDATE publicos SET titulo = ?, descricao = ?, duracao =?, hora = ?, data = ?,  cpf = ? WHERE id = ? ";//atualiza dados
			PreparedStatement stm = getConexao().prepareStatement(update);//cria Statment
			
			stm.setString(1, eventosPublicos.getTituloEPu());
			stm.setString(2, eventosPublicos.getDescricaoEPu());
			stm.setString(3, eventosPublicos.getDuracaoEPu());
			stm.setString(4, eventosPublicos.getHoraEPu());
			stm.setString(5, eventosPublicos.getDataEPu());
			stm.setString(6, cpf);
			stm.setInt(7, id);
			
			stm.executeUpdate();// executa a atualização
			fechaConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();// ver erro que foi gerado
			return false;
		}
	}
    
	public ArrayList<EventosPublicos> listarEventosPublicos(String cpf) throws IOException { //recupera lista de eventos 
		
		eventos.removeAll(eventos);
		String titulo = null;
		String descricao = null;
		String data = null;
		String hora = null;
		String duracao = null;
		//String convidados = null;
		int valor =0;
			
		try{ 
			String sql1 = "select count(titulo) from publicos where cpf = ?"; // consulta o banco
			
			PreparedStatement stmt = getConexao().prepareStatement(sql1);
			stmt.setString(1, cpf);
	
			ResultSet rs1 = stmt.executeQuery();
			if (rs1.next()) {
				valor = rs1.getInt("count(titulo)");
			}
		
			String sql = "select titulo,descricao,data,hora,duracao from publicos where cpf = ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);
			stm.setString(1, cpf);
			
			ResultSet rs = stm.executeQuery();
				
			for (int i = 0; i < valor; i++) {
				if (rs.next()) {
					titulo = (rs.getString("titulo"));
					descricao = (rs.getString("descricao"));
					data = (rs.getString("data"));
					hora = (rs.getString("hora"));
					duracao = (rs.getString("duracao"));
					//convidados = (rs.getString("convidados"));
				}	
					EventosPublicos b = new EventosPublicos(titulo, descricao, data, hora, duracao);
					eventos.add(b);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return eventos;
			
	}
	
	public ArrayList<EventosPublicos> listarEventosPublicosPorNome(String nome, String cpf) throws IOException { //recupera lista de eventos por nome
		eventos.removeAll(eventos);
		String id1 = null;
		int id = 0;
		String titulo = null;
		String data = null;
		
		try{
			String sql = "select  id, titulo, data from publicos where cpf = ? and titulo like ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);
		
			stm.setString(1, cpf);
			stm.setString(2, "%" + nome + "%");

			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
					id1 = (rs.getString("id"));
					titulo = (rs.getString("titulo"));
					data = (rs.getString("data"));
					id = Integer.parseInt(id1);
					EventosPublicos a = new EventosPublicos(titulo, data, id);
		
				eventos.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return eventos;
	}	

	public boolean deletarEventosPublicos(EventosPublicos ep) throws IOException { // deleta evento publico

		try {	
			String deletar = "delete from publicos where id = ?";// deleta dados
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
