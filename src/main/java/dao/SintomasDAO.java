package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.SintomasDTO;
import servicios.Consultas;


public class SintomasDAO  {

	public static SintomasDTO componerObjeto (ResultSet rs) throws Exception
	{
		SintomasDTO sintomaDTO = null;
		
			int id_sint = rs.getInt("id_sint");
			String desc_sint = rs.getString("des_sint");
			sintomaDTO = new SintomasDTO(id_sint, desc_sint);
		
		return sintomaDTO;
	}

	
	public static List<SintomasDTO> obtenerTodosSintomas()
	{
		List<SintomasDTO> lista_sint = new ArrayList<SintomasDTO>();
		
		SintomasDTO sintoma_auxiliar = new SintomasDTO();
		Pool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			pool = Pool.getInstance();
			con = pool.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(Consultas.CONSULTA_TODOS_SINTOMAS);
			
				while (rs.next())
				{
					sintoma_auxiliar = componerObjeto(rs);
					lista_sint.add(sintoma_auxiliar);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			pool.liberarRecursos(con, st, rs);
			
		}
		
		return lista_sint;
		
	}
		
	
}