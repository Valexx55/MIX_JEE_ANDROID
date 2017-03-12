package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import dto.MapaPatologias;
import dto.PatologiaDTO;
import dto.SintomaDTO;

public class PatologiaService {
	private final static Logger log = Logger.getLogger("mylog");
	
	public PatologiaDTO obtenerPatologiaPorID(int id)
	{
		PatologiaDTO patoDTO = null;

			patoDTO = MapaPatologias.getPatologia (id);
			log.debug("PatologiasDTO buscada y devuelta");
			
		return patoDTO;
	}

	public List<PatologiaDTO> obtenerPatologiasPorSintoma(int id_sintoma){
		
		PatologiaService service = new PatologiaService();
		List<PatologiaDTO> lista_completa = new ArrayList<PatologiaDTO>();
		lista_completa = service.obtenerListaPatologias();
		List<PatologiaDTO> lista_devuelta = new ArrayList<PatologiaDTO>();
		List<SintomaDTO> lista_sintomas = new ArrayList<SintomaDTO>();
		log.debug("Recorre lista de PatologiasDTO");
		
		for(PatologiaDTO patologia:lista_completa)
		{
			lista_sintomas = patologia.getLista_sintomas();
			
					log.debug("Recorre lista de SintomasDTO asociados a PatologiasDTO");
			if(service.sintomaEncontrado(lista_sintomas, id_sintoma))
			{
				lista_devuelta.add(patologia);
				log.debug("Filtra PatologiasDTO asociadas y devuelve lista de SintomasDTO");
			}
		}
		
		return lista_devuelta;
	}
	
	public boolean sintomaEncontrado(List<SintomaDTO> lista_sintomas,int id_sintoma){
		boolean encontrado = false;
		SintomaDTO sintoma = new SintomaDTO();
		int contador = 0;
		int id_auxilar = 0;
		
		log.debug("Recorre lista de SintomasDTO");
		
		while(contador <lista_sintomas.size()&& !encontrado)
		{
			sintoma = lista_sintomas.get(contador);
			id_auxilar = sintoma.getId_sintoma();
			
			if(id_auxilar == id_sintoma)
			{
				
				encontrado = true;
				log.debug("Si se coinciden las id es true");
			}
			contador++;
		}
		
		return encontrado;
	}

	public List<PatologiaDTO> obtenerListaPatologias(){
		Map<Integer, PatologiaDTO> mapapatologia =  MapaPatologias.getMapapatologia();
		List<PatologiaDTO> lista_completa = new ArrayList<PatologiaDTO>();
		
		log.debug("Listar todas las PatologiasDTO");
		
		for (Integer nombre: mapapatologia.keySet())
		{
			PatologiaDTO patologia = mapapatologia.get(nombre);
	    	lista_completa.add(patologia);
		} 
		
		log.debug("Se devuelve la lista de PatologiasDTO");
		return lista_completa;
	}
	
}
