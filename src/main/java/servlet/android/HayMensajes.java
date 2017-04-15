package servlet.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class TengoMensajes
 */
@WebServlet("/HayMensajes")
public class HayMensajes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger("mylog");
       
	
	public  int generarAleatorioDe3 ()
	{
		int num_aleatorio = 0;
		
			num_aleatorio = ((int)(Math.random()*1000))%3;
			
		return num_aleatorio;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HayMensajes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.debug("ENTRANDO EN DOGET");
		//String id_usuario = request.getParameter("user"); //podría recogerse 
		
		//y recogerse la info asociada a él en una base de datos 
		
		
		int n_aleatorio = generarAleatorioDe3();
		
		log.debug("NUMERO ALEATORIO " + n_aleatorio);
		switch (n_aleatorio) 
			{
				case 0: //NADA
					response.setStatus(HttpURLConnection.HTTP_NO_CONTENT);//le digo que no hay nada :)
					log.debug("DEVOLVIENDO NADA");
					
					break;
				case 1: //TEXTO 
					
					response.setStatus(HttpURLConnection.HTTP_OK);//le digo que ok!
					response.setContentType("text/plain");//y que le devuelvo texto
					PrintWriter pw = response.getWriter();
					pw.write("TU MENSAJITO");
					
					log.debug("DEVOLVIENDO TXT");
					
					break;
				case 2://FOTO
					
					response.setStatus(HttpURLConnection.HTTP_OK);//le digo que ok!
					response.setContentType("image/png");//y que le devuelvo un png
					ServletOutputStream sos = response.getOutputStream();
					
					byte buffer_lectura [] = new byte [1024];
					
					FileInputStream fin = new FileInputStream(new File ("/iconoportada.png"));
					int leido = 0;
					while ((leido = fin.read(buffer_lectura))!=-1)
						{
							sos.write(buffer_lectura, 0, leido);
						}
					
					fin.close();
					
					log.debug("DEVOLVIENDO UN PNG");
			
					break;
			}
		log.debug("FIN SERVLET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
