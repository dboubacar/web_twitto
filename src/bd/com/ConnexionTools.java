package bd.com;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class ConnexionTools {
	private static Connection conn;
	private static Statement state = null;
	private static ResultSet result = null; 


	
	public static int getIdUser(String key) throws BDException{
		// TODO Auto-generated method stub
		 
		
		 try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
			 conn = Database.getMySQLConnection();
			 state= conn.createStatement();
				// Exécution de la requête
			 result= state.executeQuery("SELECT * FROM Connexions where cle=\""+key+"\";");
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

	public static String insertSession(int id_user,int root) throws BDException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = Database.getMySQLConnection();
			state=conn.createStatement();
			String key=   UUID.randomUUID().toString();
			long debut = System.currentTimeMillis()/1000; //temps en seconde depuis 1 janvier 1970
			
			//System.out.println(debut);
			String sql="insert into Connexions(cle,id_user,debut,root) "
					+ "values('"+key+"',"+id_user+",null,"+root+");";
			state.executeUpdate(sql);
			return key;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new BDException("erreur de bd "+e.getMessage());
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

	public static boolean isConnect(String key) throws BDException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = Database.getMySQLConnection();
			state=conn.createStatement();
			String sql="select * from Connexions where cle=\""+key+"\";";
			result=state.executeQuery(sql);
			if(result.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new BDException("erreur de bd "+e.getMessage());
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

	public static void removeKey(String key) throws BDException{
		// TODO Auto-generated method stub
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn = Database.getMySQLConnection();
					state=conn.createStatement();
					String sql="delete from Connexions where cle=\""+key+"\";";
					state.executeUpdate(sql);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					new BDException("erreur de bd "+e.getMessage());
				}catch (ClassNotFoundException e) {
					new BDException(e.getMessage());
				}
				catch (IllegalAccessException e) {
					new BDException(e.getMessage());
				}
				catch (InstantiationException e){
					new BDException(e.getMessage());
				}
				
		
		
	}
	public static void deconnexionAutomatique() throws BDException{
		// TODO Auto-generated method stub
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn = Database.getMySQLConnection();
					state=conn.createStatement();
					String sql="select * from Connexions;";
					result=state.executeQuery(sql);
					if(!result.next())
						return ;
					long fin = System.currentTimeMillis()/1000; //temps en seconde depuis 1 janvier 1970
					
					String sql1="delete from Connexions where "+fin+"-debut>100 and root=-1;";
					state.executeUpdate(sql);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					new BDException("erreur de bd "+e.getMessage());
				}catch (ClassNotFoundException e) {
					new BDException(e.getMessage());
				}
				catch (IllegalAccessException e) {
					new BDException(e.getMessage());
				}
				catch (InstantiationException e){
					new BDException(e.getMessage());
				}
				
		
		
	}


}
