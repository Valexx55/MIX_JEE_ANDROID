package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import consulta.Consultas;
import dto.SintomasDTO;

public class SintomasDAO  {

	public static SintomasDTO componerObjeto (ResultSet rs) throws Exception
	{
		SintomasDTO sintomaDTO = null;
		
			int id_sint = rs.getInt("id_sint");
			String desc_sint = rs.getString("des_sint");
			sintomaDTO = new SintomasDTO(id_sint, desc_sint);
		
		return sintomaDTO;
	}

}