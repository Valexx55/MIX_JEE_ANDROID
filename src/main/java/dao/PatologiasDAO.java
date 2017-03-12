package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import consulta.Consultas;
import dto.*;


public class PatologiasDAO
{
	private final static Logger log = Logger.getLogger("mylog");
	
	public PatologiaDTO componerObjeto(ResultSet rs) throws SQLException
	{		
		PatologiaDTO patologia_dto = null;
		
		int id_patologia = rs.getInt ("id_patol");
		String nombre_patologia = rs.getString ("nom_patol");
		String descripcion_patologia = rs.getString ("des_patol");
		String tratamiento_patologia = rs.getString ("trat_patol");
		String causa_patologia = rs.getString ("causa_patol");
		String ruta_imagen_patologia = rs.getString ("imagen");
		
		patologia_dto = new PatologiaDTO(id_patologia,nombre_patologia,descripcion_patologia,tratamiento_patologia,causa_patologia,ruta_imagen_patologia);
		
		return patologia_dto;
	}
	
/*	public GenericDTO buscarPatologiaPorID(int id)
	{
		PatologiasDTO patologia = new PatologiasDTO();
		List<GenericDTO> lista_sintomas = null;
		SintomasDAO sintomas_dao = new SintomasDAO();
		
		String id_St = String.valueOf(id);
		String nombre_patologia = "\"";
			try {
				patologia = (PatologiasDTO) ejecutarConsultaSimple(Consultas.CONSULTA_PATOLOGIAS_POR_ID, id_St);
				
				nombre_patologia = nombre_patologia + patologia.getNombre_patologia();
				
				nombre_patologia = nombre_patologia + "\"";
				
				lista_sintomas = sintomas_dao.seleccionarSintomasPorPatologia(lista_sintomas, nombre_patologia);
				
				patologia.setLista_sintomas(lista_sintomas);
				
			} 
				catch (Throwable e) 
				{
					log.error ("Ha ocurrido un error inesperado");
				}
		
		return patologia;
	}*/
	
	public Map<Integer, PatologiaDTO> obtenerListaPalogias ()
	{
		Map<Integer, PatologiaDTO> mapa_patologia = new HashMap<Integer, PatologiaDTO>();
		PatologiaDTO pdto_aux = null;
		
			 List<Integer> lids = obtenerIDsPatologias ();
			 
			 for (Integer i : lids)
			 {
				pdto_aux = buscarPorId(i);
				mapa_patologia.put(i, pdto_aux);
			 }
			 
			 	log.debug ("HashMap Patologias DTO creado");
			
		return mapa_patologia;
	}
	
	public PatologiaDTO buscarPorId (int id)
	{
		PatologiaDTO patologiaDTO = null;
		Pool pool = null;
		Connection conexion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		SintomaDTO sintomaDTO = null;
	
		try
		{
				log.debug ("Buscando por ID");
			pool = Pool.getInstance();
			conexion = pool.getConnection();
			ps = conexion.prepareStatement (Consultas.CONSULTA_PATO_POR_ID);
		
			ps.setInt(1, id);
			ps.setInt(2, id);
		
			rs = ps.executeQuery();
			
				log.debug ("Conexion establecida");
			
			if (rs.next())
			{
				patologiaDTO = (PatologiaDTO) componerObjeto(rs);
			}
		
			do
			{
				sintomaDTO = SintomasDAO.componerObjeto(rs);
				patologiaDTO.addSintoma(sintomaDTO);
					log.debug ("Sintoma añadido a la lista Patologias DTO");
				
			}	while (rs.next());
		
		} 
			catch (Exception e)
			{
				log.error ("Ha ocurrido un error inesperado al buscar patologias", e);
			} 
				finally 
				{
					log.debug ("Recursos Buscar patologia por Id liberados");
					pool.liberarRecursos(conexion, ps, rs);
				}
	
	return patologiaDTO;
	}
	
	private List<Integer> obtenerIDsPatologias () 
	{
		List<Integer> listaids = new ArrayList<Integer>();
			
		Pool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
			log.error ("Lista Obtener patologias por Id");
		
		try 
		{
			pool = Pool.getInstance();
			con = pool.getConnection();
			log.error ("Conexion establecida");
			st = con.createStatement();
			rs = st.executeQuery(Consultas.CONSULTA_ID_PATOLOGIAS);
				
				while (rs.next())
				{
					listaids.add(rs.getInt(1));
				}
				log.error ("Lista Obtener patologias por Id creada");				
		} 
			catch (Exception e) 
			{
				log.error ("Ha ocurrido un error inesperado al obtener patologias por Id", e);
			} 
				finally 
				{
					pool.liberarRecursos(con, st, rs);
						log.error ("Recursos obtener patologias por id liberados");
				}
			
		return listaids;	
	}
}