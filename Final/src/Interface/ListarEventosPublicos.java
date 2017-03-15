package Interface;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/ListarEventosPublicos", "/lepu" }, name="ListarEventosPublicos")
public class ListarEventosPublicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListarEventosPublicos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head></head><body style='background: #fff8db;'>");
		out.println("<fieldset style='text-align: center;'>");
		out.println("<form action='lmepu' method='get'>");
		out.println("<input type='submit' value='Listar meus eventos públicos'></form>");
		out.println("<form action='lec' method='get'>");
		out.println("<input type='submit' value='Listar eventos que fui convidado'></form>");
		out.println("<form action='lecp' method='get'>");
		out.println("<input type='submit' value='Listar eventos eu confirmei presença'></form>");
		out.println("</fieldset>");
		out.println("</body></html>");
	}
}
