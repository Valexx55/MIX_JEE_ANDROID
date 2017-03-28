package servlet.android;

public class Mensaje {

	private int id;
	private String mensaje;
	private String fecha;
	private Usuario remitente;
	private Usuario destinatario;
	private boolean leido;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Usuario getRemitente() {
		return remitente;
	}
	public void setRemitente(Usuario remitente) {
		this.remitente = remitente;
	}
	public Usuario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}
	public boolean isLeido() {
		return leido;
	}
	public void setLeido(boolean leido) {
		this.leido = leido;
	}
	public Mensaje(int id, String mensaje, String fecha, Usuario remitente, Usuario destinatario, boolean leido) {
		super();
		this.id = id;
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.leido = leido;
	}
}
