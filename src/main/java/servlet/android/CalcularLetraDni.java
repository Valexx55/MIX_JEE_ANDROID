package servlet.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;



/**
 * Servlet implementation class CalcularLetraDni
 */
@WebServlet("/CalcularLetraDni")
public class CalcularLetraDni extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger("mylog");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcularLetraDni() {
        super();
        // TODO Auto-generated constructor stub
    }

    private char letraDNI(int dni){
		String caracteresDNI="TRWAGMYFPDXBNJZSQVHLCKE";
		char letra;
		
		int poscionLetra = dni%23;
		
		letra = caracteresDNI.charAt(poscionLetra);
		
		return letra;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("DOGET INVOCADO");
		
		String dni = request.getParameter("dni");
		int i_dni = Integer.parseInt(dni);
		char letra = letraDNI(i_dni);
		
		response.setContentType("text/plain");
		PrintWriter pw = response.getWriter();
		pw.write(letra);
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.debug("DOPOST INVOCADO");
		String linea = request.getReader().readLine();
		log.debug("MENSAJE JSON" + linea);
		response.getWriter().write(linea);
		
	}

}