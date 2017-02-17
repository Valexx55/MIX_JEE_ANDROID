package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.PatologiasDTO;
import servicios.PatologiaService;

/**
 * Servlet implementation class ObtenerPatologiaPorId
 */
@WebServlet("/BuscarPatologia")
public class BuscarPatologia extends HttpServlet {
	private PatologiaService service_patologia;
	private PatologiasDTO patologia;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarPatologia() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		
		String valorid = request.getParameter("id");
		int id = Integer.valueOf(valorid);

		patologia = service_patologia.obtenerPatologiaPorID(id);
		
		request.setAttribute("patologia", patologia);
		
		request.getRequestDispatcher(".//html//mostrarpatologia.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}
	
	@Override
	public void init() throws ServletException {
		service_patologia = new PatologiaService();
		patologia = new PatologiasDTO();
		super.init();
	}

}
