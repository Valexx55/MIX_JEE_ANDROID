package servlet.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.vale.adminbebe.dto.BuenosDias;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * Servlet implementation class BuenosDiasBebe
 */
@WebServlet("/DeletePublicacionesBDBBAdmin")
public class DeletePublicacionesBDBBAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger("mylog");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePublicacionesBDBBAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String ulti_bdias_json;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	resp.setStatus(HttpURLConnection.HTTP_OK);//le digo que ok!
    	resp.setContentType("application/json");//y que le devuelvo un mensaje JSON
		PrintWriter pw = resp.getWriter();
		pw.write(ulti_bdias_json);  //y escribo el mensaje  
		
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedReader br = null;
		List<String> lista_fechas_borrar = null;
		Type tipoListaFechas = new TypeToken<List<String>>(){}.getType();
		String cuerpo_fechas_borrar = null;
		
				//leo el cuerpo
				br = request.getReader();
				cuerpo_fechas_borrar = br.readLine();
				
				//lo desiarlizo A BuenosDias como mera prueba
				Gson gson = new Gson();
				lista_fechas_borrar = gson.fromJson(cuerpo_fechas_borrar,tipoListaFechas);
				
				log.debug("RECIBIDO " + lista_fechas_borrar.toString());
				try
				{
					Map<String, BuenosDias> mapa_publicaciones = FicheroPublicaciones.obtenerPublicaciones();
					for (String fecha : lista_fechas_borrar)
					{
						mapa_publicaciones.remove(fecha);
					}
					FicheroPublicaciones.guardarPublicaciones(mapa_publicaciones);
					log.debug("BORRADAS LAS PUBLICACIONES");
				}
				catch (Exception e)
				{
					response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
					log.error("ERROR AL ACTUAIZAR LA BD", e);
				}
				
				//FicheroPublicaciones.obtenerPublicaciones();
				
				//System.out.println("RECIBIDO " + bd.toString());
				
				
	}

}
