package servlet.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.vale.adminbebe.dto.BuenosDias;
import com.google.gson.Gson;

/**
 * Servlet implementation class ObtenerMensajeBuenosDias
 */
@WebServlet("/ObtenerMensajeBuenosDias")
public class ObtenerMensajeBuenosDias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger("mylog");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerMensajeBuenosDias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String fecha = null;
		BuenosDias bd = null;
		
				fecha = request.getParameter("fecha");//leo la fecha
				log.debug("Fecha recibida " + fecha);
				
				//buscar el mensaje en el mapa
				try{
					
					Map<String, BuenosDias> mapa_publicaciones = FicheroPublicaciones.obtenerPublicaciones();
					if (mapa_publicaciones == null)
					{
						response.setStatus(HttpURLConnection.HTTP_NO_CONTENT);
						log.debug("No hay un registro para esa fecha (mapa vacío)");
					}
					else {
						bd = mapa_publicaciones.get(fecha);
						
						if (bd != null)
						
						{
							Gson gson = new Gson();
							String mensaje = gson.toJson(bd);
						
							PrintWriter pw = response.getWriter();
							pw.write(mensaje);
						
							response.setStatus(HttpURLConnection.HTTP_OK);
							log.debug("Buenos días enviado " + bd.toString());
						}
						else 
							{
								log.debug("No hay un registro para esa fecha");
								response.setStatus(HttpURLConnection.HTTP_NO_CONTENT);
							}
					}
					
				}catch (Exception e)
				{
					log.error("ERROR AL LEER EL FICHERO PUBICACIONES", e);
					response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
				}
				
				//obtengo, edito/añado y guardo
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
