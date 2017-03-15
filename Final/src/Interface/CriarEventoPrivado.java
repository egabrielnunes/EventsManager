package Interface;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Negocio.EventosPrivados;
import Negocio.Facade;
import Negocio.Usuario;
import Negocio.Reminder;

@WebServlet(urlPatterns={"/CriarEventoPrivado", "/cepr" }, name="CriarEventoPrivado")

public class CriarEventoPrivado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Timer timer;
    static Date time;
    static  Date d = new Date();

    EventosPrivados ep = new EventosPrivados();
	
    public CriarEventoPrivado() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //gera a tela de criacao de evento privado
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head><title>Criação de Evento Privado</title></head><body style='background: #fff8db;'>");
		out.println("<fieldset>");
		out.println("<form action='cepr' method='post'>");
		out.println("<fieldset>");
		out.println("Crie seu Evento Particular: <br><br>");
		out.println("</fieldset>");
		out.println("Título: <input type='text' name='titulo'><br><br>");
		out.println("Descrição: <input type='text' name='descricao' style='width:500px;'><br><br>");
		out.println("Hora de inicio do evento: <input type='text' name='hora' style='width:22px;' maxlength='2'> horas <input type='text' name='minutos' style='width:22px;' maxlength='2'> minutos<br><br>");
		out.println("Data do Evento: <input style='width:22px;' maxlength='2' type='text' name='dia'> / <input style='width:22px;' type='text' name='mes' maxlength='2'> / <input style='width:44px;'  maxlength='4' type='text' name='ano'> dd/mm/aaaa <br><br>");
		out.println("Duração: <input type='text' name='duracaohora' style='width:22px;' maxlength='2'> horas <input type='text' name='duracaominutos' maxlength='2' style='width:22px;'> minutos<br><br>");
		out.println("Data do Agendamento: <input style='width:22px;' type='text' name='diaA' maxlength='2'> / <input style='width:22px;' type='text' name='mesA' maxlength='2'> / <input style='width:44px;' type='text' name='anoA' maxlength='4'> dd/mm/aaaa *<br><br>");
		out.println("Hora do Agendamento: <input type='text'  maxlength='2' name='horaA' style='width:22px;'> horas <input type='text'  maxlength='2' name='minutosA' style='width:22px;'> minutos *<br><br>*opcional<br><br>");
		out.println("<input type='submit' value='Criar Evento'><br>");
		out.println("</fieldset>");
		out.println("</form></body></html>");

	}
	
  @SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //recupera parametros e faz as verificacoes para o cadastro
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
		String diaA = request.getParameter("diaA");
		String mesA = request.getParameter("mesA");
		String anoA = request.getParameter("anoA");		
		String horaA = request.getParameter("horaA");
	    String minutosA = request.getParameter("minutosA");
		String cpfF = usuario.getCpf();
		
		String data = (dia + "/" + mes + "/" + ano); 
		String horaF = (hora + ":" + minutos); 
		String duracao = (duracaoH + ":" + duracaoM); 
		
//		
//		Date diii = new Date();
//		int diaaa = diii.getDay();
//		int messs = diii.getMonth();
//		int anooo = diii.getYear();
//	
		Calendar t = Calendar.getInstance();
		int diaaa = t.get(Calendar.DAY_OF_MONTH);
		int messs = t.get(Calendar.MONTH);
		int anooo =t.get(Calendar.YEAR);
	
		
		if(diaaa > Integer.parseInt(dia) || messs > Integer.parseInt(mes) || anooo > Integer.parseInt(ano)
				|| diaaa > Integer.parseInt(diaA)|| messs > Integer.parseInt(mesA)|| anooo > Integer.parseInt(anoA) ){

		if(diaA != "" && mesA != "" && anoA != "" && minutosA != "" && horaA != "" ){
			try {
			
			String horaAF = (horaA + ":" + minutosA); 
			
			Calendar calendar = Calendar.getInstance();//envio de emial no horario e dia determinado
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaA));
			calendar.set(Calendar.MINUTE, Integer.parseInt(minutosA));
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.DATE, d.getDate());
			time = calendar.getTime();
			
		    System.out.format("Task scheduled.%n");
		    System.out.println(time);
		
		    String emailU = usuario.getEmail();
		    System.out.println(emailU);
			String loginU = "drncprojeto@gmail.com";
			String passU = "projeto2014";
			String sub = "Agendamento";
			String sub1 = "Você possui o evento " + titulo + " marcado para o dia " + data + " às " + horaF + "horas. <br> SkyDiaries.";
			
		    new Reminder(loginU, passU, emailU, sub, sub1, time).start();//envia o email
		    
			Date ddd;
			Calendar d = Calendar.getInstance();
			d.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaA));
			d.set(Calendar.MONTH, Integer.parseInt(mesA)-1);
			d.set(Calendar.YEAR, Integer.parseInt(anoA));
			ddd = d.getTime();
			
			ep.setDataAgendamento(ddd);
			ep.setHoraAgendamento(horaAF);

	
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		if(dia != "" && mes != "" && ano != "" && minutos != "" && hora != ""&& duracaoM != ""&& duracaoH != ""&& descricao != ""&& titulo != "" ){
			
		Date dd;
		Calendar d = Calendar.getInstance();
		d.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
		d.set(Calendar.MONTH, Integer.parseInt(mes)-1);
		d.set(Calendar.YEAR, Integer.parseInt(ano));
		dd = d.getTime();

		ep.setDescricaoEPri(descricao);
		ep.setTituloEPri(titulo.toUpperCase());
		ep.setDataEPri(dd);
		ep.setHoraEPri(horaF);
		ep.setDuracaoEPri(duracao);
		try {
			
		if(Facade.getInstancia().cadastrarEventoPri(ep, cpfF) == true){
			out.println("<html><head><meta http-equiv='refresh' content= '3; url=Layout'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Cadastro de Evento Privado, efetuado com sucesso.</b></div></body></html>");
		} else {
			out.println("<html><head><meta http-equiv='refresh' content= '3; url=cepr'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Falha ao cadastrar o Evento Privado.</b></div></body></html>");
		}
		} catch (Exception e) {
			out.println("<html><head><meta http-equiv='refresh' content= '3; url=cepr'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Falha ao cadastrar o Evento Privado.</b></div></body></html>");
		}	
		} else {
			out.println("<html><head><meta http-equiv='refresh' content= '3; url=cepr'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Preencha todos os campos para efetuar o cadastro.</b></div></body></html>");
		}
		
		} else {
			out.println("<html><head><meta http-equiv='refresh' content= '3; url=cepr'></head><body style='background: #fff8db;'>" +
					"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
					"<b>Data inválida para a criação do Evento Privado.</b></div></body></html>");
		}
		

	}

}
