package servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import dto.ListadoSintomas;
import dto.MapaPatologias;
import dto.MapaPatologiasCandidatas;
import dto.PatologiaDTO;
import dto.SintomaDTO;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger("mylog");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private void informarResultado (PatologiaDTO patologia_ganadora, HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException
	{
		sesion.invalidate();
		
		if (patologia_ganadora != null)
		{
			log.debug("FIN: PATOLOGIA ENCONTRADA");

			request.setAttribute("patologia", patologia_ganadora);
			request.getRequestDispatcher(".//html//resultadotest.jsp").forward(request, response);
		} else {
			
			log.debug("FIN: SIN PATOLOGIAS CANDIDATAS");
			request.getRequestDispatcher(".//html//resultadotestnegativo.jsp").forward(request, response);
		}
		
	}

	private Map<Integer, PatologiaDTO> filtrar_patologias (String respuesta, MapaPatologiasCandidatas mapa_patolog_candidatas, int num_sintoma_actual)
	{
		Map<Integer, PatologiaDTO> mapa_resultado = null;
		List<SintomaDTO> lista_sintomas_ordenados = ListadoSintomas.getListado_sintomas_ordenado();
		
		if (respuesta.equals("SI")) 
		{
			//si dijo sí, me quedo con las patologías que sí tienen ese síntoma, descartando el resto
			mapa_resultado = mapa_patolog_candidatas.filtrarPatologiasCandidatas(mapa_patolog_candidatas.getMapa_patologias_candidatas(), lista_sintomas_ordenados.get(num_sintoma_actual));
		} 
		else 
		{
			//si dijo No, me quedo con las que NO, para lo cual me quedo con las que sí y hago la difrencia con el conjunto inicial (todas)
			Map<Integer, PatologiaDTO> mapa_patolog_filtradas = mapa_patolog_candidatas.filtrarPatologiasCandidatas(mapa_patolog_candidatas.getMapa_patologias_candidatas(),lista_sintomas_ordenados.get(num_sintoma_actual));
			mapa_resultado = MapaPatologias.diferencia(MapaPatologias.getMapapatologia(), mapa_patolog_filtradas);
		}
		
		
		return mapa_resultado;
	}
	
	
	private int obtenerSiguienteSintoma (int num_sintoma, Map<Integer, PatologiaDTO> mapa_patolog_resultado)
	{
		int n_sintoma_dev = -5;
		boolean sintoma_seleccionado = false;
		int nsintomas = ListadoSintomas.getListado_sintomas_ordenado().size();
		
		num_sintoma = num_sintoma + 1;
		while ((!sintoma_seleccionado) && (num_sintoma < nsintomas))
		{
			SintomaDTO sintomaactual = ListadoSintomas.getListado_sintomas_ordenado().get(num_sintoma);
			boolean sintoma_presente = false;
			PatologiaDTO paux = null;
			Iterator<Integer> itm = mapa_patolog_resultado.keySet().iterator();

			while (!sintoma_presente && itm.hasNext()) 
			{
				paux = mapa_patolog_resultado.get(itm.next());
				sintoma_presente = MapaPatologiasCandidatas.sintomaEnPatologia(sintomaactual, paux);
			}

			if (sintoma_presente) 
				{
					log.debug("Sintoma presente en  " + paux.toString());
					sintoma_seleccionado = true;
				} else {
					log.debug("Sintoma no presente en ninguna patol candidata " + sintomaactual.toString());
					log.debug("Buscando el siguiente ");
					num_sintoma = num_sintoma + 1;
				}
		} 
		

		n_sintoma_dev = (num_sintoma == nsintomas) ? -1: num_sintoma; 
		
		return n_sintoma_dev;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {

			log.debug("ENTRNADO EN TEST_SERVLET");

			HttpSession session = request.getSession(false);
			
			String respuesta = request.getParameter("resp");
			MapaPatologiasCandidatas mapa_patolog_candidatas = (MapaPatologiasCandidatas) session.getAttribute("mapa_patologias_candidatas");
			int num_sintoma_actual = Integer.parseInt(request.getParameter("npreg"));
			Map<Integer, PatologiaDTO> mapa_patolog_resultado = null;
			
			mapa_patolog_resultado =  filtrar_patologias(respuesta, mapa_patolog_candidatas, num_sintoma_actual);

			int npatols =  (mapa_patolog_resultado==null) ? 0 : mapa_patolog_resultado.size();
				
			switch (npatols) 
			{
				case 0:
				
					informarResultado(null, request, response, session);
				break;

				case 1:
					
					Iterator<Integer> it = mapa_patolog_resultado.keySet().iterator();
					PatologiaDTO patologia_ganadora = mapa_patolog_resultado.get(it.next());

					informarResultado(patologia_ganadora, request, response, session);

					break;

				default: // CASO POR DEFECTO --> quedan > 1 Patologia Candidata

					log.debug("Queda más de una patol candidadata, buscando siguiente síntoma/pregunta");

					num_sintoma_actual = obtenerSiguienteSintoma(num_sintoma_actual, mapa_patolog_resultado);
					
					if (num_sintoma_actual == -1) //se completó la lista de sintomas sin pregunta candidata 
					{
						informarResultado(null, request, response, session);
					} 
					else 
					{
						plantearSiguientePregunta (mapa_patolog_resultado, num_sintoma_actual, request, response, session);
					}

					break;
				}
	}

	private void plantearSiguientePregunta (Map<Integer, PatologiaDTO> mapa_resultado, int num_sintoma_actual, HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException
	{
		MapaPatologiasCandidatas mapa_nuevo = new MapaPatologiasCandidatas(mapa_resultado);
		sesion.setAttribute("mapa_patologias", mapa_nuevo);
		request.setAttribute("npregunta", num_sintoma_actual); 
		request.setAttribute("pregunta", ListadoSintomas.getListado_sintomas_ordenado().get(num_sintoma_actual).getPregunta_web());
		request.getRequestDispatcher(".//html//test.jsp").forward(request, response);
	}
	


}
