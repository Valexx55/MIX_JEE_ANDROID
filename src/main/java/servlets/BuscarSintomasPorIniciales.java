package servlets;

import java.util.List;
import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dto.SintomaDTO;
import servicios.SintomasService;


/**
 * Servlet implementation class BuscarSintomas
 */
@WebServlet("/BuscarSintomasPorIniciales")
public class BuscarSintomasPorIniciales extends HttpServlet {
	
	private SintomasService sintomas_service;
	private final static Logger log = Logger.getLogger("mylog");
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarSintomasPorIniciales() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String valorSintoma = null;
		List<SintomaDTO> lista_sintomas = null;
		Gson gson = null;
		Type tipoListaSintomas = new TypeToken<List<SintomaDTO>>(){}.getType();
		String json_sintomas = null;
		
			log.debug("Entra en BuscarSintomasPorIniciales");
			valorSintoma = request.getParameter("sintomaBuscado");
	
			log.debug("Valor sintoma " + valorSintoma);
						
			lista_sintomas =  sintomas_service.buscarSintomaPorInicial(valorSintoma);
			log.debug("Lista síntomas recuperada tamanio " + lista_sintomas.size());
			
			gson = new Gson();
			json_sintomas = gson.toJson(lista_sintomas, tipoListaSintomas);
			
			log.debug("Lista sintomas JSON" + json_sintomas);
			
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_sintomas);
		
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		sintomas_service = new SintomasService();
		
		log.debug("Sintoma Services Creado - INIT Buscar por iniciales done!");
	}

}
