package Interface;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.EventosPrivados;
import Negocio.Facade;
import Negocio.Usuario;

@WebServlet(urlPatterns={"/ListarEventosPorData", "/liepd" }, name="ListarEventosPorData")

public class ListarEventosPorData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListarEventosPorData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head></head><body style='background: #fff8db;'>");
		out.println("<fieldset> <br> Busque eventos por intervalo de datas.");
		out.println("<form action='liepd' method='post'>");
		out.println("Digite a primeira data: <input style='width:22px;' type='text' name='dia' maxlength='2'> / <input style='width:22px;' type='text' name='mes' maxlength='2'> / " +
				"<input style='width:44px;' type='text' name='ano' maxlength='4'> dd/mm/aaaa <br><br>");
		out.println("Digite a segunda data: <input style='width:22px;' type='text' name='dia1' maxlength='2'> / <input style='width:22px;' type='text' name='mes1' maxlength='2'> / " +
				"<input style='width:44px;' type='text' name='ano1' maxlength='4'> dd/mm/aaaa <br><br>");
		out.println("<input type='submit' value='Buscar Usuario'></form>");
		out.println("</fieldset>");
		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		ServletOutputStream out = response.getOutputStream();
		
		String dia = request.getParameter("dia");
		String mes = request.getParameter("mes");
		String ano = request.getParameter("ano");
		String dia1 = request.getParameter("dia1");
		String mes1 = request.getParameter("mes1");
		String ano1 = request.getParameter("ano1");

		Date d;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
		c.set(Calendar.MONTH, Integer.parseInt(mes)-1);
		c.set(Calendar.YEAR, Integer.parseInt(ano));
		d = c.getTime();
		
		Date dd;
		Calendar cc = Calendar.getInstance();
		cc.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia1));
		cc.set(Calendar.MONTH, Integer.parseInt(mes1)-1);
		cc.set(Calendar.YEAR, Integer.parseInt(ano1));
		dd = cc.getTime();
		Usuario usuario  = (Usuario) request.getSession(true).getAttribute("usuario");//recupera a o objeto de sessao
		String cpf = usuario.getCpf();
		ArrayList<EventosPrivados> a = Facade.getInstancia().listarEventosPrivadosPorData(d, dd, cpf); //recupera lista de usuarios nascidos entre as datas
		 out.println("<html>");
	     out.println("<body style='background: #fff8db;'><fieldset style='text-align: center; top: 200px;position:absolute; right:0%; background-color:#b0e0e6;'>");
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
	     out.println("</body>");
	     out.println("</html>");

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		}


}
