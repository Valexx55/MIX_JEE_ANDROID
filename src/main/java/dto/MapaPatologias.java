package dto;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dto.PatologiaDTO;

public class MapaPatologias {
	
	private static Map<Integer, PatologiaDTO> mapapatologia;
	
	static 
	{
		mapapatologia = new HashMap<Integer, PatologiaDTO>();
	}
	

	public static Map<Integer, PatologiaDTO> getMapapatologia() 
	{
		return mapapatologia;
	}

	public static void setMapapatologia(Map<Integer, PatologiaDTO> mapapatologia) 
	{
		MapaPatologias.mapapatologia = mapapatologia;
	}

	
	
	public static void addPatologia (Integer id, PatologiaDTO pdto)
	{
		mapapatologia.put(id, pdto);
	}
	
	
	public static PatologiaDTO getPatologia (Integer id)
	{
		return mapapatologia.get(id);
	}
	

	public static Map<Integer, PatologiaDTO> diferencia (Map<Integer, PatologiaDTO> mapaInicial, Map<Integer, PatologiaDTO> mapaFiltrado)
	{
		Map<Integer, PatologiaDTO> mapa_resultado = null;
		
			mapa_resultado = new HashMap<Integer, PatologiaDTO>();
			
			
			Iterator<Integer> itg= mapaInicial.keySet().iterator();
			PatologiaDTO pauxi = null;
			
			
			
			while (itg.hasNext())
			{
				pauxi = mapaInicial.get(itg.next());
				boolean encontrado = false;
				

				Iterator<Integer> itp = mapaFiltrado.keySet().iterator();
				PatologiaDTO pauxf = null;
								
				while (!encontrado && itp.hasNext()) 
				{
					pauxf = mapaFiltrado.get(itp.next());
					encontrado = pauxf.equals(pauxi);
				}
				//si no está, me la quedo
				if (!encontrado)
				{
					mapa_resultado.put(pauxi.getId_patologia(), pauxi);
				}
				
			}
		
		
		return mapa_resultado;
	}
}
