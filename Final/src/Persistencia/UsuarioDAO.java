package Persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Negocio.Usuario;

public class UsuarioDAO {
	
	private Connection con; // representa uma conexão física com o agbd
	private String driver = "com.mysql.jdbc.Driver"; // nome do drive que estou acessando no banco
	private String url = "jdbc:mysql://localhost:3306/projeto1"; // protocolo e endereço sgbd na rede

	private String usuario1 = "root"; // nome do usuario
	private String senha1 = "root"; // senha do usuario
	Usuario a = null;
	String b = null;
	
	private static UsuarioDAO instance = null;
	
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public static UsuarioDAO getInstance() {

		if (instance == null) {
			instance = new UsuarioDAO();
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

	public void fechaConexao() { //metodo para fechar conexao
		try {
			con.close();
			con = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean autorizaUsuario(String login, String senha) 
			throws IOException { // verifica se usuario e senha são validos

		try {
			String sql = "select login, senha from usuario where login = ? and senha = ? ";// consulta o banco o usuario e senha
			PreparedStatement stm = getConexao().prepareStatement(sql);

			stm.setString(1, login);
			stm.setString(2, senha);
			ResultSet rs = stm.executeQuery();
			return rs.isBeforeFirst(); // recebe retorno true ou false da presença do usuario e senha 
			//no banco e os transforma em string
		//return a;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean cadastrarUsuarios(Usuario usuario) throws IOException { ; //cadastra usuarios
	try {
		String inserir = "insert into usuario(nome, cpf, login, senha, email, endereco, data, foto) values (?,?,?,?,?,?,?,?)";//insere dados
		PreparedStatement stm = getConexao().prepareStatement(inserir);//cria Statment
		stm.setString(1, usuario.getNome());
		stm.setString(2, usuario.getCpf());
		stm.setString(3, usuario.getLogin());
		stm.setString(4, usuario.getSenha());
		stm.setString(5, usuario.getEmail());
		stm.setString(6, usuario.getEnd());
		stm.setDate(7, new java.sql.Date(usuario.getData().getTime()));
		stm.setString(8, usuario.getFoto());
		
		stm.executeUpdate();// executa a atualização
		fechaConexao();

		return true;
	} catch (SQLException e) {
		e.printStackTrace();// ver erro que foi gerado
		return false;
	}
}	
	
	public ArrayList<Usuario> listarUsuario() throws IOException { //recupera usuarios
		usuarios.removeAll(usuarios);
		String email = null;
		String nome = null;
		int valor =0;
		
		try{ 
			String sql1 = "select count(nome) from usuario"; // consulta o banco
			
			PreparedStatement stmt = getConexao().prepareStatement(sql1);

			ResultSet rs1 = stmt.executeQuery();
			if (rs1.next()) {
				valor = rs1.getInt("count(nome)");
			}
		
			String sql = "select nome, email, cpf from usuario"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			
			String cpf = null;
			for (int i = 0; i < valor; i++) {
				if (rs.next()) {
					nome = (rs.getString("nome"));
					cpf = (rs.getString("cpf"));
					email = (rs.getString("email"));
				}
				Usuario a = new Usuario(nome, email, cpf);
				
				usuarios.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return usuarios;
	}	

	public ArrayList<Usuario> listarUsuarioPorNome(String c) throws IOException { //recupera usuarios por nome
		usuarios.removeAll(usuarios);
		Date data = null;
		String nome = null;
		String cpf = null;
		String login = null;

		try{
			String sql = "select  cpf, login, nome, data from usuario where nome like ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);
		
			stm.setString(1, "%" + c + "%");

			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
					nome = (rs.getString("nome"));
					cpf = (rs.getString("cpf"));
					login = (rs.getString("login"));
					data = new Date(rs.getDate("data").getTime());
					
					Usuario a = new Usuario(nome, cpf, login, data);
		
				usuarios.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return usuarios;
	}	
	
	public ArrayList<Usuario> listarUsuarioPorData(java.util.Date d, java.util.Date dd) throws IOException { //recupera usuarios por nome
		usuarios.removeAll(usuarios);
		Date data = null;
		String nome = null;
		String cpf = null;
		String login = null;
		try{
			String sql = "select  cpf, login, nome, data from usuario where data between ? and ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);

			stm.setDate(1, new java.sql.Date(d.getTime()));
			stm.setDate(2, new java.sql.Date(dd.getTime()));

			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
					nome = (rs.getString("nome"));
					cpf = (rs.getString("cpf"));
					login = (rs.getString("login"));
					data = new Date(rs.getDate("data").getTime());
					
					Usuario a = new Usuario(nome, cpf, login, data);
		
				usuarios.add(a);
				System.out.println(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return usuarios;
	}	


	public ArrayList<Usuario> listarUsuarioPorLogin(String Login) throws IOException { //recupera usuario por login
		usuarios.removeAll(usuarios);
		String login = null;
		String nome = null;
		String cpf = null;
		Date data = null;
		
		try{
			String sql = "select cpf, login, nome, data from usuario where login = ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);
			
			stm.setString(1, Login);

			ResultSet rs = stm.executeQuery();
			
				if (rs.next()) {
					nome = (rs.getString("nome"));
					cpf = (rs.getString("cpf"));
					login = (rs.getString("login"));
					data = new Date(rs.getDate("data").getTime());
				}
				Usuario a = new Usuario(nome, cpf, login, data);
				
				usuarios.add(a);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return usuarios;
	}
	
	public String FotoUsuario(String cpf) throws IOException { //recupera foto do usuario
		String foto = null;
		
		try{ 
			String sql = "select foto from usuario where cpf = ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);

			stm.setString(1, cpf);

			ResultSet rs = stm.executeQuery();
			
			if (rs.next()) {
				foto = (rs.getString("foto"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return foto;
	}

	@SuppressWarnings("deprecation")
	public String perfilUsuario(String cpf) throws IOException { //recupera perfil do usuario
		
		String email = null;
		String nome = null;
		String login = null;
		String end = null;
		Date data = null;
		
		try{ 
			
			String sql = "select nome, email, login, senha, endereco, data from usuario where cpf= ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);
			
			stm.setString(1, cpf);

			ResultSet rs = stm.executeQuery();
			
			
				if (rs.next()) {
					nome = (rs.getString("nome"));
					email = (rs.getString("email"));
					data = new Date(rs.getDate("data").getTime());
					end = (rs.getString("endereco"));
					login = (rs.getString("login"));
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
				String data01 = sdf.format(data);
	
			b = ("Nome: " + nome + "<br>" + "Email: " + email + "<br>" + "Cpf: " + cpf + "<br>"+ "Endereço: " + end + "<br>"+ 
			"Login: " + login + "<br>"+ "Data: " + data.getDate() + "/"+ data01 + "<br>" );
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return b;
	}

	public Usuario objetoUsuario(String login1, String senha1) throws IOException { //recupera o objeto do usuario para a criacao da sessao
		
		String email = null;
		String foto = null;
		String nome = null;
		String cpf = null;
		String endereco = null;
		String login = null;
		String senha = null;
		Date data = null;
		
		try{ 
			String sql = "select * from usuario where login = ? and senha = ?"; //consulta o banco
			PreparedStatement stm = getConexao().prepareStatement(sql);
			
			stm.setString(1, login1);
			stm.setString(2, senha1);
			ResultSet rs = stm.executeQuery();
				
				if (rs.next()) {
					nome = rs.getString("nome");
					email = rs.getString("email");
					foto = rs.getString("foto");
					cpf = rs.getString("cpf");
					login = rs.getString("login");
					senha = rs.getString("senha");
					endereco = rs.getString("endereco");
					data = new Date(rs.getDate("data").getTime());
					
				}
				Usuario u = new Usuario(nome, endereco, cpf, login, senha, email, data, foto);
				return u;
		} catch (SQLException e) {
			e.printStackTrace();
		} //informa erro gerado
			return null;
			
	}

	public boolean atualizarUsuario(String cpf, Usuario usuario) throws IOException { //atualiza usuario
		
		try {
			
			String update = "UPDATE usuario SET nome = ?, cpf = ?, login =?, senha = ?, email = ?,  endereco = ?,  data = ?,  foto = ? WHERE cpf = ? ";//atualiza dados
			PreparedStatement stm = getConexao().prepareStatement(update);//cria Statment
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getCpf());
			stm.setString(3, usuario.getLogin());
			stm.setString(4, usuario.getSenha());
			stm.setString(5, usuario.getEmail());
			stm.setString(6, usuario.getEnd());
			stm.setDate(7, new java.sql.Date(usuario.getData().getTime()));
			stm.setString(8, usuario.getFoto());
			stm.setString(9, cpf);
			
			stm.executeUpdate();// executa a atualização
			fechaConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();// ver erro que foi gerado
			return false;
		}
	}
	
	public boolean deletarUsuario(Usuario u) throws IOException { // deleta usuario

		try {	
			String deletar = "delete from usuario where cpf = ?";// deleta dados
			PreparedStatement stm = getConexao().prepareStatement(deletar);// cria Statment

			stm.setString(1, u.getCpf());

			stm.executeUpdate();// executa a atualização
			fechaConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();// ver erro que foi gerado
			return false;
		}
	}
				
}