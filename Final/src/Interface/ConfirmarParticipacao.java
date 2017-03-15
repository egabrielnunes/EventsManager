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

@WebServlet({ "/ConfirmarParticipacao", "/cp" })
public class ConfirmarParticipacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmarParticipacao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //exibir tela de confirmação via login e senha
		ServletOutputStream out = response.getOutputStream();
		String email = request.getParameter("email");
		String idEvento = request.getParameter("evento");
	
		out.println("<html><head><title>Confirmação de Participação</title></head><body style='background: #fff8db;'>");
		out.println("<fieldset style='text-align: center'>");		
		out.println("<h3>Faça o Login, para confirmar a participação no evento.</h3>");
		out.println("<form border='solid' action='cp?email="+email+"&evento="+idEvento+"' method='post' align='center'>");
		out.println("Login: <input type='text' name='login'><br><br>");
		out.println("Senha: <input type='password' name='senha'><br><br>");
		out.println("<input type='submit' value='Logar'><br>");
		out.println("</fieldset>");
		out.println("</form></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //recupera parametros e faz verificações, e por fim insere o confirmado a tabela
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		ServletOutputStream out = response.getOutputStream();
		String bla = login.substring(0, 1);
		
			if(bla.equals("a") || bla.equals("A") || bla.equals("e") || bla.equals("E") || bla.equals("i") 
					|| bla.equals("I") || bla.equals("o") || bla.equals("O") || bla.equals("u") || bla.equals("U")){
				out.println("<html><head><meta http-equiv='refresh' content= '3; url=cp'></head><body style='background: #fff8db;'>" +
						"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
						"<b>Login incoerente com o desejado.</b></div></body></html>");

			}else {
				if(login != "" && senha != "") {
					if(senha.length() >= 6 && login.length() >= 5) {
						try {
							if(Facade.getInstancia().autorizaUsuario(login, senha) == true){
									
								Usuario a = Facade.getInstancia().objetoUsuario(login, senha);
								String email = request.getParameter("email");
								String idEvento = request.getParameter("evento");
								String cpf = a.getCpf();
							//	if(a.getEmail() == email) {
								if(email != null || idEvento != null) {
									if(Facade.getInstancia().cadastrarConfirmado(cpf,email,idEvento) == true) {
										out.println("<html><head></head><body style='background: #fff8db;'>" +
											"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
											"<b>Participação confirmada.</b></div></body></html>");
									} else {
										out.println("<html><head><meta http-equiv='refresh' content= '3; url=cp'></head><body style='background: #fff8db;'>" +
												"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:15%; '> " +
												"<b>Falha ao realiazar o login.</b></div></body></html>");
									}
//								}else {
//									out.println("<html><head><meta http-equiv='refresh' content= '3; url=cp'></head><body style='background: #fff8db;'>" +
//											"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:15%; '> " +
//											"<b>Confirme a presença no evento, através do link no seu próprio email.</b></div></body></html>");
//								}
								} else {
									out.println("<html><head><meta http-equiv='refresh' content= '3; url=cp'></head><body style='background: #fff8db;'>" +
											"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:15%; '> " +
											"<b>Você tem que estar cadastrado em um evento, para poder participar dele.</b></div></body></html>");
								}
							} else {
								out.println("<html><head><meta http-equiv='refresh' content= '3; url=cp'></head><body style='background: #fff8db;'>" +
										"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
										"<b>Login ou senha inexistentes.</b></div></body></html>");
									}

							} catch (Exception e) {
								// TODO: handle exception
							}
					} else {
						out.println("<html><head><meta http-equiv='refresh' content= '3; url=cp'></head><body style='background: #fff8db;'>" +
								"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
								"<b>Quantidade de digitos invalida.</b></div></body></html>");
				}
				} else {
					out.println("<html><head><meta http-equiv='refresh' content= '3; url=cp'></head><body style='background: #fff8db;'>" +
							"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
							"<b>Preencha os campos para continuar.</b></div></body></html>");
				}
			}
		}
}
