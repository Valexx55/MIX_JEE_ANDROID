package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import consultas_instrucciones.Consultas;
import dto.GenericDTO;
import dto.SintomasDTO;

public class SintomasDAO extends GenericDAO {

	
	@Override
	public GenericDTO componerObjeto (ResultSet rs) throws SQLException
	{
		SintomasDTO sintomasDTO = null;
		
		sintomasDTO = new SintomasDTO (rs.getString("NOMBRE_PATOLOGIA"));
		
		return sintomasDTO;
	}
	
	public GenericDTO seleccionarSintomaPorNombre(){
		
		return null;
	}
	
	public List<GenericDTO> seleccionarTodosSintomas() throws SQLException{
		
		List<GenericDTO> lista_sintomas = null;
		
			lista_sintomas = ejecutarConsultaMultiple(Consultas.CONSULTA_LISTAR_SINTOMAS);
		
		return lista_sintomas;
	}
	
	public List<GenericDTO> seleccionarSintomasPorPatologia(){
		
		return null;
	}
}