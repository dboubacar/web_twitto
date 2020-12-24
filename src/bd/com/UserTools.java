package bd.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTools {
	private static Connection conn;
	private static Statement state = null;
	private static ResultSet result = null; 

	public static boolean checkPassword(String login, String password) throws BDException{
		// TODO Auto-generated method stub
		 try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
			 conn=Database.getMySQLConnection();
			 state=conn.createStatement();
			String sql="Select * from Users where login=\""+login+"\" and password=('"+password+"');";
			 //String sql="Select * from Users where login=\""+login+"\" and password=MD5("+password+");";

			 result=state.executeQuery(sql);
			 if(result.next())
				 return true;
		 }catch(SQLException e){
			 new BDException("Erreur de bd "+e.getMessage());
		 }catch (ClassNotFoundException e) {
				new BDException(e.getMessage());
			}
			catch (IllegalAccessException e) {
				new BDException(e.getMessage());
			}
			catch (InstantiationException e){
				new BDException(e.getMessage());
			}
		 return false;
	
		
	}

	public static int getIdUser(String login) throws BDException{
		// TODO Auto-generated method stub
		 
		
		 try{
			 conn = Database.getMySQLConnection();
			 state= conn.createStatement();
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
				// Exécution de la requête
			 result= state.executeQuery("SELECT id_user FROM Users where login=\""+login+"\";");
				// Récupération des données
			 if(result.next())
				 return result.getInt("id_user");
		
		 	}catch (SQLException e) {
		 		new BDException("Erreur de connexion a la bd "+e.getMessage());
			
		 	
		 	}catch (ClassNotFoundException e) {
				new BDException(e.getMessage());
			}
			catch (IllegalAccessException e) {
				new BDException(e.getMessage());
			}
		 catch (InstantiationException e){
				new BDException(e.getMessage());
			}
		 return 0;
		  
		
		
	}
	
	public static String getLogin(int id) throws BDException{
		// TODO Auto-generated method stub
		 
		
		 try{
			 conn = Database.getMySQLConnection();
			 state= conn.createStatement();
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
				// Exécution de la requête
			 result= state.executeQuery("SELECT login FROM Users where id_user="+id+";");
				// Récupération des données
			 if(result.next())
				 return result.getString("login");
		
		 	}catch (SQLException e) {
		 		new BDException("Erreur de connexion a la bd "+e.getMessage());
			
		 	}catch (ClassNotFoundException e) {
				new BDException(e.getMessage());
			}
			catch (IllegalAccessException e) {
				new BDException(e.getMessage());
			}
			catch (InstantiationException e){
				new BDException(e.getMessage());
			}
		 return null;
		  
		
		
	}

	public static boolean userExist(String login) throws BDException{
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=Database.getMySQLConnection();
			state=conn.createStatement();
		    String query="select id_user from Users where login=\""+login+"\";";

			result=state.executeQuery(query);
			if(result.next()) {
				return true;}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 new BDException("impossible de connexion bd "+e.getMessage());
		}catch (ClassNotFoundException e) {
			new BDException(e.getMessage());
		}
		catch (IllegalAccessException e) {
			new BDException(e.getMessage());
		}
		catch (InstantiationException e){
			new BDException(e.getMessage());
		}
		return false;
	}
	
	public static boolean userExist(int id_user) throws BDException{
		// TODO Auto-generated method stub
		try {
			
				Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn=Database.getMySQLConnection();
			state=conn.createStatement();
			String query="select * from Users where id_user="+id_user+";";

			result=state.executeQuery(query);
			if(result.next()) {
				return true;}
		} 
		catch (ClassNotFoundException e) {
			new BDException(e.getMessage());
		}
		catch (IllegalAccessException e) {
			new BDException(e.getMessage());
		}
		catch (InstantiationException e){
			new BDException(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			 new BDException("user no existe "+e.getMessage());
		}
		return false;
	}

	public static void creatUser(String login, String password, String nom, String prenom) throws BDException{
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = Database.getMySQLConnection();
			state=conn.createStatement();
			//String query="  INSERT INTO users(id,login,password,nom,prenom) VALUES(NULL,\""+nom+"\" );   ";

			//Statement inst=connexion.createStatement();
			//inst.executeUpdate(query);
		    //System.out.println(inst.getResultSet());
		    ////
		    String slq="INSERT INTO Users(id_user,login,password,nom,prenom) "
		    		+ "VALUES(NULL,'"+login+"','"+password+"','"+nom+"','"+prenom+"');";
		    state.executeUpdate(slq);
		    		
		   
		   
		} 
		catch (ClassNotFoundException e) {
			new BDException(e.getMessage());
		}
		catch (IllegalAccessException e) {
			new BDException(e.getMessage());
		}
		catch (InstantiationException e){
			new BDException(e.getMessage());
		}
		catch (SQLException e1) {
			new BDException("Erreur de connexion a la bd "+e1.getMessage());
		}
		
		
	}

    

	

}