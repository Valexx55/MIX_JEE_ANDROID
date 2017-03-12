package dto;

import java.util.ArrayList;
import java.util.List;

import dto.SintomaDTO;

public class ListadoSintomas {

	private static List<SintomaDTO> listado_sintomas;
	private static List<SintomaDTO> listado_sintomas_ordenado;
	
	
	static
	{
		listado_sintomas = new ArrayList<SintomaDTO>();
		listado_sintomas_ordenado = new ArrayList<SintomaDTO>();
	}
	
	public static List<SintomaDTO> getListado_sintomas_ordenado() {
		return listado_sintomas_ordenado;
	}

	public static void setListado_sintomas_ordenado(List<SintomaDTO> listado_sintomas_ordenado) {
		ListadoSintomas.listado_sintomas_ordenado = listado_sintomas_ordenado;
	}

	public static void setListado_sintomas(List<SintomaDTO> lista_sintomas) {
		ListadoSintomas.listado_sintomas = lista_sintomas;
	}

	public static void addSintoma (SintomaDTO sintoDTO)
	{
		listado_sintomas.add(sintoDTO);
	}
	
	public static SintomaDTO getSintoma (Integer n_sintoma)
	{
		return listado_sintomas.get(n_sintoma);
	}
	
	public static List<SintomaDTO> getListado_sintomas(){
		return listado_sintomas;
	}
}
