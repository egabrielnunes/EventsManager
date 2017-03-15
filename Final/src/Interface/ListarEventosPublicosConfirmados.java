package Interface;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Negocio.Confirmados;
import Negocio.Facade;
import Negocio.Usuario;

@WebServlet(urlPatterns={"/ListarEventosPublicosConfirmados", "/lecp" }, name="ListarEventosPublicosConfirmados")
public class ListarEventosPublicosConfirmados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListarEventosPublicosConfirmados() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //recupera lista de confirmados
		
		ServletOutputStream out = response.getOutputStream();
		Usuario usuario  = (Usuario) request.getSession(true).getAttribute("usuario");
		String cpfF = usuario.getCpf();
		try{
		if(cpfF != ""){
		 ArrayList<Confirmados> a = Facade.getInstancia().listarEventosPublicosConfirmados(cpfF);
		 out.println("<html>");
	     out.println("<body style='background: #fff8db;'><fieldset style='text-align: center; top: 200px;position:absolute; right:25%; background-color:#b0e0e6;'>");
	     out.println("<table border='1'>");
	     out.println("<tr>");
	     out.println("<td>Id Evento Confirmados</td>");
	     out.println("<td>Id do Evento Publico</td>");
	     out.println("<td>Email</td>");
	     out.println("<td>Cpf</td>");
	     out.println("</tr>");

	     for (int i=0; i< a.size(); i++)
	     {
		 	 out.println("<tr>");
		 	 out.println("<td>" + a.get(i).getId() + "</td>");
		 	 out.println("<td> " + a.get(i).getIdP() + "</td>");
		 	 out.println("<td> " + a.get(i).getEmailU() + "</td>");
		 	 out.println("<td>" + a.get(i).getCpfU() + "</td>");
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
