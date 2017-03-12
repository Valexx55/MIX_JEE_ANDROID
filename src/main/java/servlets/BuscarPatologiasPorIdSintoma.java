package servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dto.PatologiaDTO;
import servicios.PatologiaService;

/**
 * Servlet implementation class BuscarPatologiasPorSintoma
 */
@WebServlet("/BuscarPatologiasPorIdSintoma")
public class BuscarPatologiasPorIdSintoma extends HttpServlet {
	
	private PatologiaService patologia_service;
	
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger("mylog");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarPatologiasPorIdSintoma() {
        super();
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * s
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String valorSintoma = null;
		int id_sintoma = -1;
		List<PatologiaDTO> lista_patologias = null;
		Gson gson = null;
		Type tipoListaSintomas = new TypeToken<List<PatologiaDTO>>(){}.getType();
		String json_patologias = null;	
		
			log.debug("Doget BuscarPatologiasPorIdSintoma invoado");
			
			//OBTENGO PARÁMETROS Y CONSUTO
			valorSintoma = request.getParameter("sintomaIntroducido");
			id_sintoma = Integer.parseInt(valorSintoma);
			lista_patologias = patologia_service.obtenerPatologiasPorSintoma(id_sintoma);
		
			//SERIZALIZO A JSON
			gson = new Gson();
			json_patologias = gson.toJson(lista_patologias, tipoListaSintomas);

			//ENVIO RESPUESTA
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_patologias);
	
	}

	@Override
	public void init() throws ServletException {
		super.init();
		this.patologia_service = new PatologiaService();
		
		log.debug("Init BuscarPatologiaPorIdSintoma invocado (patologia service instanciado)");
	}
}
