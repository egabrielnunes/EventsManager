package Junit;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import Negocio.Usuario;
import Persistencia.UsuarioDAO;

public class UsuarioDAOTest {
	
	private UsuarioDAO usuario;
	private Usuario usuario2;
	
	@Before
	public void setUp() throws Exception {
		usuario = new UsuarioDAO();
	}
		
	@Test
	public void testCadastrarUsuarioDAO() throws IOException {
		assertNotNull(usuario.cadastrarUsuarios(usuario2));
	}
	@Test
	public void testListarUsuarioDAO() throws IOException {
		assertNotNull(usuario.listarUsuario());
	}
	@Test
	public void testAtualizarUsuarioDAO()throws IOException {
		assertNotNull(usuario.atualizarUsuario(usuario2.getCpf(), usuario2));
	}
	@Test
	public void testDeletarUsuarioDAO()throws IOException  {
		assertNotNull(usuario.deletarUsuario(usuario2));
	}
	@Test
	public void testAutenticarUsuarioDAO()throws IOException  {
		assertNotNull(usuario.autorizaUsuario(usuario2.getLogin(), usuario2.getSenha()));
	}
}
