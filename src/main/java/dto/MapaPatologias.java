package dto;
import java.util.HashMap;
import java.util.Map;

import dto.PatologiasDTO;

public class MapaPatologias {
	
	private static Map<Integer, PatologiasDTO> mapapatologia;

	public Map<Integer, PatologiasDTO> getMapapatologia() {
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
}
