package servicios;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dto.*;

public class ConsultorPatologiasService
{
	private final static Logger log = Logger.getLogger("mylog");
	
	/**  Primero de todo una pagina web envia un resultado dada la primera pregunta del test
	 * (que ser siempre la misma) en funcion del resultado se llamara a patologiasConSintoma
	 * o a patologiasSinSintoma mediante el metodo controlDeTest
	 * Cuando devuelva la lista de patologias se llamar a sintomaAPreguntar para poder
	 * realizar la siguiente pregunta del test  */
	
	/**  Se encarga de llamar a patologiasConSintoma o a patologiasSinSintoma en funcion de la respuesta
	 * del usuario y devuelve la lista de patologias resultante  */
	
	public List<PatologiaDTO> controlDeTest (List<PatologiaDTO> listaPatologias,SintomaDTO sintoma,boolean respuestaDelUsuario)
	{
		return listaPatologias;
	}
	
		/**	Devuelve una lista de patologias que SI tengan el sintoma introducido  */
	public List<PatologiaDTO> patologiasConSintoma(List<PatologiaDTO> listaPatologias,SintomaDTO sintoma_incluido)
	{
		List <PatologiaDTO> lista_devuelta = new ArrayList<PatologiaDTO>();
		ConsultorPatologiasService consultor = new ConsultorPatologiasService();
			log.debug ("Lista Patologias DTO creada (sintomas incluidos en la lista)");
		
		for (PatologiaDTO patologia:listaPatologias)
		{
			if(consultor.comprobarSintomaEnPatologia(patologia, sintoma_incluido))
			{
				lista_devuelta.add(patologia);
			}
		}
			log.debug ("Lista Patologias DTO Con Sintoma recorrida");
		
		return lista_devuelta;
	}
	
		/** Devuelve una lista de patologias que NO tengan el sintoma introducido */
	public List<PatologiaDTO> patologiasSinSintoma(List<PatologiaDTO> listaPatologias,SintomaDTO sintoma_excluido)
	{
		List <PatologiaDTO> lista_devuelta = new ArrayList<PatologiaDTO>();
		ConsultorPatologiasService consultor = new ConsultorPatologiasService();
			log.debug ("Lista Patologias DTO creada (sintomas excluidos de la lista)");
		
		for(PatologiaDTO patologia:listaPatologias)
		{
			if(!consultor.comprobarSintomaEnPatologia(patologia, sintoma_excluido))
			{
				lista_devuelta.add(patologia);
			}
		}
			log.debug ("Lista Patologias DTO Sin Sintoma recorrida");
			
		return lista_devuelta;
	}
	
		/**  Devuelve un true o false si la patologia tiene el sintoma introducido  */
	public boolean comprobarSintomaEnPatologia(PatologiaDTO patologia,SintomaDTO sintoma_buscado)
	{
		boolean encontrado = false;
		List<SintomaDTO> lista_sintomas = patologia.getLista_sintomas();
		SintomaDTO sintoma_auxiliar = new SintomaDTO();
		String nombre_sintoma = null;
		String nombre_sintoma_buscado = sintoma_buscado.getDescripcion();
		int contador = 0;
		
			log.debug ("Metodo comprobar sintomas en patologias iniciado... Buscando sintomas");
		
			while(!encontrado && contador <lista_sintomas.size())
			{
				sintoma_auxiliar = lista_sintomas.get(contador);
				nombre_sintoma = sintoma_auxiliar.getDescripcion();
				
				if(nombre_sintoma.equals(nombre_sintoma_buscado))
				{
					encontrado=true;
				}
				log.debug ("Recorriendo lista de patologia en busca de sintomas..");
			}
				log.debug ("Sintoma encontrado = TRUE, Sintoma no encontrado = FALSE");
				
		return encontrado;
	}
	
		/**  Dada una lista de Patologias se devuelve el sintoma mas comun de ellas */
	public SintomaDTO sintomaAPreguntar(List<PatologiaDTO> lista_patologias)
	{
		SintomaDTO sintoma_pregunta = null;
			log.debug ("Preguntando por los sintomas..");
		return sintoma_pregunta;
	}
}