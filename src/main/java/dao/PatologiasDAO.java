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

import consulta.Consultas;
import dto.*;


public class PatologiasDAO {

	public PatologiasDTO componerObjeto(ResultSet rs) throws SQLException {
		
		PatologiasDTO patologia_dto = null;
		
		int id_patologia = rs.getInt("id_patol");
		String nombre_patologia = rs.getString("nom_patol");
		String descripcion_patologia = rs.getString("des_patol");
		String tratamiento_patologia = rs.getString("trat_patol");
		String causa_patologia = rs.getString("causa_patol");
		String ruta_imagen_patologia = rs.getString("imagen");
		
		patologia_dto = new PatologiasDTO(id_patologia,nombre_patologia,descripcion_patologia,tratamiento_patologia,causa_patologia,ruta_imagen_patologia);
		
		return patologia_dto;
	}
	
	/*public GenericDTO buscarPatologiaPorID(int id){
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
				
			} catch (Throwable e) 
			{
				e.printStackTrace();
			}
		
		return patologia;
	}*/
	
	public Map<Integer, PatologiasDTO> obtenerListaPalogias ()
	{
		Map<Integer, PatologiasDTO> mapa_patologia = new HashMap<Integer, PatologiasDTO>();
		PatologiasDTO pdto_aux = null;
		
			 List<Integer> lids = obtenerIDsPatologias ();
			 
			 for (Integer i : lids)
			 {
				pdto_aux = buscarPorId(i);
				mapa_patologia.put(i, pdto_aux);
			
			 }
			
		return mapa_patologia;
	}
	
	public PatologiasDTO buscarPorId (int id)
	{
	PatologiasDTO patologiaDTO = null;
	Pool pool = null;
	Connection conexion = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	SintomasDTO sintomaDTO = null;
	
	try{
		
	
		pool = Pool.getInstance();
		conexion = pool.getConnection();
		ps = conexion.prepareStatement(Consultas.CONSULTA_PATO_POR_ID);
		
		ps.setInt(1, id);
		ps.setInt(2, id);
		
		rs = ps.executeQuery();
		
		if (rs.next())
		{
			patologiaDTO = (PatologiasDTO) componerObjeto(rs);
		}
		
		do
		{
			sintomaDTO = SintomasDAO.componerObjeto(rs);
			patologiaDTO.addSintoma(sintomaDTO);
			
		}while (rs.next());
		
		
		
	} catch (Exception e)
	{
		e.printStackTrace();
		
	} finally 
	{
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
			
			try {
				
				pool = Pool.getInstance();
				con = pool.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(Consultas.CONSULTA_ID_PATOLOGIAS);
				
					while (rs.next())
					{
						listaids.add(rs.getInt(1));
					}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				
				pool.liberarRecursos(con, st, rs);
				
			}
			
			return listaids;
			
		}

}