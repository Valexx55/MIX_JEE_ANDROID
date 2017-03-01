package dto;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dto.PatologiasDTO;

public class MapaPatologias {
	
	private static Map<Integer, PatologiasDTO> mapapatologia;

	public static Map<Integer, PatologiasDTO> getMapapatologia() {
		return mapapatologia;
	}

	public void setMapapatologia(Map<Integer, PatologiasDTO> mapapatologia) {
		MapaPatologias.mapapatologia = mapapatologia;
	}

	public MapaPatologias() {
		super();
		mapapatologia = new HashMap<Integer, PatologiasDTO>();
	}
	
	public void addPatologia (Integer id, PatologiasDTO pdto)
	{
		mapapatologia.put(id, pdto);
	}
	
	public static  Map<Integer, PatologiasDTO> obtenerMapapatologia(){
		return mapapatologia;
	}
	
	public static PatologiasDTO getPatologia (Integer id)
	{
		return mapapatologia.get(id);
	}
	

	public static Map<Integer, PatologiasDTO> diferencia (Map<Integer, PatologiasDTO> mapaInicial, Map<Integer, PatologiasDTO> mapaFiltrado)
	{
		Map<Integer, PatologiasDTO> mapa_resultado = null;
		
			mapa_resultado = new HashMap<Integer, PatologiasDTO>();
			
			
			Iterator<Integer> itg= mapaInicial.keySet().iterator();
			PatologiasDTO pauxi = null;
			
			
			
			while (itg.hasNext())
			{
				pauxi = mapaInicial.get(itg.next());
				boolean encontrado = false;
				

				Iterator<Integer> itp = mapaFiltrado.keySet().iterator();
				PatologiasDTO pauxf = null;
								
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
