package bd.com;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
public class Database {
	private DataSource dataSource;
	private static Database database;
	public Database(String jndiname)throws SQLException {
		try
		{
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/"+ jndiname);
	     }
		catch(NamingException e) {
			// Handle error that it’s not configured in JNDI.
	
			throw new SQLException(jndiname +" is missing in JNDI! : "+e.getMessage());
		}

	}
	
	public Connection getConnection() throws SQLException {
	
		return (Connection) dataSource.getConnection();
	
	}
	public static Connection getMySQLConnection() throws SQLException {
			if (DBStatic.mysqlpooling==false) {
				
				return (Connection) (
			DriverManager.getConnection("jdbc:mysql://"+ DBStatic.mysqlhost +"/"+DBStatic.mysqldb, 
					DBStatic.mysqlusername, 
					DBStatic.mysqlpassword));
			}
		    else
			{
		    	if(database==null) {
		    		database=new Database("jdbc/db");
				 }
				return(database.getConnection());
			}
			

	}

}
