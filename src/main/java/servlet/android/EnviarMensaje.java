package servlet.android;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * Servlet implementation class EnviarMensaje
 */
@WebServlet("/EnviarMensaje")
public class EnviarMensaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger("mylog");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarMensaje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Gson gson = new Gson();
		 ArrayList<Mensaje> listaMen= new ArrayList<Mensaje>();
		
		 
		 
		 
		 String json = request.getReader().readLine();//gson.toJson(new Carta ("ICA", "DJO", "Hola"), Carta.class);
	
		 log.debug("MENSAJE RECIBIDO " + json);
		 Carta cartita = gson.fromJson(json, Carta.class); 
		 
		 Usuario usrRem = MapaUsuarios.getHm().get(cartita.getId_remitente());
		 
		 Usuario usrDest= MapaUsuarios.getHm().get(cartita.getId_destinatario());
		
		 Mensaje msg = new Mensaje(0,cartita.getMensaje() , null,usrRem , usrDest, false);
		 listaMen.add(msg);
		 
		 MapaUsuarios.getHm().get(usrRem.getId()).setLista_enviados(listaMen);
		 MapaUsuarios.getHm().get(usrDest.getId()).setLista_recibidos(listaMen);
		 
		 log.debug("MENSAJE develto " + msg.getMensaje());
		 response.setStatus(HttpURLConnection.HTTP_OK);
		 /*PrintWriter pw = response.getWriter();
		 pw.write("Mensaje enviado " +msg.getMensaje());*/
		 
	}

}
