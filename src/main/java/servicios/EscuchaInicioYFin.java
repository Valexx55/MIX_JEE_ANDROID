package servicios;


import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.PatologiasDAO;
import dao.conexionssh;
import dto.MapaPatologias;
import dto.PatologiasDTO;

/**
 * Application Lifecycle Listener implementation class EscuchaInicioYFin
 *
 */
@WebListener
public class EscuchaInicioYFin implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public EscuchaInicioYFin() {
        // TODO Auto-generated constructor stub
    }
    
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("PROGRAMA DESTRUIDO");
    	System.out.println("PROGRAMA DESTRUIDO");
    	System.out.println("PROGRAMA DESTRUIDO");
    	System.out.println("PROGRAMA DESTRUIDO");
    	
    	conexionssh.desconectate_D_SSH ();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("PROGRAMA INICIADO");
    	System.out.println("PROGRAMA INICIADO");
    	System.out.println("PROGRAMA INICIADO");
    	System.out.println("PROGRAMA INICIADO");
    	try {
			conexionssh.conectate_A_SSH ();
			System.out.println("La conexi SSH queda iniciada");
			
			PatologiasDAO patologiaDAO = new PatologiasDAO();
			Map<Integer, PatologiasDTO> mapa_patDto = patologiaDAO.obtenerListaPalogias();
				
				MapaPatologias mapaPatologias = new MapaPatologias();
				mapaPatologias.setMapapatologia(mapa_patDto);
				
				System.out.println("Mapa Inicializado");
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
}
