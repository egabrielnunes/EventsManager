package Interface;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.EnviarMail;
import Negocio.EventosPublicos;
import Negocio.Facade;
import Negocio.Usuario;

@WebServlet(urlPatterns = { "/CriarEventoPublico", "/cepu" }, name = "CriarEventoPublico")
public class CriarEventoPublico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CriarEventoPublico() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //mostra tela de criacao de evento publico
		ServletOutputStream out = response.getOutputStream();
		out.println("<?php");
		out.println("if(isset($_REQUEST['enviarDados'])){");
		out.println("	$inputsCount = $_POST['inputsCount'];");
		out.println("	for($x = 1; $x <= $inputsCount; $x++){"); //não consegui de outra forma.
		out.println("		echo $_POST['formInput'.$x];");
		out.println("		echo '<br/>';");
		out.println("	}}?>");
		out.println("<html><head><title>Criação de Evento Público</title>");
		out.println("<script type='text/javascript'>");
		out.println("var id = 1;");
		out.println("function novoCampo(){"); //gera campos dinamicamente
		out.println("var inp = document.createElement('input');");
		out.println("inp.setAttribute('id', id );");
		out.println("inp.type= 'email';");
		out.println("inp.setAttribute('name', 'formInput'+id );");
		out.println("var lab = document.createElement('label');");
		out.println("lab.setAttribute('id', 'l'+id);");
    	out.println("var lab_content = document.createTextNode('Convidado ' + id + ' ');");
	    out.println("var quebra = document.createElement('br');");
		out.println("lab.appendChild(lab_content);");
		out.println("var inpP = document.getElementById(id-1);");
		out.println("var parentInp = inpP.parentNode;");
		out.println("parentInp.insertBefore(lab, inpP.nextSibling);");
		out.println("parentInp.insertBefore(inp, lab.nextSibling);");
		out.println("parentInp.insertBefore(quebra, lab);");
		out.println("document.getElementById('0').value = id;");
		out.println("++id;");
		out.println("}");
		out.println("</script>");

		out.println("</head><body style='background: #fff8db;'>");
		out.println("<fieldset>");
		out.println("<form action='cepu' method='post' action='?enviarDados'>");
		out.println("<fieldset>");
		out.println("Crie seu Evento Público: <br><br>");
		out.println("</fieldset>");
		out.println("Título: <input type='text' name='titulo'><br><br>");
		out.println("Descrição: <input type='text' name='descricao' style='width:500px;'><br><br>");
		out.println("Hora de inicio do evento: <input type='text' name='hora' style='width:22px;' maxlength='2'> horas <input type='text' name='minutos' style='width:22px;' maxlength='2'> minutos<br><br>");
		out.println("Data do Evento: <input style='width:22px;' type='text' name='dia'  maxlength='2'> / <input style='width:22px;' type='text' name='mes' maxlength='2'> / <input style='width:44px;' type='text' name='ano' maxlength='4'> dd/mm/aaaa <br><br>");
		out.println("Duração: <input type='text' name='duracaohora'  maxlength='2' style='width:22px;'> horas <input type='text'  maxlength='2' name='duracaominutos' style='width:22px;'> minutos<br>");
		out.println("<input type='hidden' name='inputsCount' id='0' value='0'>");
		out.println("<br/>");
		out.println("<input type='button' value='Adicionar Convidado' onClick='novoCampo();'>");
		out.println("<br/>");
		out.println("<br/>");
		out.println("<input type='submit' value='Criar Evento'><br>");
		out.println("</fieldset>");
		out.println("</form></body></html>");

	}

	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException { //recupera os paremetros e faz verificacoes para a criacao do evento
		ServletOutputStream out = response.getOutputStream();
		Usuario usuario  = (Usuario) request.getSession(true).getAttribute("usuario");
		
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String hora = request.getParameter("hora");
		String minutos = request.getParameter("minutos");
		String dia = request.getParameter("dia");
		String mes = request.getParameter("mes");
		String ano = request.getParameter("ano");
		String duracaoH = request.getParameter("duracaohora");
		String duracaoM = request.getParameter("duracaominutos");
		String cpfF = usuario.getCpf();
		String data = (dia + "/" + mes + "/" + ano);
		String horaF = (hora + ":" + minutos);
		String duracao = (duracaoH + ":" + duracaoM);

		EventosPublicos epu = new EventosPublicos();

		epu.setDescricaoEPu(descricao);
		epu.setTituloEPu(titulo.toUpperCase());
		epu.setDataEPu(data);
		epu.setHoraEPu(horaF);
		epu.setDuracaoEPu(duracao);
		
		try {
			if (Facade.getInstancia().cadastrarEventoPu(epu, cpfF) == true) { //cria evento
				out.println("<html><head><meta http-equiv='refresh' content= '3; url=Layout'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Cadastro de Evento Publico, efetuado com sucesso.</b></div></body></html>");
			} else {
				out.println("<html><head><meta http-equiv='refresh' content= '3; url=cepu'></head><body style='background: #fff8db;'>" +
						"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
						"<b>Falha ao cadastrar o Evento Publico.</b></div></body></html>");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String aaa = request.getParameter("inputsCount"); //quantidade de emails criados dinamicamente
		int a = Integer.parseInt(aaa);
		int c =1;
		String[] emails = new String[a];
		
		for (int i = 0; i < a; i++) {
			try {
				emails[i] = request.getParameter("formInput" + c);
				String valor = emails[i];
				String idPublico = Facade.getInstancia().recuperaId(titulo); //recupera id do evento recem criado
				int id = Integer.parseInt(idPublico);
				String loginU = "drncprojeto@gmail.com";
				String passU = "projeto2014";
				String sub = "Convite Especial";
				String sub1 = "Você foi convidado para o evento: "+ titulo + "<br>" +
				"Que irá ocorrer no dia " + data + "às " + horaF + "<br>" +
				"Para confirmar a participação no evento <a href='http://localhost:8080/Final/cp?email="+valor+"&evento="+idPublico+"'>" +
						"Clique aqui.</a>";

				new EnviarMail(loginU, passU, valor, sub, sub1).start(); //envia email para convidado
				ArrayList<Usuario> convidados = Facade.getInstancia().listarUsuarios();
	
				//for (int j = 0; j < convidados.size(); j++) {
					for (Usuario u : convidados) {
						try {
							String cpf = u.getCpf();
							String email = valor;
				
							Facade.getInstancia().cadastrarConvidado(cpf, email, id); //cadastra na tabela de convidados
						} catch (Exception e) {
							out.println("<html><head><meta http-equiv='refresh' content= '3; url=cepu'></head><body style='background: #fff8db;'>" +
									"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
									"<b>Falha ao inserir o convidado do Evento Publico.</b></div></body></html>");
						}
					}
				//}
				c++;
			} catch (Exception e) {
			}
		}

	}
}
