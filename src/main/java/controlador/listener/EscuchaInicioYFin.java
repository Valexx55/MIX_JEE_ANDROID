package controlador.listener;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import dao.SintomasDAO;
import dto.ListadoSintomas;
import dao.PatologiasDAO;
import dto.MapaPatologias;
import dto.PatologiasDTO;

/**  Application Lifecycle Listener implementation class EscuchaInicioYFin  */
	@WebListener
public class EscuchaInicioYFin implements ServletContextListener
{
	private final static Logger log = Logger.getLogger("mylog");
	
		/** Default constructor */
    public EscuchaInicioYFin()
    {
    	//default
    }
    
		/**  @see ServletContextListener#contextDestroyed(ServletContextEvent) */
    public void contextDestroyed(ServletContextEvent arg0) 
    { 
    	log.error ("PROGRAMA FINALIZADO");
    }

		/**  @see ServletContextListener#contextInitialized(ServletContextEvent) */
    public void contextInitialized(ServletContextEvent arg0)  { 

    	log.error ("PROGRAMA INICIADO");
    	arg0.getServletContext().setAttribute("tiempo", 0);
    	try
    	{
    		log.error ("La conexion SSH queda iniciada");
    		
			PatologiasDAO patologiaDAO = new PatologiasDAO();
			Map<Integer, PatologiasDTO> mapa_patDto = patologiaDAO.obtenerListaPalogias();
			
			SintomasDAO sintomasDAO = new SintomasDAO();
			ArrayList lista_sintomas =  (ArrayList) sintomasDAO.obtenerTodosSintomas();
			
				MapaPatologias mapaPatologias = new MapaPatologias();
				mapaPatologias.setMapapatologia(mapa_patDto);
				
				ListadoSintomas listadoSintomas = new ListadoSintomas();
				listadoSintomas.setMapapatologia(lista_sintomas);
				
				log.error ("Mapa Inicializado");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				log.error ("Ha ocurrido un error inesperado", e);
			}
	    }
	
}