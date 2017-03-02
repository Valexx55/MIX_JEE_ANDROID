package servicios;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dao.SintomasDAO;
import dto.ListadoSintomas;
import dto.SintomasDTO;

public class SintomasService {

	private final static Logger log = Logger.getLogger("mylog");
	
	public List<SintomasDTO> buscarSintomaPorInicial(String inicial){
		
		log.debug("Entramos en buscarsintoamporinicial service");
		
		List<SintomasDTO> lista_descripcion = new ArrayList<SintomasDTO>();
		List<SintomasDTO> lista_sintomas = ListadoSintomas.listaSintomasCompleta();
		String sintoma_descripcion = null;
		String[] palabras_sintoma_descripcion = null;
		boolean encontrado = false;
		inicial = inicial.toLowerCase();
		String sintoma_desc_aux = null;
		String palabra_sintoma = null;
		int contador = 0;
		
		log.debug("Recorro lista de síntomas");
		
		try{
			
		
		for (SintomasDTO sintoma:lista_sintomas)
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

    	String delimitadores= "[ .,;?!��\'\"\\[\\]]+";
    	String[] palabrasSeparadas = frase_introducida.split(delimitadores);
    	
        return palabrasSeparadas;
    }
    
    public List<SintomasDTO> listarSintomasOrdenados()
	{	
		List<SintomasDTO> lista_sintomas = null;
		
			lista_sintomas = SintomasDAO.getSintomasOrdenados(); 
		
		return lista_sintomas;
	}
}