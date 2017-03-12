package servicios;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dao.SintomasDAO;
import dto.ListadoSintomas;
import dto.SintomaDTO;

public class SintomasService {

	private final static Logger log = Logger.getLogger("mylog");
	
	public List<SintomaDTO> buscarSintomaPorInicial(String inicial){
		
		log.debug("Entramos en buscarsintoamporinicial service");
		
		List<SintomaDTO> lista_descripcion = new ArrayList<SintomaDTO>();
		List<SintomaDTO> lista_sintomas = ListadoSintomas.getListado_sintomas();
		String sintoma_descripcion = null;
		String[] palabras_sintoma_descripcion = null;
		boolean encontrado = false;
		inicial = inicial.toLowerCase();
		String sintoma_desc_aux = null;
		String palabra_sintoma = null;
		int contador = 0;
		
		log.debug("Recorro lista de síntomas");
		
		try{
			
		
		for (SintomaDTO sintoma:lista_sintomas)
		{
			sintoma_descripcion = sintoma.getDescripcion();
	        
	        palabras_sintoma_descripcion = separarFrase(sintoma_descripcion);
	        
	        while(!encontrado&&contador<palabras_sintoma_descripcion.length)
	        {
	        	palabra_sintoma = palabras_sintoma_descripcion[contador];
	        	sintoma_desc_aux = palabra_sintoma.toLowerCase();
	        	if(sintoma_desc_aux.startsWith(inicial)) 
	        	{
	        		lista_descripcion.add(sintoma);
	        		encontrado = true;
	        		
	        	}
	        	
	        	contador ++;
	        }
	        contador = 0;
			encontrado = false;

		}
		} catch (Exception e)
		{
			log.error("NO SE QUE PETA!", e);
		}
		
		log.debug("FIN Recorro lista de síntomas");
		
		return lista_descripcion;
	}

	/**
	 * Dada una frase introducida devuelve un array con las palabras que contiene
	 * @param frase_introducida
	 * @return
	 */
    public static String[] separarFrase(String frase_introducida) {

    	log.debug("Se separa la frase por delimitadores");
    	
    	String delimitadores= "[ .,;?!��\'\"\\[\\]]+";
    	String[] palabrasSeparadas = frase_introducida.split(delimitadores);
    	
    	log.debug("Se devuelve la frase separada");
        return palabrasSeparadas;
    }
    
    public List<SintomaDTO> listarSintomasOrdenados()
	{	
    	log.debug("Se listan los sintomas y se devuelven ordenados");
		List<SintomaDTO> lista_sintomas = null;
		
			lista_sintomas = SintomasDAO.getSintomasOrdenados(); 
		
		return lista_sintomas;
	}
}