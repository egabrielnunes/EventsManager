package Interface;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.Facade;

@WebServlet(urlPatterns={"/DeletarEventoPublico", "/depu"}, name="DeletarEventoPublico")

public class DeletarEventoPublico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeletarEventoPublico() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//formulario de delecao de evento
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head></head><body style='background: #fff8db;'>");
		out.println("<fieldset>");
		out.println("<form action='depu' method='post'>");
		out.println("Digite o id do evento para ele ser deletado: <input type='text' name='id'><br><br>");
		out.println("<input type='submit' value='Apagar Evento'></form>");
		out.println("</fieldset>");
		out.println("</body></html>");


	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		
		int id = Integer.parseInt(request.getParameter("id"));
			try {
			Facade.getInstancia().deletarEventoPu(id);//deleta evento
			out.println("<html><head><meta http-equiv='refresh' content= '3; url=Layout'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Evento Publico, deletado com sucesso.</b></div></body></html>");

			} catch (Exception e) {
				out.println("<html><head><meta http-equiv='refresh' content= '3; url=depu'></head><body style='background: #fff8db;'>" +
						"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
						"<b>Falha ao deletar o Evento Publico.</b></div></body></html>");
			}
	}

}
