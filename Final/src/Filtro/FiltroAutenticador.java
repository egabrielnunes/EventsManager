package Filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import Negocio.Usuario;

@WebFilter(
		servletNames = { "AtualizarEventoPrivado", 
				"AtualizarEventoPublico", 
				"AtualizarUsuario", 
				"CriarEventoPrivado", 
				"CriarEventoPublico", 
				"DeletarEventoPrivado", 
				"DeletarEventoPublico", 
				"DeletarUsuario", 
				"EventoPrivado", 
				"EventoPublico", 
				"ListarEventosPrivados", 
				"ListarEventosPublicos", 
				"ListarEventosPublicosConfirmados", 
				"ListarEventosPublicosConvidados", 
				"ListarMeusEventosPublicos",
				"Layout",
				"ListarUsuarioPorLogin",
				"Usuarios",
				"ListarUsuarioPorNome",
				"ListarUsuariosPorData",
				"ListarEventosPorNomes",
				"ListarEventosPorData",
				"ListarEventosPublicosPorCriador",
				"PerfilUsuario",
				"ListarUsuarios",
				"Eventos"
		})
public class FiltroAutenticador implements Filter {

    public FiltroAutenticador() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
	  	ServletOutputStream out = response.getOutputStream();
		
		Usuario usuario = (Usuario) httpRequest.getSession(true).getAttribute("usuario");
		if (usuario == null) {
			out.println("<html><head><meta http-equiv='refresh' content= '3; url=1'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Por favor, efetue o login.</b></div></body></html>");

		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
