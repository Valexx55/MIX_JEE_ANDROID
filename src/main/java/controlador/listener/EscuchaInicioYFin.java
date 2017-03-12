package controlador.listener;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;
import dao.SintomasDAO;
import dto.ListadoSintomas;
import dao.PatologiasDAO;
import dto.MapaPatologias;
import dto.PatologiaDTO;
import dto.SintomaDTO;

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
    	log.info ("PROGRAMA FINALIZADO");
    }

    /**
	 * @param arg0 
	 * 
	 * Al iniciar la aplicación, precargamos desde la base de datos a memoria los datos maestros 
	 * para agilizar el rendimiento. Estos son todas las patologías (en un MAPA), los síntomas, y los 
	 * síntomas ordenados
	 *  
	 */
    
    /*
	 * NOTA ACLARATORIA: SE SETEAN LOS DATOS MAESTROS EN OBJETOS ESTÁTICOS, PARA FACILITAR SU ACCESSO
	 * DESDE TODAS LAS CLASES DE LA APLIACIÓN, A LO LARGO DEL CICLO DE VIDA
	 * 
	 * AUNQUE EL CONTEXTO SERÍA EL ÁMBITO IDONEO PARA ALMACENAR ESTAS VARIABLES, SE PREFIERE HACERLO
	 * EN CLASES "NORMALES", PARA QUE SE PUEDA ACCEDER A ELLAS SIN NECESIDAD DE ESTAR EN UN SERVLET
	 * 
	 */
    
    public void contextInitialized(ServletContextEvent arg0)  
    { 
    	
    	PatologiasDAO patologiaDAO = null;
    	Map<Integer, PatologiaDTO> mapa_patologias = null;
    	List<SintomaDTO> lista_sintomas = null;
    	List<SintomaDTO> lista_sintomas_ordenados = null;

    	log.info ("PROGRAMA INICIADO");
    	try
    	
    	{
    		log.debug ("Precargando patologías y síntomas ..");
    		
			patologiaDAO = new PatologiasDAO();
			
			mapa_patologias = patologiaDAO.obtenerListaPalogias();
			lista_sintomas = SintomasDAO.obtenerTodosSintomas();
			lista_sintomas_ordenados = SintomasDAO.getSintomasOrdenados(); 
			
			MapaPatologias.setMapapatologia(mapa_patologias);
			ListadoSintomas.setListado_sintomas(lista_sintomas);
			ListadoSintomas.setListado_sintomas_ordenado(lista_sintomas_ordenados);
			
			log.debug ("Patologías y Síntomas precargados");
			
		} 
    	catch (Throwable e) 
    	{
			log.error ("Ha ocurrido un error inesperado", e);
		}
    }
	
}