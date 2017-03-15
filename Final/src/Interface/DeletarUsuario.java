package Interface;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Negocio.Facade;
import Negocio.Usuario;

@WebServlet(urlPatterns={"/DeletarUsuario", "/du"}, name="DeletarUsuario")

public class DeletarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeletarUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//formulario de delecao de conta
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head></head><body style='background: #fff8db;'>");
		out.println("<fieldset>");
		out.println("Deseja deletar a sua conta no SkyDiaries?<br>");
		out.println("<form action='du' method='post'>");
		out.println("<input type='submit' value='Sim'></form>");
		out.println("<form action='Layout' method='get'>");
		out.println("<input type='submit' value='Não'></form>");
		out.println("</fieldset>");
		out.println("</body></html>");
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//deleta usuario
		
		Usuario usuario  = (Usuario) request.getSession(true).getAttribute("usuario");//recupera cpf
		
		String cpfF = usuario.getCpf();
		
		ServletOutputStream out = response.getOutputStream();
		
		if(cpfF != ""){
			try {
				
			if(Facade.getInstancia().deletarUsuario(cpfF) == true) {//deleta usuario do banco
				out.println("<html><head><meta http-equiv='refresh' content= '3; url=Interface'></head><body style='background: #fff8db;'>" +
						"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
						"<b>Sua conta foi deletada com sucesso.</b></div></body></html>");
			} else {
				//response.sendRedirect("Erro.html");
				out.println("<html><head></head><body>erro</body></html>");
			}

			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			//response.sendRedirect("Erro.html");
			out.println("<html><head></head><body>erro</body></html>");
		}
	}

}
