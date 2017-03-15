package Interface;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Negocio.Facade;
import Negocio.Usuario;


@WebServlet({"/Login", "/login", "/1"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//formulario de login
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head><title>Login</title></head><body style='background: #fff8db;'>");
		out.println("<fieldset style='text-align: center'>");
		out.println("<form border='solid' action='Login' method='post' align='center'>");
		out.println("Login: <input type='text' name='login'><br><br>");
		out.println("Senha: <input type='password' name='senha'><br><br>");
		out.println("<input type='submit' value='Logar'><br>");
		out.println("</fieldset>");
		out.println("</form></body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//valida o login e da acesso ao sistema, criando uma sessao com o obejeto usuario
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		ServletOutputStream out = response.getOutputStream();
		
		String bla = login.substring(0, 1);
	
		if(bla.equals("a") || bla.equals("A") || bla.equals("e") || bla.equals("E") || bla.equals("i") 
				|| bla.equals("I") || bla.equals("o") || bla.equals("O") || bla.equals("u") || bla.equals("U")){
			out.println("<html><head><meta http-equiv='refresh' content= '3; url=1'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Digite um login válido.</b></div></body></html>");

		}else {
			if(login != "" && senha != "") {
				if(senha.length() >= 6 && login.length() >= 5) {
						try {
							
						if(Facade.getInstancia().autorizaUsuario(login, senha) == true){
							Usuario a = Facade.getInstancia().objetoUsuario(login, senha);
							request.getSession(true).setAttribute("usuario", a);
							response.sendRedirect("Layout");
						} else {
							out.println("<html><head><meta http-equiv='refresh' content= '3; url=1'></head><body style='background: #fff8db;'>" +
									"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
									"<b>Falha na autenticação.</b></div></body></html>");
						}

						} catch (Exception e) {
							// TODO: handle exception
						}
				} else {
					out.println("<html><head><meta http-equiv='refresh' content= '3; url=1'></head><body style='background: #fff8db;'>" +
							"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
							"<b>Digite um login e senha validos.</b></div></body></html>");
				}
			} else {
				out.println("<html><head><meta http-equiv='refresh' content= '3; url=1'></head><body style='background: #fff8db;'>" +
						"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
						"<b>Preencha os campos para fazer o login.</b></div></body></html>");
			}
		}

	}

}
