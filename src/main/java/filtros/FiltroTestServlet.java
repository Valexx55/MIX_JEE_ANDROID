package filtros;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class FiltroTest
 */
@WebFilter("/TestServlet")
public class FiltroTestServlet implements Filter {

	private final static Logger log = Logger.getLogger("mylog");
	
	
	@SuppressWarnings("unchecked")
	private boolean peticionCorrecta (HttpServletRequest http_request)
	{
		boolean correcta = true;
		
		HttpSession session = null;
		List<Integer> lista_sintomas_preguntados = null;
		String respuesta = null;
		int npregunta = -5;
		
			session = http_request.getSession(false);
			respuesta = http_request.getParameter("resp");
			npregunta = Integer.parseInt(http_request.getParameter("npreg"));
		
			if (session != null)
			{
				lista_sintomas_preguntados = (List<Integer>) session.getAttribute("lista_sintomas_preguntados");
				if (respuesta == null || respuesta == ""||lista_sintomas_preguntados == null || lista_sintomas_preguntados.contains(npregunta))
				{
					correcta = false;
				}
			} 
			else 
				{ 
					correcta = false;
				}

		return correcta;
	}
	
	@SuppressWarnings("unchecked")
	private void updateListaSintomasPreguntados (HttpServletRequest http_request)
	{
		HttpSession session = null;
		List<Integer> lista_sintomas_preguntados = null;
		Integer npregunta = Integer.parseInt(http_request.getParameter("npreg"));
			
			session = http_request.getSession(false);

			lista_sintomas_preguntados = (List<Integer>) session.getAttribute("lista_sintomas_preguntados");
			lista_sintomas_preguntados.add(npregunta);
			log.debug("Lista de sintomas preguntados actualizada");

	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filtro) throws IOException, ServletException {

		HttpServletRequest http_request = null;
		HttpServletResponse http_response = null;
		
			http_request = (HttpServletRequest) req;

			if (peticionCorrecta(http_request))
			{
				log.debug("Petición formalmente correctca");
				updateListaSintomasPreguntados(http_request);
				
				log.info("A TestServlet");
				filtro.doFilter(req, resp);
				
			} else 
				{
					log.info("Petición incorrecta. Se le redirige al inicio del test");
					http_response = (HttpServletResponse)resp;
					http_response.sendRedirect("/InitTest");
				}
		
	}
	
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	/*
	 * DEMO EXPERIMENTAL QUE USA LOS FILTROS PARA ACUMULAR EL TIMEPO DE SERVICIO EN EL CONTEXTO
	 */
	/*
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		long tiempoInicial = System.currentTimeMillis();
		
		chain.doFilter(request, response);

        long tiempoFinal = System.currentTimeMillis();
        
        long tiempo = tiempoFinal - tiempoInicial;
        
        long tiempoTotal = (Long)(request.getServletContext().getAttribute("tiempoTotal"));
        
        request.getServletContext().setAttribute("tiempoTotal", tiempoTotal+tiempo);
        log.debug("Ha tardado en esta petición "+tiempo);
        log.debug("El tiempo total es "+tiempoTotal);
	}*/
	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
