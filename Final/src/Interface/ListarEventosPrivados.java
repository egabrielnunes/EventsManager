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
import Negocio.Facade;
import Negocio.Usuario;

@WebServlet(urlPatterns={"/ListarEventosPrivados", "/lepr"}, name="ListarEventosPrivados")

public class ListarEventosPrivados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListarEventosPrivados() {
        super();
    }

	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //recupera lista com os eventos privados que foi criado pelo proprio usuario
		
		ServletOutputStream out = response.getOutputStream();
		Usuario usuario  = (Usuario) request.getSession(true).getAttribute("usuario");
		String cpfF = usuario.getCpf();
		try {
			
		if(cpfF != ""){
		 ArrayList<EventosPrivados> a = Facade.getInstancia().listarEventosPrivados(cpfF); //recupera lista
		 out.println("<html>");
	     out.println("<body style='background: #fff8db;'><fieldset style='text-align: center; top: 200px;position:absolute; right:25%; background-color:#b0e0e6;'>");
	     out.println("<table border='1'>");
	     out.println("<tr>");
	     out.println("<td>Titulo</td>");
	     out.println("<td>Descrição</td>");
	     out.println("<td>Data</td>");
	     out.println("<td>Hora</td>");
	     out.println("<td>Duração</td>");
	     out.println("<td>Data de Agendamento</td>");
	     out.println("<td>Hora de Agendamento</td>");
	     out.println("</tr>");

	     for (int i=0; i< a.size(); i++)
	     {
		 	 out.println("<tr>");
		 	 out.println("<td>" + a.get(i).getTituloEPri() + "</td>");
		 	 out.println("<td> " + a.get(i).getDescricaoEPri() + "</td>");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
			String data = sdf.format(a.get(i).getDataEPri());
			int dia = a.get(i).getDataEPri().getDate();

		 	 out.println("<td>" + dia + "/"+data + "</td>");
		 	 out.println("<td> " + a.get(i).getHoraEPri() + "</td>");
		 	 out.println("<td>" + a.get(i).getDuracaoEPri() + "</td>");
		 	 if(a.get(i).getDataAgendamento() != null || a.get(i).getHoraAgendamento() != null){
				
				String data01 = sdf.format(a.get(i).getDataAgendamento());
				int dia1 = a.get(i).getDataAgendamento().getDate();

		 		 out.println("<td> " + dia1 + "/"+data01 + "</td>");
		 		 out.println("<td> " + a.get(i).getHoraAgendamento() + "</td>");
		 	 }
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

