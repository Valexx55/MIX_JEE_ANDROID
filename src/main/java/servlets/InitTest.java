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

import dto.MapaPatologiasCandidatas;
import dto.Pregunta;
import dto.SintomasDTO;
import servicios.SintomasService;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try 
		{
			
			log.debug("Entrando en INIT_TEST");
			
			SintomasService ss = new SintomasService();
			MapaPatologiasCandidatas mapa_patolog_candidatas = new MapaPatologiasCandidatas();
			int sintoma_actual = 0;
			List<SintomasDTO> lista_sdto = ss.listarSintomasOrdenados();
			
			List<Integer> lista_sintomas_preguntados = new ArrayList<Integer>();
			
			HttpSession session = request.getSession(false);
			if (session != null) 
			{
				session.invalidate();
				log.debug("Sesión preexistente. Se anula");
			}
			
			
			
			session = request.getSession(true);//creo una sesión nueva, siempre
			log.debug("Sesión CREADA con id " + session.getId());
			
			session.setAttribute("lista_sint", lista_sdto);
			log.debug("Lista sintomas " + lista_sdto.toString());
			session.setAttribute("mapa_patologias", mapa_patolog_candidatas);
			log.debug("Mapa patologias candidatas " + mapa_patolog_candidatas.toString());
			session.setAttribute("num_sintoma_actual", sintoma_actual);
			log.debug("Sintoma / pregunta actual " + sintoma_actual + " " + lista_sdto.get(sintoma_actual).getPregunta_web() );
			request.setAttribute("pregunta", lista_sdto.get(sintoma_actual).getPregunta_web()); //esto debería estar en el contexto
			
			request.setAttribute("npregunta", sintoma_actual); //esto debería estar en el contexto
			
			
			session.setAttribute("lista_sintomas_preguntados", lista_sintomas_preguntados);
			request.getRequestDispatcher(".//html//test.jsp").forward(request, response);		
		
		} catch (Throwable t)
		{
			log.error("ERRORAZO", t);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
