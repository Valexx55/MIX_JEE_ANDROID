package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import dto.MapaPatologias;
import dto.PatologiasDTO;
import dto.SintomasDTO;

public class PatologiaService {
	
	public PatologiasDTO obtenerPatologiaPorID(int id){
		PatologiasDTO patoDTO = null;

			patoDTO = MapaPatologias.getPatologia (id);
			
		return patoDTO;
	}

	public List<PatologiasDTO> obtenerPatologiasPorSintoma(int id_sintoma){
		
		PatologiaService service = new PatologiaService();
		List<PatologiasDTO> lista_completa = new ArrayList<PatologiasDTO>();
		lista_completa = service.obtenerListaPatologias();
		List<PatologiasDTO> lista_devuelta = new ArrayList<PatologiasDTO>();
		List<SintomasDTO> lista_sintomas = new ArrayList<SintomasDTO>();
		
		
		for(PatologiasDTO patologia:lista_completa)
		{
			lista_sintomas = patologia.getLista_sintomas();
			
					
			if(service.sintomaEncontrado(lista_sintomas, id_sintoma))
			{
				lista_devuelta.add(patologia);
			}
		}
		
		return lista_devuelta;
	}
	
	public boolean sintomaEncontrado(List<SintomasDTO> lista_sintomas,int id_sintoma){
		boolean encontrado = false;
		SintomasDTO sintoma = new SintomasDTO();
		int contador = 0;
		int id_auxilar = 0;
		
		while(contador <lista_sintomas.size()&& !encontrado)
		{
			sintoma = lista_sintomas.get(contador);
			id_auxilar = sintoma.getId_sintoma();
			
			if(id_auxilar == id_sintoma)
			{
				encontrado = true;
			}
			contador++;
		}
		
		return encontrado;
	}

	public List<PatologiasDTO> obtenerListaPatologias(){
		Map<Integer, PatologiasDTO> mapapatologia =  MapaPatologias.obtenerMapapatologia();
		List<PatologiasDTO> lista_completa = new ArrayList<PatologiasDTO>();
		
		for (Integer nombre: mapapatologia.keySet())
		{
			PatologiasDTO patologia = mapapatologia.get(nombre);
	    	lista_completa.add(patologia);
		} 
		
		
		return lista_completa;
	}
	
}
