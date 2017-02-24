package dto;

import org.apache.log4j.Logger;

public class SintomasDTO
{	
	private final static Logger log = Logger.getLogger("mylog");
	
	private int id_sintoma;
	private String descripcion;
	
	public SintomasDTO(int id_sintoma, String nombre_sintoma)
	{
		super();
		this.id_sintoma = id_sintoma;
		this.descripcion = nombre_sintoma;
		log.debug ("Objeto sintomas DTO creado");
	}

	public SintomasDTO()
	{
		log.debug ("Sintomas DTO");
	}
	
	public SintomasDTO(String nombre_sintoma)
	{
		this.descripcion = nombre_sintoma;
	}

	public int getId_sintoma()
	{
		return id_sintoma;
	}
	
	public void setId_sintoma(int id_sintoma)
	{
		this.id_sintoma = id_sintoma;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public void setDescripcion(String nombre_sintoma)
	{
		this.descripcion = nombre_sintoma;
	}
}
