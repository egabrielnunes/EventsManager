	package Junit;

	import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import Negocio.EventosPrivados;
import Negocio.EventosPublicos;
import Negocio.Facade;
import Negocio.Usuario;


	public class FacadeTest {
		
		private Facade facade;
		private EventosPrivados eventosPrivados;
		private EventosPublicos eventosPublicos;
		private Usuario usuario;
		
		@Before
		public void setUp() throws Exception {
			facade = new Facade();
		}
		@Test
		public void testCadastrarUsuario() throws IOException {
			assertNotNull(facade.cadastrarUsuario(usuario));
		}
		@Test
		public void testListarUsuario() throws IOException {
			assertNotNull(facade.listarUsuarios());
		}
		@Test
		public void testAtualizarUsuario()throws IOException {
			assertNotNull(facade.atualizarUsuario(usuario, usuario.getCpf()));
		}
		@Test
		public void testDeletarUsuario()throws IOException  {
			assertNotNull(facade.deletarUsuario(usuario.getCpf()));
		}
		@Test
		public void testAutenticarUsuario()throws IOException  {
			assertNotNull(facade.autorizaUsuario(usuario.getLogin(), usuario.getSenha()));
		}
		@Test
		public void testCadastrarEventosPublicos() throws IOException {
			assertNotNull(facade.cadastrarEventoPu(eventosPublicos, usuario.getCpf()));
		}
		@Test
		public void testListarEventosPublicosConvidados() throws IOException {
			assertNotNull(facade.listarEventosPublicosConfirmados(usuario.getCpf()));
		}
		public void testListarEventosPublicosConfirmados() throws IOException {
			assertNotNull(facade.listarEventosPublicosConfirmados(usuario.getCpf()));
		}
		@Test
		public void testAtualizarEventosPublicos() throws IOException {
			assertNotNull(facade.atualizarEventoPu(eventosPublicos, eventosPublicos.getId(), usuario.getCpf()));
		}
		@Test
		public void testDeletarEventosPublicos()throws IOException  {
			assertNotNull(facade.deletarEventoPu(eventosPublicos.getId()));
		}
		@Test
		public void testCadastrarEventosPrivados() throws IOException {
			assertNotNull(facade.cadastrarEventoPri(eventosPrivados, usuario.getCpf()));
		}
		@Test
		public void testListarEventosPrivados() throws IOException {
			assertNotNull(facade.listarEventosPrivados(usuario.getCpf()));
		}
		@Test
		public void testAtualizarEventosPrivados()throws IOException {
			assertNotNull(facade.atualizarEventoPri(eventosPrivados, eventosPrivados.getId(), usuario.getCpf()));
		}
		@Test
		public void testDeletarEventosPrivados()throws IOException  {
			assertNotNull(facade.deletarEventoPri(eventosPrivados.getId()));
		}
	}
