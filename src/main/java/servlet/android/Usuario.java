package servlet.android;
import java.util.List;

public class Usuario {

	private String nombre;
	private String id;
	private List<Mensaje> lista_recibidos;
	private List<Mensaje> lista_enviados;
	
	public Usuario ()
	{
		
	}	
	public Usuario(String nombre, String id, List<Mensaje> lista_recibidos, List<Mensaje> lista_enviados) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.lista_recibidos = lista_recibidos;
		this.lista_enviados = lista_enviados;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Mensaje> getLista_recibidos() {
		return lista_recibidos;
	}
	public void setLista_recibidos(List<Mensaje> lista_recibidos) {
		this.lista_recibidos = lista_recibidos;
	}
	public List<Mensaje> getLista_enviados() {
		return lista_enviados;
	}
	public void setLista_enviados(List<Mensaje> lista_enviados) {
		this.lista_enviados = lista_enviados;
	}
}
