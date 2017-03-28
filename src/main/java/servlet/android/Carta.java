package servlet.android;

public class Carta {

	private String id_remitente;
	private String id_destinatario;
	private String mensaje;
	
	
	
	public Carta(String id_remitente, String id_destinatario, String mensaje) {
		super();
		this.id_remitente = id_remitente;
		this.id_destinatario = id_destinatario;
		this.mensaje = mensaje;
	}
	public String getId_remitente() {
		return id_remitente;
	}
	public void setId_remitente(String id_remitente) {
		this.id_remitente = id_remitente;
	}
	public String getId_destinatario() {
		return id_destinatario;
	}
	public void setId_destinatario(String id_destinatario) {
		this.id_destinatario = id_destinatario;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
