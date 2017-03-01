package servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import servicios.SintomasService;
import dto.MapaPatologias;
import dto.MapaPatologiasCandidatas;
import dto.PatologiasDTO;
import dto.SintomasDTO;

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

    
    private void mostrar (Map<Integer, PatologiasDTO> mapa)
    {
    	Iterator<Integer> it = mapa.keySet().iterator();
    	PatologiasDTO paux = null;
    	while (it.hasNext())
    	{
    		paux = mapa.get(it.next());
    		log.debug(paux.toString());
    	}
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try
		{
			
		
			log.debug("ENTRNADO EN TEST_SERVLET");
			
			HttpSession session = request.getSession(false);
			if (session == null)
			{
				//caso especial, el usuario a heehco uan petición sin pasar por init test
				log.debug("Cliente sin sesión válida");
				response.sendRedirect("/InitTest");
				
			} 
			
			String respuesta = request.getParameter("resp");
			if (respuesta == null || respuesta == "")
			{
				log.debug("Petición incorrecta (sin respuesta)");
				response.sendRedirect("/InitTest");	
			}
			
			
			List<SintomasDTO> lista_sdto = (List<SintomasDTO>) session.getAttribute("lista_sint");
			MapaPatologiasCandidatas mapa_patolog_candidatas = (MapaPatologiasCandidatas) session.getAttribute("mapa_patologias");
			int num_sintoma_actual = (Integer) session.getAttribute("num_sintoma_actual");
			
			Map<Integer, PatologiasDTO> mapa_patolog_resultado = null;
			//TODO SI NO HAY RESPUESTA, REDIRIGIR A INICIO O MANDAR A PÁGINA DE ERROR
			
			if(respuesta.equals("SI"))
			{
				mapa_patolog_resultado = mapa_patolog_candidatas.filtrarPatologiasCandidatas(mapa_patolog_candidatas.getMapa_patologias_candidatas(), lista_sdto.get(num_sintoma_actual));
			}
			else //dijo que no
			{
				Map<Integer, PatologiasDTO> mapa_patolog_filtradas = mapa_patolog_candidatas.filtrarPatologiasCandidatas(mapa_patolog_candidatas.getMapa_patologias_candidatas(), lista_sdto.get(num_sintoma_actual));
				mapa_patolog_resultado = MapaPatologias.diferencia(MapaPatologias.getMapapatologia(), mapa_patolog_filtradas);
			}
			
			log.debug("MOSTRANDO PATOLOGIAS CANDIDATAS");
			mostrar(mapa_patolog_resultado);
			int npatols = mapa_patolog_resultado.size();
			
			
			log.debug(mapa_patolog_resultado.size());
			
			switch (npatols) {
			case 0: 
				log.debug("Sin patologias candidatas");
				session.invalidate();
				log.debug("Sesión invalidada");
				//MENSAJE ERROR--> NO SABEMOS QU� TE PASA
				request.setAttribute("pregunta", "PREGUNTE A SU M�DICO");
				request.getRequestDispatcher(".//html//test.jsp").forward(request, response);	
				break;
	
				
			case 1:
				
				log.debug("Patolgia ENCONTRADA");
				session.invalidate();
				log.debug("Sesion invalidada");
				
				Iterator<Integer> it = mapa_patolog_resultado.keySet().iterator();
				PatologiasDTO patologia_ganadora = mapa_patolog_resultado.get (it.next()); 
				
				log.debug("Patolgia resultado 1" + patologia_ganadora.toString());
				
				request.setAttribute("patologia", patologia_ganadora); //lista_sdto.get(num_sintoma_actual).getPregunta_web());
				request.getRequestDispatcher(".//html//resultadotest.jsp").forward(request, response);	
				
				
				break;
				
			default: //CASO POR DEFECTO --> + DE 1 PATOLOG�A CANDIDATA
				
				log.debug("Queda más de una patol candidadata, buscando siguiente síntoma/pregunta");
				
				boolean sintoma_seleccionado = false;
				int nsintomas = lista_sdto.size();
				
				while (!sintoma_seleccionado && (num_sintoma_actual < nsintomas-1));
				{
					log.debug("Buscando entre " + nsintomas + " sintomas");
					
					num_sintoma_actual = num_sintoma_actual + 1;
					
					log.debug("Sintoma actual " + num_sintoma_actual);
					SintomasDTO sintomaactual = lista_sdto.get(num_sintoma_actual);
					
					log.debug("Sintoma actual " + sintomaactual.toString());
					
					boolean sintoma_presente = false;
					PatologiasDTO paux = null;
					Iterator<Integer> itm = mapa_patolog_resultado.keySet().iterator();
					
					
					while (!sintoma_presente && itm.hasNext())
					{
						paux = mapa_patolog_resultado.get(itm.next());
						sintoma_presente = mapa_patolog_candidatas.sintomaEnPatologia(sintomaactual, paux);
					}
					
					if (sintoma_presente)
					{
						log.debug("Sintoma presente en  " + paux.toString());
						sintoma_seleccionado = true;
					} else
					{
						log.debug("Sintoma no presente en ninguna patol candidata " + sintomaactual.toString());
						log.debug("Buscando el siguiente " + sintomaactual.toString());
						num_sintoma_actual = num_sintoma_actual + 1;
					}
					
				} 
				
				if (!sintoma_seleccionado) //ninguno de los s�ntomas, est� presente en la lista de patolog�as candidatas
				{
					//case 0: //MENSAJE ERROR--> NO SABEMOS QU� TE PASA
					log.debug("Llegué al final y no está presente en ninguna patol ABANDONO  ");
					session.invalidate();
					log.debug("Sesión invalidada");
					request.setAttribute("pregunta", "PREGUNTE A SU MEDICO");
					request.getRequestDispatcher(".//html//test.jsp").forward(request, response);	
				} else
				{
					log.debug("Síntoma encontrado  ");
					
					MapaPatologiasCandidatas mapa_nuevo = new MapaPatologiasCandidatas (mapa_patolog_resultado);
					session.setAttribute("mapa_patologias", mapa_nuevo);//actualiza mapa en sesi�n
					session.setAttribute("num_sintoma_actual", num_sintoma_actual);//actualiza n�mero de s�ntoma en sesi�n
					//la lista de s�ntomas no se actualiza porque siempre es la misma.
					request.setAttribute("pregunta", lista_sdto.get(num_sintoma_actual).getPregunta_web());
					request.getRequestDispatcher(".//html//test.jsp").forward(request, response);	
					//Y REDIRIGIR AL JSP CON LA PREGUNTA/SINTOMA SELECCIONADA
				}
				
				break;
			}
		}
		catch (Throwable t)
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
