package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import dto.SintomaDTO;
import servicios.Consultas;


public class SintomasDAO  {
	private final static Logger log = Logger.getLogger("mylog");

	public static SintomaDTO componerObjeto (ResultSet rs) throws Exception
	{
		SintomaDTO sintomaDTO = null;
		
			int id_sint = rs.getInt("id_sint");
			String desc_sint = rs.getString("des_sint");
			sintomaDTO = new SintomaDTO(id_sint, desc_sint);
		
		return sintomaDTO;
	}

	
	public static List<SintomaDTO> obtenerTodosSintomas()
	{
		List<SintomaDTO> lista_sint = new ArrayList<SintomaDTO>();
		
		SintomaDTO sintoma_auxiliar = new SintomaDTO();
		Pool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			pool = Pool.getInstance();
			con = pool.getConnection();
			log.error ("Conexion establecida");
			st = con.createStatement();
			rs = st.executeQuery(Consultas.CONSULTA_TODOS_SINTOMAS);
			
				while (rs.next())
				{
					sintoma_auxiliar = componerObjeto(rs);
					lista_sint.add(sintoma_auxiliar);
				}
			
		} catch (Exception e) {
			log.error("FALLO AL OBTENER LOS SINTOMAS: ",e);
			e.printStackTrace();
		} finally {
			
			pool.liberarRecursos(con, st, rs);
			
		}
		
		return lista_sint;
		
	}
	
	public static ArrayList<SintomaDTO> getSintomasOrdenados() {
		
		ArrayList<SintomaDTO> lista = new ArrayList<SintomaDTO>();
		SintomaDTO sint = null;
		
		ResultSet rset = null;
		Pool.getInstance();
		Connection conn=null;
		conn=Pool.getConnection();
		Statement stmt=null;
		
		try
		{
			
		stmt=conn.createStatement();
		rset = stmt.executeQuery("select * from Sintomas ORDER BY prioridad_sint");
		
		while (rset.next())
		{
			
			sint = new SintomaDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4));
			lista.add(sint);
		}
		
		}catch(SQLException e)
		{
				e.printStackTrace();
		}
		finally
		{
			Pool.liberarRecursos(conn, stmt, rset);
		}
		
		return lista;
		
		
		}
		
	
}