package servlet.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.vale.adminbebe.dto.BuenosDias;
import com.google.gson.Gson;


/**
 * Servlet implementation class BuenosDiasBebe
 */
@WebServlet("/BuenosDiasBebeAdmin")
public class BuenosDiasBebeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuenosDiasBebeAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String ulti_bdias_json;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	resp.setStatus(HttpURLConnection.HTTP_OK);//le digo que ok!
    	resp.setContentType("application/json");//y que le devuelvo un mensaje JSON
		PrintWriter pw = resp.getWriter();
		pw.write(ulti_bdias_json);  //y escribo el mensaje  
		
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedReader br = null;
		BuenosDias bd = null;
		
				//leo el cuerpo
				br = request.getReader();
				ulti_bdias_json = br.readLine();
				
				//lo desiarlizo A BuenosDias como mera prueba
				Gson gson = new Gson();
				bd = gson.fromJson(ulti_bdias_json, BuenosDias.class);
				
				System.out.println("RECIBIDO " + bd.toString());
				
				
	}

}
