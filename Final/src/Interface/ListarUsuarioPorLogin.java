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

import Negocio.Facade;
import Negocio.Usuario;

@WebServlet(urlPatterns={"/ListarUsuarioPorLogin", "/lupl"}, name="ListarUsuarioPorLogin")

public class ListarUsuarioPorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListarUsuarioPorLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //lista de usuarios por login
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head></head><body style='background: #fff8db;'>");
		out.println("<fieldset>");
		out.println("<form action='lupl' method='post'>");
		out.println("Digite o login do usuario para efetuar a busca: <input type='text' name='login'><br><br>");
		out.println("<input type='submit' value='Buscar Usuario'></form>");
		out.println("</fieldset>");
		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//recupera login e gera tabela com usuario
		try {
			String login = request.getParameter("login");
			ServletOutputStream out = response.getOutputStream();
			ArrayList<Usuario> a = Facade.getInstancia().listarUsuarioPorLogin(login);
			 out.println("<html>");
		     out.println("<body style='background: #fff8db;'><fieldset style='text-align: center; top: 200px;position:absolute; right:42%; background-color:#b0e0e6;'>");
		     out.println("<table border='1'>");
		     out.println("<tr>");
		     out.println("<td>Cpf</td>");
		     out.println("<td>Nome</td>");
		     out.println("<td>Data</td>");
		     out.println("<td>Login</td>");
		     out.println("</tr>");
		     for (int i=0; i< a.size(); i++)
		     {
		    	 if (a.get(i).getNome() != null) {
		    		 out.println("<tr>");
		    		 out.println("<td><a href='pu?cpf=" + a.get(i).getCpf() +"'>" + a.get(i).getCpf() + "</a></td>");//link para perfil do usuario
		    		 out.println("<td> " + a.get(i).getNome() + "</td>" );
		    		 SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
		    		 String data01 = sdf.format(a.get(i).getData());
		    		 out.println("<td> " + data01 + "</td>" );
		    		 out.println("<td> " + a.get(i).getLogin() + "</td>" );
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


