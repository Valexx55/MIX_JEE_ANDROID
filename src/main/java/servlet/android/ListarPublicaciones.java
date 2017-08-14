package servlet.android;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.vale.adminbebe.dto.BuenosDias;

/**
 * Servlet implementation class ListarPublicaciones
 */
@WebServlet("/ListarPublicaciones")
public class ListarPublicaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger("mylog");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPublicaciones() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			
			Map<String, BuenosDias> mapa = FicheroPublicaciones.obtenerPublicaciones();
			if (null!= mapa)
			{
				Set<String> cjto_claves = mapa.keySet();
				Iterator<String> it = cjto_claves.iterator();
				String clave_aux = null;
				BuenosDias bd_aux = null;
				while (it.hasNext())
				{
					clave_aux = it.next();
					bd_aux = mapa.get(clave_aux);
					log.debug(bd_aux.toString());
				}
				log.debug("TOTAL "+ mapa.size());
			} else {
				log.debug("MAPA VAcío");
				
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("ERROR AL MOSTRAR", e);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
