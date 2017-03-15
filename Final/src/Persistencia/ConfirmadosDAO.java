package Persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Negocio.Confirmados;

public class ConfirmadosDAO {
	
	private Connection con; // representa uma conexão física com o agbd
	private String driver = "com.mysql.jdbc.Driver"; // nome do drive que estou acessando no banco
	private String url = "jdbc:mysql://localhost:3306/projeto1"; // protocolo e endereço sgbd na rede

	private String usuario1 = "root"; // nome do usuario
	private String senha1 = "root"; // senha do usuario

	private static ConfirmadosDAO instance = null;

	ArrayList<Confirmados> confirmados = new ArrayList<Confirmados>();
	
	public static ConfirmadosDAO getInstance() {

		if (instance == null) {
			instance = new ConfirmadosDAO();
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
	
	
	public boolean cadastrarConfirmado(String cpf, String email, String idPublico) throws IOException { ; //cadastra usuarios confirmados
	
	try {
		String inserir = "insert into confirmados(cpf, email, idPublico) values (?,?,?)";//insere dados
		PreparedStatement stm = getConexao().prepareStatement(inserir);//cria Statment

		stm.setString(1, cpf);
		stm.setString(2, email);
		stm.setString(3, idPublico);
		
		stm.executeUpdate();// executa a atualização
		fechaConexao();

		return true;
	} catch (SQLException e) {
		e.printStackTrace();// ver erro que foi gerado
		return false;
	}
	}	
   
	public ArrayList<Confirmados> listaConfirmados(String cpf) throws IOException { //lista usuarios confirmados
		
		confirmados.removeAll(confirmados);
		int id = 0;
		int idP = 0;
		String email = null;
		String cpfF = null;
		int valor =0;
			
		try{ 
			String sql1 = "select count(id) from confirmados where cpf = ?"; // consulta o banco
			
			PreparedStatement stmt = getConexao().prepareStatement(sql1);
			stmt.setString(1, cpf);
	
			ResultSet rs1 = stmt.executeQuery();
			if (rs1.next()) {
				valor = rs1.getInt("count(id)");
			}
		
			String sql = "select id, idPublico, email, cpf from confirmados where cpf = ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);
			stm.setString(1, cpf);
			
			ResultSet rs = stm.executeQuery();
				
			for (int i = 0; i < valor; i++) {
				if (rs.next()) {
					id = (rs.getInt("id"));
					idP = (rs.getInt("idPublico"));
					email = (rs.getString("email"));
					cpfF = (rs.getString("cpf"));
				}	
					Confirmados b = new Confirmados(id, idP, email, cpfF);
					confirmados.add(b);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return confirmados;
			
	}

}
