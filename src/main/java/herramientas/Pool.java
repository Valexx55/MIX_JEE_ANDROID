package herramientas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Pool {

	public static Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try 
		{
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:xe", "HR", "password");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
