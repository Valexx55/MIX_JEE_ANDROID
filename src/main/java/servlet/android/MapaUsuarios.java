package servlet.android;
import java.util.HashMap;

public class MapaUsuarios {
	
	private static HashMap<String, Usuario> hm = new HashMap<String, Usuario>();

	public static HashMap<String, Usuario> getHm() {
		return hm;
	}

	public static void setHm(HashMap<String, Usuario> hm) {
		MapaUsuarios.hm = hm;
	}

	public MapaUsuarios ()
	{
		MapaUsuarios.hm = new HashMap<String, Usuario>();
	}
	
	
}
