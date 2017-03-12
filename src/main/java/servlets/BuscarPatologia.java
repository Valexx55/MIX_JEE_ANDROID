package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dto.PatologiaDTO;
import servicios.PatologiaService;


/** Servlet implementation class ObtenerPatologiaPorId  */

	@WebServlet("/BuscarPatologia")
public class BuscarPatologia extends HttpServlet
{
	private PatologiaService service_patologia;
	private PatologiaDTO patologia;
	
	private final static Logger log = Logger.getLogger("mylog");
	private static final long serialVersionUID = 1L;
       
	/** @see HttpServlet#HttpServlet()   */
    public BuscarPatologia()
    {
        super();
    }

	/**  @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String valorid = null;
		int id = 0;
		
			log.debug("DoGet BuscarPatología invocado");
			
			valorid = request.getParameter("id");
			id = Integer.valueOf(valorid);
			
			log.debug("Id patología recibido " + id);
			
			patologia = service_patologia.obtenerPatologiaPorID(id);//PM ROBUSTEZ TODO Se debería crear una excepción en caso de que fuera null el valor obtenido (id palogoia no existiera)
			
			request.setAttribute("patologia", patologia);
			request.getRequestDispatcher(".//html//mostrarpatologia.jsp").forward(request, response);
	}

	
	@Override
	public void init() throws ServletException
	{
		super.init();
		service_patologia = new PatologiaService();
		
		log.debug("metodo init ejecutado. Patología Serivces creado");
	}
}
