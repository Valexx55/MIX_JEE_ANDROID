package dto;

public class Pregunta {
	
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
	}
	

}
