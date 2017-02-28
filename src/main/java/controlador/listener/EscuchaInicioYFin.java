package controlador.listener;


import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

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
    public void contextInitialized(ServletContextEvent arg0) 
    { 
    	log.error ("PROGRAMA INICIADO");
    	
    	try
    	{
    		log.error ("La conexion SSH queda iniciada");
			
			PatologiasDAO patologiaDAO = new PatologiasDAO();
			Map <Integer, PatologiasDTO> mapa_patDto = patologiaDAO.obtenerListaPalogias();
				
				MapaPatologias mapaPatologias = new MapaPatologias();
				mapaPatologias.setMapapatologia (mapa_patDto);
				
				log.error ("Mapa Inicializado");
		} 
    		catch (Throwable e)
    		{
				log.error ("Ha ocurrido un error inesperado", e);
			}
    }
}