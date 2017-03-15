package Interface;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.Facade;
import Negocio.Usuario;

@WebServlet(urlPatterns={"/ListarUsuarios", "/lu" }, name="ListarUsuarios")
public class ListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListarUsuarios() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //lista todos os usuarios no banco
		try {
			
		ServletOutputStream out = response.getOutputStream();
		ArrayList<Usuario> a = Facade.getInstancia().listarUsuarios();
		 out.println("<html>");
	     out.println("<body style='background: #fff8db;'><fieldset style='text-align: center; top: 200px;position:absolute; right:42%; background-color:#b0e0e6;'>");
	     out.println("<table border='1'>");
	     out.println("<tr>");
	     out.println("<td>Nome</td>");
	     out.println("<td>E-mail</td>");
	     out.println("</tr>");
	     for (int i=0; i< a.size(); i++)
	     {
	    	 if (a.get(i).getNome() != null) {
			 	 out.println("<tr>");
			 	 out.println("<td><a href='pu?cpf=" + a.get(i).getCpf() +"'>" + a.get(i).getNome() + "</a></td>"); //cria link para ir para o perfil do usuario
			 	 out.println("<td> " + a.get(i).getEmail() + "</td>" );
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
