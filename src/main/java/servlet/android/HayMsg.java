package servlet.android;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class HayMsg
 */
@WebServlet("/HayMsg")
public class HayMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HayMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("id");
		int contador = 0;
		Usuario user = MapaUsuarios.getHm().get(id);
		
		List<Mensaje> lista_recibidos = new ArrayList<Mensaje>();
		
		lista_recibidos = user.getLista_recibidos();
		
		if (lista_recibidos.isEmpty())
		{
			PrintWriter pw = response.getWriter();
			pw.write("No hay mensajes");
		}
		else
		{
			for (Mensaje mensaje : lista_recibidos) 
			{
				if(!mensaje.isLeido())
				{
					mensaje.setLeido(true);
					PrintWriter pw = response.getWriter();
					pw.write(mensaje.getMensaje());
				}
				else 
				{
					contador++;
				}
			}
			
			if(contador!=0)
			{
				PrintWriter pw = response.getWriter();
				pw.write("No hay mensajes nuevos");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);
	}

}
