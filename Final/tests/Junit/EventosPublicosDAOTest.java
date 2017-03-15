	package Junit;

	import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import Negocio.EventosPublicos;
import Negocio.Usuario;
import Persistencia.EventosPublicosDAO;


	public class EventosPublicosDAOTest {
		
		private EventosPublicosDAO eventosPublicosDAO;
		private EventosPublicos eventosPublicos;
		private Usuario usuario;
		
		@Before
		public void setUp() throws Exception {
			eventosPublicosDAO = new EventosPublicosDAO();
		}
			
		@Test
		public void testCadastrarEventosPublicosDAO() throws IOException {
			assertNotNull(eventosPublicosDAO.cadastrarEventosPublicos(eventosPublicos, usuario.getCpf()));
		}
		@Test
		public void testListarEventosPublicosDAO() throws IOException {
			assertNotNull(eventosPublicosDAO.listarEventosPublicos(usuario.getCpf()));
		}
		@Test
		public void testAtualizarEventosPublicosDAO()throws IOException {
			assertNotNull(eventosPublicosDAO.atualizarEventosPublicos(eventosPublicos.getId(),usuario.getCpf() ,eventosPublicos));
		}
		@Test
		public void testDeletarEventosPublicosDAO()throws IOException  {
			assertNotNull(eventosPublicosDAO.deletarEventosPublicos(eventosPublicos));
		}
	}
