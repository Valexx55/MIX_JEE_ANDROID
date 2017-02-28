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

import dto.SintomasDTO;
import servicios.SintomasService;


/**
 * Servlet implementation class BuscarSintomas
 */
@WebServlet("/BuscarSintomasPorIniciales")
public class BuscarSintomasPorIniciales extends HttpServlet {
	
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
		log.debug("Entra en BuscarSintomasPorIniciales");
		String valorSintoma = request.getParameter("sintomaBuscado");

		log.debug("Valor sintoma " + valorSintoma);
		SintomasService sintomas_service = new SintomasService();
		
		log.debug("Objeto sintoma servicio creado ");
		List<SintomasDTO> lista_sintomas = null;
		lista_sintomas =  sintomas_service.buscarSintomaPorInicial(valorSintoma);
		log.debug("Lista síntomas creada tamanio " + lista_sintomas.size());
		
		Gson gson = new Gson();
		Type tipoListaSintomas = new TypeToken<List<SintomasDTO>>(){}.getType();
		String s = gson.toJson(lista_sintomas, tipoListaSintomas);
		
		log.debug("Lista sen JSON" + s);
		
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(s);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
