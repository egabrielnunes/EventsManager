package Interface;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.MaskFormatter;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import Negocio.Facade;
import Negocio.Usuario;

@WebServlet(urlPatterns={"/AtualizarUsuario", "/au" }, name="AtualizarUsuario")

public class AtualizarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 1024 * 1024 * 1024;
	private int maxMemSize = 4*1024;
	private File file;

       
    public AtualizarUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//formulario
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head></head><body style='background: #fff8db;'>");
		out.println("<fieldset>");
		out.println("<form enctype='multipart/form-data' action='au' method='post'>");
		out.println("<fieldset>");
		out.println("Atualize os seus dados: <br><br>");
		out.println("</fieldset>");
		out.println("Login: <input type='text' name='login'> Deve iniciar com uma consoante e ter pelo menos 5 caracteres.<br><br>");
		out.println("Senha: <input type='password' name='senha'> Deve " +
				"ter pelo menos 6 caracteres.<br><br>");
		out.println("Nome completo: <input type='text' name='nome'><br><br>");
		out.println("Endere�o: <input type='text' name='end'><br><br>");
		out.println("CPF: <input type='text' name='cpf' maxlength='11'> Deve ser �nico e conter 11 digitos<br><br>");
		out.println("Email: <input type='email' name='email'> usuario@dominio.com<br><br>");
		out.println("Data de Nascimento: <input style='width:22px;' type='text' name='dia' maxlength='2'> / <input style='width:22px;' type='text' name='mes' maxlength='2'> / " +
				"<input style='width:44px;' type='text' name='ano' maxlength='4'> dd/mm/aaaa <br><br>");
		out.println("Foto: <input type='file' name='foto'><br><br>");
		out.println("<input type='submit' value='Atualizar'><br>");
		out.println("</fieldset>");
		out.println("</body></html>");
		
	}
	
	private static String format(String pattern, Object value) { //formata o cpf
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			isMultipart = ServletFileUpload.isMultipartContent(request);
			response.setContentType("text/html");
			ServletOutputStream out = response.getOutputStream();

			String login = null;
			String senha = null;
			String nome = null;
			String end = null;
			String cpf = null;
			String emailF = null;
			String dia = null;
			String mes = null;
			String ano = null;
			String cpfF = null;
			String bla = null;
		
			Usuario us = new Usuario();
			Usuario usuario  = (Usuario) request.getSession(true).getAttribute("usuario");
			String cpfU = usuario.getCpf();


			if (isMultipart) {
				try {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					// define o tamanho máximo do arquivo que será armazenado em
					// memória
					factory.setSizeThreshold(maxMemSize);
					// Define a localização temporária do arquivo. Em geral a
					// localização será na pasta temporaria do sistema
					factory.setRepository(new File("C:\\Users\\Gabriel\\workspace\\Final\\Fotos\\")); //alterar caminho

					// Cria um novo manipulador do arquivo de upload
					ServletFileUpload upload = new ServletFileUpload(factory);
					// Define o tamanho máximo do arquivo
					upload.setSizeMax(maxFileSize);

					Map<String, List<FileItem>> itens = upload.parseParameterMap(request);
			
					nome = itens.get("nome").get(0).getString();
					end = itens.get("end").get(0).getString();
					cpf = itens.get("cpf").get(0).getString();
					login = itens.get("login").get(0).getString();
					senha = itens.get("senha").get(0).getString();
					emailF = itens.get("email").get(0).getString();
					dia = itens.get("dia").get(0).getString();
					mes = itens.get("mes").get(0).getString();
					ano = itens.get("ano").get(0).getString();
					
					cpfF = format("###.###.###-##", cpf);
					bla = login.substring(0, 1);
					
					Date dd;
					Calendar d = Calendar.getInstance();
					d.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
					d.set(Calendar.MONTH, Integer.parseInt(mes)-1);
					d.set(Calendar.YEAR, Integer.parseInt(ano));
					dd = d.getTime();
					
					us.setEmail(emailF);
					us.setNome(nome);
					us.setCpf(cpfF);
					us.setEnd(end);
					us.setLogin(login);
					us.setSenha(senha);
					us.setData(dd);
					
					String Destino = null;
					
					if (itens.containsKey("foto")) {
						
						FileItem item = itens.get("foto").get(0);
						
						//String nomeArquivo = item.getName();
						
						File arquivo = new File("C:\\Users\\Gabriel\\workspace\\Final\\Fotos\\");   //alterar caminho 
						File[] file1 = arquivo.listFiles();   
		       
						//Confere se os arquivos foram listados    
		            
						int l = file1.length;      
						
						for(int i = 0; i < l; ++i){	
							Destino = "Foto" + (i+1) + ".jpg";
						}
						
						file = new File(filePath + Destino);
						item.write(file);
						
						us.setFoto(Destino);
					
						} 
				} catch (Exception ex) {
					out.println("<html><head><meta http-equiv='refresh' content= '3; url=au'></head><body style='background: #fff8db;'>" +
							"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
							"<b>Por favor, preencha todos os campos para continuar.</b></div></body></html>");
				}
			} else {
				out.println("<html><head><meta http-equiv='refresh' content= '3; url=au'></head><body style='background: #fff8db;'>" +
						"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
						"<b>Por favor, preencha todos os campos para continuar.</b></div></body></html>");
			}
					if(dia.equals("01") || dia.equals("02") || dia.equals("03")|| dia.equals("04")|| dia.equals("05")|| dia.equals("06")|| 
							dia.equals("07")|| dia.equals("08")|| dia.equals("09")|| dia.equals("10")|| dia.equals("11")|| dia.equals("12")|| 
							dia.equals("13")|| dia.equals("14")|| dia.equals("15")|| dia.equals("16")|| dia.equals("17")|| dia.equals("18")|| 
							dia.equals("19")|| dia.equals("20")|| dia.equals("21")|| dia.equals("22")|| dia.equals("23")|| dia.equals("24")|| 
							dia.equals("25")|| 	dia.equals("26")|| dia.equals("27")|| dia.equals("28")|| dia.equals("29")|| dia.equals("30")|| 
							dia.equals("31") || mes.equals("01") || mes.equals("02")|| mes.equals("03")|| mes.equals("04")|| mes.equals("05")|| 
							mes.equals("06")|| mes.equals("07")|| mes.equals("08")|| mes.equals("09")|| mes.equals("10")|| mes.equals("11")|| 
							mes.equals("12") ){
						if(nome != "" || end != "" || end != "" || cpf != "" || login != "" || senha != "" 
								 || emailF != "" || dia != "" || mes != "" || ano != "" /*|| foto != ""*/){
							if(senha.length() >= 6 && login.length() >= 5 && cpf.length() == 11) {
								if(bla.equals("a") || bla.equals("A") || bla.equals("e") || bla.equals("E") || bla.equals("i") 
									|| bla.equals("I") || bla.equals("o") || bla.equals("O") || bla.equals("u") || bla.equals("U")|| bla.equals("0")
									|| bla.equals("1")|| bla.equals("2")|| bla.equals("3")|| bla.equals("4")|| bla.equals("5")
									|| bla.equals("6")|| bla.equals("7")|| bla.equals("8")|| bla.equals("9")){
									out.println("<html><head><meta http-equiv='refresh' content= '3; url=au'></head><body style='background: #fff8db;'>" +
											"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
											"<b>Por favor, digite um login valido para continuar.</b></div></body></html>");
								}else {
									if(Facade.getInstancia().atualizarUsuario(us, cpfU) == true) { //atualiza o usuario no banco
										out.println("<html><head><meta http-equiv='refresh' content= '3; url=Layout'></head><body style='background: #fff8db;'>" +
												"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
												"<b>Atualiza��o Efetuada Com Sucesso!</b></div></body></html>");
									}else{
										out.println("<html><head><meta http-equiv='refresh' content= '3; url=au'></head><body style='background: #fff8db;'>" +
												"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
												"<b>Ocorreu uma falha ao salvar o usuario.</b></div></body></html>");
									}
								}
							}
						} else {
							out.println("<html><head><meta http-equiv='refresh' content= '3; url=au'></head><body style='background: #fff8db;'>" +
									"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
									"<b>Por favor, preencha todos os campos para continuar.</b></div></body></html>");
						}
					} else {
						out.println("<html><head><meta http-equiv='refresh' content= '3; url=au'></head><body style='background: #fff8db;'>" +
								"<div style='text-align: center; font-size:15px; top: 50px;position:absolute; right:40%; '> " +
								"<b>Digite uma data valida.</b></div></body></html>");
					}
		}
}
