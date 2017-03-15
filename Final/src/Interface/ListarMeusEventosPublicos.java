package Interface;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Negocio.EventosPublicos;
import Negocio.Facade;
import Negocio.Usuario;

@WebServlet(urlPatterns={"/ListarMeusEventosPublicos", "/lmepu"}, name="ListarMeusEventosPublicos")

public class ListarMeusEventosPublicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListarMeusEventosPublicos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //lista eventos publicos criados pelo proprio usuario
	ServletOutputStream out = response.getOutputStream();
		
	Usuario usuario  = (Usuario) request.getSession(true).getAttribute("usuario");
	String cpfF = usuario.getCpf();
	
		try {
			
		if(cpfF != ""){
			ArrayList<EventosPublicos> a = Facade.getInstancia().listarEventosPublicos(cpfF); //recupera lista pelo cpf
			 out.println("<html>");
		     out.println("<body style='background: #fff8db;'><fieldset style='text-align: center; top: 200px;position:absolute; right:35%; background-color:#b0e0e6;'>");
		     out.println("<table border='1'>");
		     out.println("<tr>");
		     out.println("<td>Titulo</td>");
		     out.println("<td>Descrição</td>");
		     out.println("<td>Data</td>");
		     out.println("<td>Hora</td>");
		     out.println("<td>Duração</td>");
		     out.println("</tr>");

		     for (int i=0; i< a.size(); i++)
		     {
			 	 out.println("<tr>");
			 	 out.println("<td>" + a.get(i).getTituloEPu() + "</td>");
			 	 out.println("<td> " + a.get(i).getDescricaoEPu() + "</td>");
			 	 out.println("<td>" + a.get(i).getDataEPu() + "</td>");
			 	 out.println("<td> " + a.get(i).getHoraEPu() + "</td>");
			 	 out.println("<td>" + a.get(i).getDuracaoEPu() + "</td>");
			 	 out.println("</tr>");	
		     }
		     out.println("</table></fieldset>");	
		     out.println("</body>");
		     out.println("</html>");

		} else {
			//response.sendRedirect("Erro.html");
			//out.println("<html><head></head><body>erro</body></html>");
		}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
