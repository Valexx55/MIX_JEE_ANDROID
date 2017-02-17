package dto;

import java.util.List;

public class SintomasDTO{
	
	private int id_sintoma;
	private String descripcion;
	
	public SintomasDTO(int id_sintoma, String nombre_sintoma)
	{
		super();
		this.id_sintoma = id_sintoma;
		this.descripcion = nombre_sintoma;
	}

	public SintomasDTO(){
		
	}
	
	public SintomasDTO(String nombre_sintoma) {
		this.descripcion = nombre_sintoma;
	}

	public int getId_sintoma() {
		return id_sintoma;
	}
	public void setId_sintoma(int id_sintoma) {
		this.id_sintoma = id_sintoma;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String nombre_sintoma) {
		this.descripcion = nombre_sintoma;
	}
	
}
