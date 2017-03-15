package Interface;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Negocio.Facade;

@WebServlet({ "/PerfilUsuario", "/pu" })
public class PerfilUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PerfilUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {//gera o perfil do usuario
		try {
		ServletOutputStream out = response.getOutputStream();
		String cpf = request.getParameter("cpf");
		
		String foto = Facade.getInstancia().fotoUsuario(cpf);//recupera foto
		String perfil = Facade.getInstancia().perfilUsuario(cpf);//recupera perfil
	
		
		out.println("<html><head></head><body style='background: #fff8db;>");
		out.println("<div style='left: 20%'><img  style='position:relative' align='center' height='200' width='200' src='C:\\Users\\Gabriel\\workspace\\Final\\Fotos\\"+ foto + "'></div>");
		out.println(""+perfil+"</body></html>");
		} catch (IOException e) {
		}
		
	}

}
