package servicios;

import dto.*;

public class PatologiaService
{
	public PatologiasDTO obtenerPatologiaPorID(int id)
	{
		PatologiasDTO patoDTO = null;
		
		//VERSION 1 .- Accediendo a la base de datos cada vez
			
			/*PatologiaDAO pdao = new PatologiaDAO();
			patoDTO = pdao.buscarPorId(id);
		
			System.out.println(patoDTO.toString());*/
		
		//FIN VERSION 1 .- Accediendo a la base de datos cada vez
			
		//VERSION 2 .- Accediendo al Mapa precargado con Todas las patlogs
			
			patoDTO = MapaPatologias.getPatologia (id);
			
		//FIN VERSION 2 .- Accediendo al Mapa precargado con Todas las patlogs
			
		return patoDTO;
	}
}
