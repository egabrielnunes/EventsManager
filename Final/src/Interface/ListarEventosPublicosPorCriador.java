package Interface;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/ListarEventosPublicosPorCriador", "/leppc" }, name="ListarEventosPublicosPorCriador")
public class ListarEventosPublicosPorCriador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListarEventosPublicosPorCriador() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		out.println("falta fazer.");
		//sabia fazer tambem, porem nao deu tempo
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
