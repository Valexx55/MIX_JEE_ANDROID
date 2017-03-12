package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class FiltroInitTest
 */
@WebFilter("/InitTest")
public class FiltroInitTest implements Filter {
	
	private final static Logger log = Logger.getLogger("mylog");

    /**
     * Default constructor. 
     */
    public FiltroInitTest() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 *SE COMPRUEBA QUE EL USUARIO NO TENGA UNA SESIÓN
	 *EN CASO DE TENERLA, SE LE INVALIDA
	 *
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest http_servlet_request = null;
		HttpSession http_session = null;
		
			http_servlet_request = (HttpServletRequest)request;
			http_session = http_servlet_request.getSession(false);
			if (http_session != null)
			{
				log.info("El usuario contaba con una sesión preexistente. Se procede a invalidarla");
				log.info("SESSION_ID " + http_session.getId());
				
				http_session.invalidate();
				log.info("Sesión invalidada");
				
			}
		
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
