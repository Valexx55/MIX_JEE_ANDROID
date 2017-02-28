package servicios;

import java.util.ArrayList;
import java.util.List;

import dto.ListadoSintomas;
import dto.SintomasDTO;

public class SintomasService {

	public List<SintomasDTO> buscarSintomaPorInicial(String inicial){
		List<SintomasDTO> lista_descripcion = new ArrayList<SintomasDTO>();
		List<SintomasDTO> lista_sintomas = ListadoSintomas.listaSintomasCompleta();
		String sintoma_descripcion = null;
		String[] palabras_sintoma_descripcion = null;
		boolean encontrado = false;
		inicial = inicial.toLowerCase();
		String sintoma_desc_aux = null;
		String palabra_sintoma = null;
		int contador = 0;
		
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
		
		return lista_descripcion;
	}
	
	
	
    public static String[] separarFrase(String frase_introducida) {

    	String delimitadores= "[ .,;?!��\'\"\\[\\]]+";
    	String[] palabrasSeparadas = frase_introducida.split(delimitadores);
    	
        return palabrasSeparadas;
    }
}