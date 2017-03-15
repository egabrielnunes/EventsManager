package Interface;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.Usuario;
@WebServlet(urlPatterns={"/Eventos", "/e" }, name="Eventos")
public class Eventos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Eventos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		
		out.println("<html><head></head><body style='background: #fff8db;'>");
		out.println("<fieldset style='text-align: center'>");
		out.println("<form action='epu' method='get'>");
		out.println("<input type='submit' value='Eventos Públicos'></form>");
		out.println("<form action='epr' method='get'>");
		out.println("<input type='submit' value='Eventos Privados'></form>");
		out.println("<form action='liepd' method='get'>");
		out.println("<input type='submit' value='Lista Eventos Por Data'></form>");
		out.println("<form action='leppc' method='get'>");
		out.println("<input type='submit' value='Lista Eventos Por Criador'></form>");
		out.println("<form action='liepn' method='get'>");
		out.println("<input type='submit' value='Lista Eventos Por Nome'></form>");
		out.println("<form action='e' method='post'>");
		out.println("<input type='submit' value='Logout'></form>");
		out.println("</fieldset>");
		out.println("</body></html>");

	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { //faz o logout
		ServletOutputStream out = response.getOutputStream();

		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
		if (usuario != null) {
			request.getSession(true).invalidate();//desativa sessao
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
