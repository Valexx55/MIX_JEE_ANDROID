package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import dto.GenericDTO;
import herramientas.Pool;

public abstract class  GenericDAO {

	protected Connection conexion;
	protected Statement stmt;
	protected ResultSet rset;
	
	/**
	 * Mediante una consulta de sql devuelve un unico objeto de GenericDTO
	 * @param consulta - Consulta que es introducida cuando se invoca el metodo
	 * @return
	 * @throws SQLException
	 */
	public GenericDTO ejecutarConsultaSimple(String consulta) throws SQLException	{
		
			GenericDTO dtoAux = null;
			String consultaTraducida = null;
			
			try
				{
					conexion = Pool.getConnection();
					stmt = conexion.createStatement();
					stmt.execute(consultaTraducida);
					rset = stmt.getResultSet();
					if (rset != null)
					{
						if (rset.next())
						{
							dtoAux = this.componerObjeto(rset);					
						
						}
					}
			}
			finally
			{
				liberarRecursos(conexion, stmt, rset);
			}
									
			return dtoAux;
	}

	public List<GenericDTO> ejecutarSentencia(Connection conn,String instruccion){
		return null;
	}
	
	/**
	 * Mediante una consulta de sql devuelve una lista de objetos de GenericDTO
	 * @param consulta - Consulta que es introducida cuando se invoca el metodo
	 * @return
	 * @throws SQLException
	 */
	public List<GenericDTO> ejecutarConsultaMultiple(String consulta) throws SQLException{
		List<GenericDTO> ldev = null;
		GenericDTO dtoAux = null;
		
		try
		{
		conexion = Pool.getConnection();
		stmt = conexion.createStatement();
		stmt.execute(consulta);
		rset = stmt.getResultSet();
		
			if (rset.next())
			{
				dtoAux = this.componerObjeto(rset);
				ldev.add(dtoAux);
			}
			
		}catch(Exception e){
			
		}
		finally
		{
			liberarRecursos(conexion, stmt, rset);
		}
		return ldev;
	}

	/**
	 * Metodo que compone el objeto GenericDTO dado un ResulSet
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public abstract GenericDTO componerObjeto (ResultSet rs) throws SQLException;
	 
	/**
	 * Dado un string "sentencia" se cambia una ' de ese string y se sustituye por un String "argumento"
	 * Tendra que contar los "(" para añadir al final la misma cantidad de ")"
	 * @param sentencia
	 * @param argumento
	 * @return
	 * @throws Exception
	 */
	public String sustituirArgumentos (String sentencia, String argumento) throws Exception
	{
		String sentenciatraducida = "";
		
		return sentenciatraducida;
	}

	private static void liberarRecursos (Connection conn,Statement st, ResultSet rs){
		
			if (rs != null) 	{ try { rs.close(); } catch (Exception e2) { e2.printStackTrace(); }}
			if (st != null)	{ try {	st.close(); } catch (Exception e2) { e2.printStackTrace(); }}
			if (conn != null) 	{ try { conn.close(); } catch (Exception e3) { e3.printStackTrace(); }}
		  
	}
	
}
