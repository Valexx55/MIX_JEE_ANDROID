package servicios;

import java.util.List;

import dao.PatologiasDAO;
import dto.*;

public class PatologiaService {
	
	public PatologiasDTO obtenerPatologiaPorID(int id){
		PatologiasDTO patoDTO = null;
		
		//VERSIN 1 .- Accediendo a la base de datos cada vez
			/*PatologiaDAO pdao = new PatologiaDAO();
			patoDTO = pdao.buscarPorId(id);
		
			System.out.println(patoDTO.toString());*/
		//FIN VERSI�N 1 .- Accediendo a la base de datos cada vez
			
		//VERSIN 2 .- Accediendo al Mapa precargado con Todas las patlogs
			
			patoDTO = MapaPatologias.getPatologia (id);
			
		//FIN VERSI�N 2 .- Accediendo al Mapa precargado con Todas las patlogs
			
		return patoDTO;
	}
}
