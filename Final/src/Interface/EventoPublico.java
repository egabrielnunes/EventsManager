package Interface;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.Usuario;


@WebServlet(urlPatterns={"/EventoPublico", "/epu"}, name="EventoPublico")
public class EventoPublico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EventoPublico() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ServletOutputStream out = response.getOutputStream();
		out.println("<html><head></head><body style='background: #fff8db;'>");
		out.println("<fieldset style='text-align: center'>");
		out.println("<form action='cepu' method='get'>");
		out.println("<input type='submit' value='Criar Evento Público'></form>");
		out.println("<form action='depu' method='get'>");
		out.println("<input type='submit' value='Deletar Evento Público'></form>");
		out.println("<form action='lepu' method='get'>");
		out.println("<input type='submit' value='Listar Eventos Públicos'></form>");
		out.println("<form action='aepu' method='get'>");
		out.println("<input type='submit' value='Alterar Evento Público'></form>");
		out.println("<form action='epu' method='post'>");
		out.println("<input type='submit' value='Logout'></form>");
		out.println("</fieldset>");
		out.println("</body></html>");
		
	

	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {//faz o logout
		ServletOutputStream out = response.getOutputStream();

		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
		if (usuario != null) {
			request.getSession(true).invalidate();//invalida a sessao
			out.println("<html><head><meta http-equiv='refresh' content= '3; url=0'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Saindo..</b></div></body></html>");
		} else {
			out.println("<html><head><meta http-equiv='refresh' content= '3; url=1'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Por favor, efetue o login.</b></div></body></html>");
		}
	}


}
