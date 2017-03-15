	package Junit;

	import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import Negocio.EventosPrivados;
import Negocio.Usuario;
import Persistencia.EventosPrivadosDAO;


	public class EventosPrivadosDAOTest {
		
		private EventosPrivadosDAO eventosPrivadosDAO;
		private EventosPrivados eventosPrivados;
		private Usuario usuario;
		
		@Before
		public void setUp() throws Exception {
			eventosPrivadosDAO = new EventosPrivadosDAO();
		}
			
		@Test
		public void testCadastrarEventosPrivadosDAO() throws IOException {
			assertNotNull(eventosPrivadosDAO.cadastrarEventosPrivados(eventosPrivados, usuario.getCpf()));
		}
		@Test
		public void testListarEventosPrivadosDAO() throws IOException {
			assertNotNull(eventosPrivadosDAO.listarEventosPrivados(usuario.getCpf()));
		}
		@Test
		public void testAtualizarEventosPrivadosDAO()throws IOException {
			assertNotNull(eventosPrivadosDAO.atualizarEventosPrivados(eventosPrivados.getId(), eventosPrivados, usuario.getCpf()));
		}
		@Test
		public void testDeletarEventosPrivadosDAO()throws IOException  {
			assertNotNull(eventosPrivadosDAO.deletarEventosPrivados(eventosPrivados));
		}
	}
