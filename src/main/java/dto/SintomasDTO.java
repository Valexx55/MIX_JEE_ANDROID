package dto;

import org.apache.log4j.Logger;

public class SintomasDTO
{	
	private final static Logger log = Logger.getLogger("mylog");
	
	private int id_sintoma;
	private String descripcion;
	private String pregunta_web;
	private int prioridad_sint;
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.id_sintoma == ((SintomasDTO) obj).id_sintoma;
	}


	public String getPregunta_web() {
		return pregunta_web;
	}



	public void setPregunta_web(String pregunta_web) {
		this.pregunta_web = pregunta_web;
	}



	public int getPrioridad_sint() {
		return prioridad_sint;
	}



	public void setPrioridad_sint(int prioridad_sint) {
		this.prioridad_sint = prioridad_sint;
	}





	public SintomasDTO(int id, String descripcion, String pregunta_web, int prioridad_sint) {
		super();
		this.id_sintoma = id;
		this.descripcion = descripcion;
		this.pregunta_web = pregunta_web;
		this.prioridad_sint = prioridad_sint;
	}

	
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
