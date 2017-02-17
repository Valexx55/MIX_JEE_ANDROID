package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class Pool {
	
	private static final String fuente = "java:comp/env/jdbc/pool";
	//private static final Logger log = LogManager.getRootLogger();
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
	}
		

	private static DataSource iniciarFuente ()
	{
		DataSource ds = null;
		
		try
		{
			InitialContext ct = new InitialContext ();
			ds = (DataSource)ct.lookup(fuente);
		}
		catch (Exception e) {
			System.out.println("Error al acceder a los recursos del context.xml para configurar el Pool de la base de datos");
		}
		return ds;
	}
	
	
	public static Connection getConnection ()
	{
		Connection conexion = null;
		try
		{
			conexion = fuenteDatos.getConnection();
			//fuenteDatos.
		}
		catch (Exception e) {
		}
		return conexion;
	}
	
	public static void liberarRecursos (Connection conexion, Statement st, ResultSet rs)
	{
			
			 try 
			 {
				 rs.close(); 
			 }
			 catch (SQLException e)
			 {
				 System.out.println("Error al liberar el ResultSet");
			 }
			 try 
			 {
				 st.close(); 
			 }
			 catch (SQLException e)
			 {
				 System.out.println("Error al liberar el Statement"); 
			 }
			 try 
			 {conexion.close();
			 
			 } catch (SQLException e) {
			}
		
	}
	
	
	public static void liberarRecursos (Connection conexion, Statement st)
	{
			
			 try 
			 {
				 st.close(); 
			 }
			 catch (SQLException e)
			 {
				 System.out.println("Error al liberar el Statement"); 
			 }
			 try 
			 {conexion.close();
			 
			 } catch (SQLException e) {
			}
	}
}
