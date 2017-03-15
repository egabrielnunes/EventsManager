package Negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import Negocio.Facade;
import Persistencia.ConfirmadosDAO;
import Persistencia.ConvidadosDAO;
import Persistencia.EventosPrivadosDAO;
import Persistencia.EventosPublicosDAO;
import Persistencia.UsuarioDAO;


public class Facade {

	private Usuario u = new Usuario();
	private EventosPrivados epr = new EventosPrivados();
	private EventosPublicos epu = new EventosPublicos();
	
	private static Facade instancia = new Facade();


	UsuarioDAO usuario;
	EventosPrivadosDAO eventoPrivado;
	EventosPublicosDAO eventoPublico;
	ConfirmadosDAO confirmados;
	ConvidadosDAO convidados;

	public Facade() {
		usuario = UsuarioDAO.getInstance();
		eventoPrivado = EventosPrivadosDAO.getInstance();
		eventoPublico = EventosPublicosDAO.getInstance();
		confirmados = ConfirmadosDAO.getInstance();
		convidados = ConvidadosDAO.getInstance();
	}

	public static Facade getInstancia() {
		return instancia;
	}
	
	public boolean autorizaUsuario(String login, String senha) throws IOException {
		u.setLogin(login); // login usuario
		u.setSenha(senha);// senha usuario

		return usuario.autorizaUsuario(login, senha); // retorno do banco se for true pode entrar no sistema

	}
	
	public String recuperaId(String titulo) throws IOException { //recupera id evento publico
		return eventoPublico.RecuperaId(titulo);
	}

	public Usuario objetoUsuario(String login, String senha) throws IOException { //recupera objeto usuario para a sessao
		
		return usuario.objetoUsuario(login, senha);
	}	
	
	public boolean cadastrarConvidado(String cpf, String email, int id) throws IOException { //cadastra usuarios convidados
		
		return convidados.cadastrarConvidado(cpf, email, id);
	}	

	public boolean cadastrarConfirmado(String cpf, String email, String id) throws IOException { //cadastra usuarios confirmados
		
		return confirmados.cadastrarConfirmado(cpf, email, id);
	}
	
	public String perfilUsuario(String cpf) throws IOException { //recupera perfil do usuario
		
		return usuario.perfilUsuario(cpf);
	}

	public String fotoUsuario(String cpf) throws IOException { //recupera foto do usuario 
		
		return usuario.FotoUsuario(cpf);
	}
	
	public boolean cadastrarUsuario(Usuario u) throws IOException { ///cadastra usuarios
		
		return usuario.cadastrarUsuarios(u);
	}

	public boolean cadastrarEventoPri(EventosPrivados epr, String cpf) throws IOException { //cadastra eventos privados
		
		return eventoPrivado.cadastrarEventosPrivados(epr, cpf);
	}

	public boolean cadastrarEventoPu(EventosPublicos epu, String cpf) throws IOException { //cadastra eventos publicos
		
		return eventoPublico.cadastrarEventosPublicos(epu, cpf);
	}

	public boolean atualizarUsuario(Usuario u, String cpf) throws IOException { //atualiza usuarios
		
		return usuario.atualizarUsuario(cpf, u);
	}
	
	public boolean atualizarEventoPri(EventosPrivados epr, int id, String cpf) throws IOException { //atualiza eventos privado
		
		return eventoPrivado.atualizarEventosPrivados(id, epr, cpf);
	}

	public boolean atualizarEventoPu(EventosPublicos epu, int id, String cpf) throws IOException {//atualiza eventos publicos
		
		return eventoPublico.atualizarEventosPublicos(id, cpf, epu);
	}
	
	public boolean deletarUsuario(String cpf) throws IOException { //deleta usuario
		u.setCpf(cpf);
		
		return usuario.deletarUsuario(u);
	}

	public boolean deletarEventoPu(int id) throws IOException { //deleta evento publico
		epu.setId(id);
		
		return eventoPublico.deletarEventosPublicos(epu);
	}

	public boolean deletarEventoPri(int id) throws IOException { //deleta evento privado
		epr.setId(id);
		
		return eventoPrivado.deletarEventosPrivados(epr);
	}

	public ArrayList<Usuario> listarUsuarios() throws IOException { //Lista usuarios
		
		return usuario.listarUsuario();
	}
	
	public ArrayList<EventosPrivados> listarEventosPrivados(String cpf) throws IOException { //Lista eventos privados
		
		return eventoPrivado.listarEventosPrivados(cpf);
	}

	public ArrayList<EventosPublicos> listarEventosPublicos(String cpf) throws IOException { //Lista eventos publicos
		
		return eventoPublico.listarEventosPublicos(cpf);
	}
	
	public ArrayList<Confirmados> listarEventosPublicosConfirmados(String cpf) throws IOException { //Lista usuarios confirmados em evento publico
		
		return confirmados.listaConfirmados(cpf);
	}
		
	public ArrayList<Convidados> listarEventosPublicosConvidados(String cpf) throws IOException { //Lista usuarios convidados para evento publico
		
		return convidados.listaConvidados(cpf);
	}
	
	public ArrayList<Usuario> listarUsuarioPorLogin(String login) throws IOException { //Lista usuarios por login
		
		return usuario.listarUsuarioPorLogin(login);
	}
	
	public ArrayList<Usuario> listarUsuarioPorNome(String nome) throws IOException { //Lista usuarios por nome
		
				return usuario.listarUsuarioPorNome(nome);
	}
	
	public ArrayList<EventosPublicos> listarEventosPublicosPorNome(String titulo, String cpf) throws IOException { //Lista eventos publicos por nome
		
		return eventoPublico.listarEventosPublicosPorNome(titulo, cpf);
	}
	
	public ArrayList<EventosPrivados> listarEventosPrivadosPorNome(String titulo,String cpf) throws IOException { //Lista eventos privados por nome
		
		return eventoPrivado.listarEventosPrivadosPorNome(titulo, cpf);
	}
	
	public ArrayList<Usuario> listarUsuarioPorData(Date d, Date dd) throws IOException { //Lista usuarios por intervalo de datas de nascimento

		return usuario.listarUsuarioPorData(d, dd);
	}
	
	public ArrayList<EventosPublicos> listarEventosPublicosPorCriador(String cpf) throws IOException { //Lista evento por criador
		//incompleto :(
		//mudar para login
		return eventoPublico.listarEventosPublicos(cpf);
	}
	
	public ArrayList<EventosPublicos> listarEventosPublicosPorData(String titulo, String cpf) throws IOException {//Lista eventos por intervalor de datas
		//incompleto :(
		//por data
		return eventoPublico.listarEventosPublicosPorNome(titulo, cpf);
	}
	
	public ArrayList<EventosPrivados> listarEventosPrivadosPorData(Date d, Date dd, String cpf) throws IOException {//Lista eventos por intervalor de datas 
		//incompleto :(
		//por data
		return eventoPrivado.listarEventosPrivadosPorData(d, dd, cpf);
	}

	
}
