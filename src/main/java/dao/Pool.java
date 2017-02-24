package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;


public class Pool
{	
	private final static Logger log = Logger.getLogger("mylog");
	
	private static final String fuente = "java:comp/env/jdbc/pool";
	private static DataSource fuenteDatos = null;;
	private static final Pool pool = new Pool ();
	
	
	public static Pool getInstance ()
	{
		return pool;
	}
		//singelton
	private Pool ()
	{
		fuenteDatos = iniciarFuente();
		log.debug ("Fuente de datos iniciada");
	}
		
	private static DataSource iniciarFuente ()
	{
		DataSource ds = null;
		
			try
			{
				InitialContext ct = new InitialContext ();
				ds = (DataSource)ct.lookup(fuente);
					log.debug ("Fuente de datos iniciada");
			}
				catch (Exception e)
				{
					log.error ("Error al acceder a los recursos del context.xml para configurar el Pool de la base de datos");
				}
		
		return ds;
	}
	
	public static Connection getConnection ()
	{
		Connection conexion = null;
		
			try
			{
				conexion = fuenteDatos.getConnection();
					log.debug ("Conexion de fuente de datos establecida");
			}
				catch (Exception e) 
				{
					log.error ("Error al intentar conectar con la fuente de datos");
				}
			
		return conexion;
	}
	
	public static void liberarRecursos (Connection conexion, Statement st, ResultSet rs)
	{
		try 
		{
			rs.close(); 
				log.debug ("ResulSet liberado");
		}
			catch (SQLException e)
			{
				log.error ("Error al liberar el ResultSet");
			}
		
		try 
		{			
			st.close(); 
				log.debug ("Statement liberado");
		}
			catch (SQLException e)
			{
				log.error ("Error al liberar el Statement"); 
			}
		
		try 
		{
			conexion.close();
				log.debug ("Connection liberado");
		} 
			catch (SQLException e)
			{
				log.error ("Error al liberar la Connection");	
			}
	}
	
	public static void liberarRecursos (Connection conexion, Statement st)
	{
		try 
		{
			st.close();
				log.debug ("Statement liberado");
		}
			catch (SQLException e)
			{
				log.error ("Error al liberar el Statement"); 
			}
		
		try 
		{
			conexion.close();
				log.debug ("Connection liberado");
		} 
			catch (SQLException e)
			{
				log.error ("Error al liberar la Connection"); 	
			}
	}
}
