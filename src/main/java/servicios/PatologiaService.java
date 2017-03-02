package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import dto.MapaPatologias;
import dto.PatologiasDTO;
import dto.SintomasDTO;

public class PatologiaService {
	private final static Logger log = Logger.getLogger("mylog");
	
	public PatologiasDTO obtenerPatologiaPorID(int id){
		PatologiasDTO patoDTO = null;

			patoDTO = MapaPatologias.getPatologia (id);
			log.debug("PatologiasDTO buscada y devuelta");
		return patoDTO;
	}

	public List<PatologiasDTO> obtenerPatologiasPorSintoma(int id_sintoma){
		
		PatologiaService service = new PatologiaService();
		List<PatologiasDTO> lista_completa = new ArrayList<PatologiasDTO>();
		lista_completa = service.obtenerListaPatologias();
		List<PatologiasDTO> lista_devuelta = new ArrayList<PatologiasDTO>();
		List<SintomasDTO> lista_sintomas = new ArrayList<SintomasDTO>();
		log.debug("Recorre lista de PatologiasDTO");
		
		for(PatologiasDTO patologia:lista_completa)
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
	
	public boolean sintomaEncontrado(List<SintomasDTO> lista_sintomas,int id_sintoma){
		boolean encontrado = false;
		SintomasDTO sintoma = new SintomasDTO();
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

	public List<PatologiasDTO> obtenerListaPatologias(){
		Map<Integer, PatologiasDTO> mapapatologia =  MapaPatologias.obtenerMapapatologia();
		List<PatologiasDTO> lista_completa = new ArrayList<PatologiasDTO>();
		
		log.debug("Listar todas las PatologiasDTO");
		
		for (Integer nombre: mapapatologia.keySet())
		{
			PatologiasDTO patologia = mapapatologia.get(nombre);
	    	lista_completa.add(patologia);
		} 
		
		log.debug("Se devuelve la lista de PatologiasDTO");
		return lista_completa;
	}
	
}
