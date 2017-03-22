package servlet.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;





/**
 * Servlet implementation class ObtenerListaAleatoria
 */
@WebServlet("/ObtenerListaAleatoria")
public class ObtenerListaAleatoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private List<String> lista_palabras; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerListaAleatoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		//Type tipoLista_palabras = new TypeToken<List<String>>(){}.getType();
		String jsonPalabras = gson.toJson(lista_palabras);
		//setear el tipo
		response.setContentType("application/json");
		response.getWriter().write(jsonPalabras);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
		super.init();
		lista_palabras = new ArrayList<String>();
		lista_palabras.add("jaguar");
		lista_palabras.add("badminton");
		lista_palabras.add("snowboard");
		lista_palabras.add("Rafa Nadal");
		lista_palabras.add("papaya");
		lista_palabras.add("Sierra de Gredos");
		lista_palabras.add("La naranja mecanica");
		lista_palabras.add("cereza");
		lista_palabras.add("Descifrando enigma");
		lista_palabras.add("Scarface");
		
	}
}
