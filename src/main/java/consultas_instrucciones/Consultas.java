package consultas_instrucciones;

public class Consultas {

	public static final String CONSULTA_LISTAR_SINTOMAS 
	= "SELECT * FROM Sintomas";
	
	public static final String CONSULTA_LISTAR_PATOLOGIAS 
	= "SELECT * FROM Patologias";
	
	public static final String CONSULTA_PATOLOGIAS_POR_NOMBRE 
	= "Select * From Patologias Where id_patol IN (Select id_patol from Patologias Where nom_patol = '";
	
	public static final String CONSULTA_SINTOMAS_POR_NOMBRE 
	= "SELECT des_sint From Sintomas Where id_sint IN (Select id_sint From Sintomas Where des_sint = '";
	
	public static final String CONSULTA_PATOLOGIAS_POR_SINTOMA 
	= "SELECT * FROM Patologias WHERE id_patol IN (SELECT id_patol FROM Causas WHERE id_sint IN (SELECT id_sint FROM Sintomas WHERE des_sint = '";
	
	public static final String CONSULTA_SINTOMAS_POR_PATOLOGIA 
	= "SELECT des_sint FROM Sintomas WHERE id_sint IN (SELECT id_sint FROM Causas WHERE id_patol IN (SELECT id_patol FROM Patologias WHERE nom_patol = '";
}

