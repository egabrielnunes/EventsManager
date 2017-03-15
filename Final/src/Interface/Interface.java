package Interface;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/Interface", "/0"})
public class Interface extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Interface() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//pagina inicial aplicacao
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head><title>Bem Vindo ao SkyDiaries</title></head><body style='background: #fff8db;'>");
		out.println("<div style='text-align: center; font-family:Times New Roman; font-size:40px; top: 50px;position:absolute; right:43%; background-color:#b0e0e6;'>  SkyDiaries  </div>");
		out.println("<fieldset style='text-align: center; top: 200px;position:absolute; right:42%; background-color:#b0e0e6;'>");
		out.println("<form action='Login' method='get'>");
		out.println("<input type='submit' value='Realizar Login'></form>");
		out.println("<form action='cu' method='get'>");
		out.println("<input type='submit' value='Criar Novo Usuário'></form>");
		out.println("</fieldset>");
		out.println("</body></html>");
		
	}
	
	
}

