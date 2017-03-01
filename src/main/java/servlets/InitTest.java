package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		SintomasService ss = new SintomasService();
		MapaPatologiasCandidatas mapa_patolog_candidatas = new MapaPatologiasCandidatas();
		int sintoma_actual = 0;
		
		List<SintomasDTO> lista_sdto = ss.listarSintomasOrdenados();
		
		HttpSession session = request.getSession(false);
		if (session != null) 
		{
			session.invalidate();
		} else 
		{
			session = request.getSession(true);
		}
		
		
		session.setAttribute("lista_sint", lista_sdto);
		session.setAttribute("mapa_patologias", mapa_patolog_candidatas);
		session.setAttribute("num_sintoma_actual", sintoma_actual);
		
		request.setAttribute("pregunta", lista_sdto.get(sintoma_actual).getPregunta_web());
		request.getRequestDispatcher(".//html//test.jsp").forward(request, response);		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
