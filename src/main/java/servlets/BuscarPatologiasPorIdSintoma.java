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

import dto.PatologiasDTO;
import servicios.PatologiaService;

/**
 * Servlet implementation class BuscarPatologiasPorSintoma
 */
@WebServlet("/BuscarPatologiasPorIdSintoma")
public class BuscarPatologiasPorIdSintoma extends HttpServlet {
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
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
		String valorSintoma = request.getParameter("sintomaIntroducido");
		int id_sintoma = Integer.parseInt(valorSintoma);
		
		PatologiaService service = new PatologiaService();
		List<PatologiasDTO> lista_patologias = null;
		
		lista_patologias = service.obtenerPatologiasPorSintoma(id_sintoma);
		
		Gson gson = new Gson();
		Type tipoListaSintomas = new TypeToken<List<PatologiasDTO>>(){}.getType();
		String s = gson.toJson(lista_patologias, tipoListaSintomas);
		
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(s);
		} catch (Exception e)
		{
			log.error("ERROR 32" , e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
