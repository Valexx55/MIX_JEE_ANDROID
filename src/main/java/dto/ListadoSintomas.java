package dto;

import java.util.ArrayList;
import java.util.List;

import dto.SintomasDTO;

public class ListadoSintomas {

	private static List<SintomasDTO> listado_sintomas;
	
	public void setMapapatologia(List<SintomasDTO> lista_sintomas) {
		ListadoSintomas.listado_sintomas = lista_sintomas;
	}

	public ListadoSintomas() {
		super();
		listado_sintomas = new ArrayList<SintomasDTO>();
	}
	
	public void addSintoma (SintomasDTO sintoDTO)
	{
		listado_sintomas.add(sintoDTO);
	}
	
	public static SintomasDTO getSintomas (Integer id)
	{
		return listado_sintomas.get(id);
	}
	
	public static List<SintomasDTO> listaSintomasCompleta(){
		return listado_sintomas;
	}
}
