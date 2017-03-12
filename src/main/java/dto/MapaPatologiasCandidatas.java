package dto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MapaPatologiasCandidatas {
	
	
	public  Map<Integer, PatologiaDTO> mapa_patologias_candidatas;

	public  Map<Integer, PatologiaDTO> getMapa_patologias_candidatas() {
		return mapa_patologias_candidatas;
	}

	public  void setMapa_patologias_candidatas(Map<Integer, PatologiaDTO> mapa_patologias_candidatas) {
		this.mapa_patologias_candidatas = mapa_patologias_candidatas;
	}
	

	public MapaPatologiasCandidatas()
	{
		
		this.mapa_patologias_candidatas = MapaPatologias.getMapapatologia();//new HashMap<Integer, PatologiaDTO>();
	}
	
	public MapaPatologiasCandidatas(Map<Integer, PatologiaDTO> mapa_patologias_candidatas)
	{
		
		this.mapa_patologias_candidatas = mapa_patologias_candidatas;//new HashMap<Integer, PatologiaDTO>();
	}
	
	
	public static boolean sintomaEnPatologia(SintomaDTO sintoma, PatologiaDTO patologia)
	{
		boolean pertenece = false;
		int n_sintomas = 0;
		int pos_actual = 0;
		SintomaDTO sintoma_aux = null;
		
		List<SintomaDTO> lista = patologia.getLista_sintomas();
		n_sintomas = lista.size();
		
		do
		{
			sintoma_aux = lista.get(pos_actual);
			if(sintoma_aux.equals(sintoma))
			{
				pertenece = true;
			}
			else
			{
				pos_actual = pos_actual + 1;
			}
		}
		while(!pertenece && (pos_actual < n_sintomas));
			
		
		return pertenece;
	}

	public Map<Integer, PatologiaDTO> filtrarPatologiasCandidatas(Map<Integer, PatologiaDTO> patologias_candidatas, SintomaDTO sintoma_filtrar)
	{
		Map<Integer, PatologiaDTO> mapa_filtrado = null;
		PatologiaDTO patodto_aux = null;
		
		Iterator<Integer> it = patologias_candidatas.keySet().iterator();
		mapa_filtrado = new HashMap<Integer, PatologiaDTO>();
		
		while(it.hasNext())
		{
			
			patodto_aux = patologias_candidatas.get(it.next());
			if (sintomaEnPatologia(sintoma_filtrar, patodto_aux))
			{
				mapa_filtrado.put(patodto_aux.getId_patologia(), patodto_aux);
			}
		
		}
		
		return mapa_filtrado;
	}
	
}
