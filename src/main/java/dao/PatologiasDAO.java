package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.GenericDTO;

public class PatologiasDAO extends GenericDAO{

	@Override
	public GenericDTO componerObjeto(ResultSet rs) throws SQLException {

		return null;
	}

	public GenericDTO seleccionarPatologiaPorNombre(){
		
		return null;
	}
	
	public List<GenericDTO> seleccionarTodasPatologias(){
		
		return null;
	}
	
	public List<GenericDTO> seleccionarPatologiasPorSintoma(){
		
		return null;
	}
}