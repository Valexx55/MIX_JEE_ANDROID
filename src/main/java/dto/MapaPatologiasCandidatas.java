package dto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


public class MapaPatologiasCandidatas {
	
	private final static Logger log = Logger.getLogger("mylog");
	
	public  Map<Integer, PatologiasDTO> mapa_patologias_candidatas;

	public  Map<Integer, PatologiasDTO> getMapa_patologias_candidatas() {
		return mapa_patologias_candidatas;
	}

	public  void setMapa_patologias_candidatas(Map<Integer, PatologiasDTO> mapa_patologias_candidatas) {
		this.mapa_patologias_candidatas = mapa_patologias_candidatas;
	}
	

	public MapaPatologiasCandidatas()
	{
		
		this.mapa_patologias_candidatas = MapaPatologias.getMapapatologia();//new HashMap<Integer, PatologiaDTO>();
	}
	
	public MapaPatologiasCandidatas(Map<Integer, PatologiasDTO> mapa_patologias_candidatas)
	{
		
		this.mapa_patologias_candidatas = mapa_patologias_candidatas;//new HashMap<Integer, PatologiaDTO>();
	}
	
	
	public boolean sintomaEnPatologia(SintomasDTO sintoma, PatologiasDTO patologia)
	{
		boolean pertenece = false;
		int n_sintomas = 0;
		int pos_actual = 0;
		SintomasDTO sintoma_aux = null;
		
		List<SintomasDTO> lista = patologia.getLista_sintomas();
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
		
		log.debug ("SintomaDTO en PatologiaDTO existe");
		
		return pertenece;
	}

	public Map<Integer, PatologiasDTO> filtrarPatologiasCandidatas(Map<Integer, PatologiasDTO> patologias_candidatas, SintomasDTO sintoma_filtrar)
	{
		Map<Integer, PatologiasDTO> mapa_filtrado = null;
		PatologiasDTO patodto_aux = null;
		
		Iterator<Integer> it = patologias_candidatas.keySet().iterator();
		mapa_filtrado = new HashMap<Integer, PatologiasDTO>();
		
		while(it.hasNext())
		{
			
			patodto_aux = patologias_candidatas.get(it.next());
			if (sintomaEnPatologia(sintoma_filtrar, patodto_aux))
			{
				mapa_filtrado.put(patodto_aux.getId_patologia(), patodto_aux);
			}
			
			
		}
		
		log.debug ("Mapa PatologiaDTO candidatas filtradas");
		
		return mapa_filtrado;
	}
	
}
