package Negocio;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class MyStartupUtil implements ServletContextListener {

    public MyStartupUtil() {
    }

    public void contextInitialized(ServletContextEvent arg0) {

    	//nao deu tempo :(
    	//incompleto :(
    	//eu sabia fazer, aqui iria chamar um metodo que iria ler as datas em evento privado
    	//essa data seria verificada se já passou ou nao e iria em colocar em memoria a que estivesse
    	//mais proxima de acontecer a data de envio, seria carregado e setado uma variavel Calendar e iria
    	//somente chamar o metodo reminder.
    	//iria tb verificar um campo criado em envento privado que 0 é pq ele nao foi enviado e 1 era pra se ele ja
    	//estivesse sido enviado, aqui teria uma chamada para um metodo que iria ler se foi ou nao enviado, os que 
    	//ainda nao tivessem sido enviados, iriam para o primeiro metodo citado acima.
    }

	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
	
}
