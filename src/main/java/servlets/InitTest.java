package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dto.ListadoSintomas;
import dto.MapaPatologiasCandidatas;
import dto.SintomaDTO;

/**
 * Servlet implementation class ListarSintomasOrdenados
 */
@WebServlet("/InitTest")
public class InitTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger("mylog");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitTest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		MapaPatologiasCandidatas mapa_patolog_candidatas = null;
		int sintoma_actual = 0;
		List<SintomaDTO> lista_sintomas_ordenados = null;
		List<Integer> lista_sintomas_preguntados = null;
		HttpSession session = null;
		
		
			log.debug("Entrando en INIT_TEST");
			
			mapa_patolog_candidatas = new MapaPatologiasCandidatas();
			lista_sintomas_ordenados = ListadoSintomas.getListado_sintomas_ordenado();
			lista_sintomas_preguntados = new ArrayList<Integer>();
			
			session = request.getSession(true);//creo una sesión nueva, siempre
			log.debug("Sesión CREADA con id " + session.getId());
			
			session.setAttribute("mapa_patologias_candidatas", mapa_patolog_candidatas);
			session.setAttribute("lista_sintomas_preguntados", lista_sintomas_preguntados);
			
			request.setAttribute("pregunta", lista_sintomas_ordenados.get(sintoma_actual).getPregunta_web()); 
			request.setAttribute("npregunta", sintoma_actual); 
			
			request.getRequestDispatcher(".//html//test.jsp").forward(request, response);		
		
	}


}
