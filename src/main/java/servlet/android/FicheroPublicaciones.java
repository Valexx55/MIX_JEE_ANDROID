package servlet.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.example.vale.adminbebe.dto.BuenosDias;

public class FicheroPublicaciones {
	
	/**
	 * POR LA SENCILLEZ DEL PROYECTO, SE VA A EMPLEAR UN FICHERO
	 * DONDE SER GUARDAN LAS PUBLICACIONES.
	 * 
	 * ESTE FICHERO, SERÁ EL HASHMAP SERIZALIZADO QUE CONTENGA
	 * EL CLAVE VALOR MAP < FECHA, REGISTRO_PUBLICACION (BuenosDias>>
	 */
	
	public static String RUTA_FICHERO_HASH_MAP = "programabb";
	
	
	public static void guardarPublicaciones (Map<String, BuenosDias> mapa_publicaciones) throws FileNotFoundException, IOException
	{
		File f = new File (RUTA_FICHERO_HASH_MAP);
		if (!f.exists())
			f.createNewFile();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(mapa_publicaciones);
		oos.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public static Map<String, BuenosDias> obtenerPublicaciones () throws FileNotFoundException, IOException, ClassNotFoundException
	{
		Map<String, BuenosDias> mapa_publicaciones_guardado = null;
		File f = new File (RUTA_FICHERO_HASH_MAP);
		if (f.exists())
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File (RUTA_FICHERO_HASH_MAP)));
			mapa_publicaciones_guardado = (Map<String, BuenosDias>)ois.readObject();
			ois.close();
		}
		return mapa_publicaciones_guardado;
	}

}
