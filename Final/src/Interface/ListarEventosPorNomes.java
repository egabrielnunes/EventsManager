package Interface;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Negocio.EventosPrivados;
import Negocio.EventosPublicos;
import Negocio.Facade;
import Negocio.Usuario;

@WebServlet(urlPatterns={"/ListarEventosPorNomes", "/liepn" }, name="ListarEventosPorNomes")

public class ListarEventosPorNomes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListarEventosPorNomes() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head></head><body style='background: #fff8db;'>");
		out.println("<fieldset>");
		out.println("<form action='liepn' method='post'>");
		out.println("Digite o titulo do evento que deseja buscar: <input type='text' name='titulo' >");
		out.println("<input type='submit' value='Buscar Evento'></form>");
		out.println("</fieldset>");
		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		ServletOutputStream out = response.getOutputStream();
		
		String titulo = request.getParameter("titulo");

		Usuario usuario  = (Usuario) request.getSession(true).getAttribute("usuario");//recupera a o objeto de sessao
		String cpf = usuario.getCpf();
	
		ArrayList<EventosPrivados> a = Facade.getInstancia().listarEventosPrivadosPorNome(titulo, cpf);//recupera lista de usuarios nascidos entre as datas
		ArrayList<EventosPublicos> b = Facade.getInstancia().listarEventosPublicosPorNome(titulo, cpf);//recupera lista de usuarios nascidos entre as datas

		 out.println("<html>");
	     out.println("<body style='background: #fff8db;'><fieldset style='text-align: center; top: 200px;position:absolute; right:20%; background-color:#b0e0e6;'>");
	     out.println("Eventos Privados <br><table border='1'>");
	     out.println("<tr>");
	     out.println("<td>Id</td>");
	     out.println("<td>Titulo</td>");
	     out.println("<td>Data</td>");
	     out.println("<td>Tipo</td>");
	     out.println("</tr>");
	     for (int i=0; i< a.size(); i++)
	     {
	    	 if (a.get(i).getTituloEPri() != null) {
	    		 out.println("<tr>");
	    		 out.println("<td>" + a.get(i).getId() + "</td>"); //link para perfil do usuario
	    		 out.println("<td> " + a.get(i).getTituloEPri() + "</td>" );
	    		 SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
	    		 String data01 = sdf.format(a.get(i).getDataEPri());
	    		 out.println("<td> " + data01 + "</td>" );
	    		 out.println("<td> Privado </td>" );
	    		 out.println("</tr>");	
	    	 } else {
	    		 out.println("<tr>");
	    		 out.println("<td></td>" );
	    		 out.println("<td></td>" );
	    		 out.println("<td></td>" );
	    		 out.println("<td></td>" );
				 out.println("</tr>");	
	    	 }
	     }
	     out.println("</table></fieldset>");
	     out.println("<fieldset style='text-align: center; top: 200px;position:absolute; right:80%; background-color:#b0e0e6;'>Eventos Publicos <br><table border='1'>");
	     out.println("<tr>");
	     out.println("<td>Id</td>");
	     out.println("<td>Titulo</td>");
	     out.println("<td>Data</td>");
	     out.println("<td>Tipo</td>");
	     out.println("</tr>");
	     for (int i=0; i< b.size(); i++)
	     {
	    	 if (b.get(i).getTituloEPu() != null) {
	    		 out.println("<tr>");
	    		 out.println("<td>" + b.get(i).getId() + "</td>"); //link para perfil do usuario
	    		 out.println("<td> " + b.get(i).getTituloEPu() + "</td>" );
	    		 out.println("<td> " + b.get(i).getDataEPu() + "</td>" );
	    		 out.println("<td> Publico </td>");
	    		 out.println("</tr>");	
	    	 } else {
	    		 out.println("<tr>");
	    		 out.println("<td>Não</td>" );
	    		 out.println("<td>Possui</td>" );
	    		 out.println("<td>Nenhum</td>" );
	    		 out.println("<td>Evento</td>" );
				 out.println("</tr>");	
	    	 }
	     }
	     out.println("</table></fieldset>");	
	     out.println("</body>");
	     out.println("</html>");

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		}


}
