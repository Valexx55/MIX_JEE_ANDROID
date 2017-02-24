package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import consulta.Consultas;
import dto.SintomasDTO;

public class SintomasDAO
{	
	private final static Logger log = Logger.getLogger("mylog");
	
	public static SintomasDTO componerObjeto (ResultSet rs) throws Exception
	{
		SintomasDTO sintomaDTO = null;
				log.debug("Componiendo objeto sintoma DTO");
			int id_sint = rs.getInt ("id_sint");
			String desc_sint = rs.getString ("des_sint");
			sintomaDTO = new SintomasDTO(id_sint, desc_sint);
		
		return sintomaDTO;
	}
}