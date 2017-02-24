package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

		/**  Servlet implementation class ListarPatologias  */
	@WebServlet("/ListarPatologias")
public class ListarPatologias extends HttpServlet
{
	private final static Logger log = Logger.getLogger("mylog");
		
	private static final long serialVersionUID = 1L;
       
    	/**  @see HttpServlet#HttpServlet()   */
    public ListarPatologias()
    {
        super();
    }

	/**  @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		log.debug ("doGet Iniciado");
	}

	/**  @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		log.debug ("doPost Iniciado");
		doGet(request,response);
	}
}
