package dto;

import org.apache.log4j.Logger;

public class Pregunta {
	
	private final static Logger log = Logger.getLogger("mylog");
	private String descrip_pregunta;

	public String getPregunta() {
		return descrip_pregunta;
	}

	public void setPregunta(String pregunta) {
		this.descrip_pregunta = pregunta;
	}

	public Pregunta(String pregunta) {
		super();
		this.descrip_pregunta = pregunta;
		log.debug("Pregunta Creada");
	}
	

}
