package bd.com;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class FriendTools {
	private static Connection conn;
	private static Statement state = null;
	private static ResultSet result = null; 

	public static void addAbonement(int id_user, int id_friend) throws BDException{
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = Database.getMySQLConnection();
			state=conn.createStatement();
		    String slq="INSERT INTO Friends(id_user1,id_user2) "
		    		+ "VALUES('"+id_user+"','"+id_friend+"');";
		    state.executeUpdate(slq);
		    		
		   
		} 
		catch (SQLException e1) {
			new BDException("Erreur de connexion a la bd "+e1.getMessage());
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
	
	public static boolean checkFriend(int id_user1,int id_user2) throws BDException{
		// TODO Auto-generated method stub
		 try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
			 conn=Database.getMySQLConnection();
			 state=conn.createStatement();
			 String sql="Select * from Friends where id_user1="+id_user1+" and id_user2="+id_user2+";";
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
	//cette methode verifie si id_user a des abonements
	public static boolean checkAbonnement(int id_user) throws BDException{
		// TODO Auto-generated method stub
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn=Database.getMySQLConnection();
			state=conn.createStatement();
			String query="select id_user2 from Friends where id_user1="+id_user+";";

			result=state.executeQuery(query);
			if(result.next())
				return true;
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
			 new BDException("error de sql "+e.getMessage());
		}
		return false;
	}
	//cette methode rend une liste des identifiants suivi par id_user
	public static JSONObject mesAbonements(int id_user) throws BDException{
		// TODO Auto-generated method stub
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn=Database.getMySQLConnection();
			state=conn.createStatement();
			String q="SELECT id_user,login,nom,prenom FROM Users u,Friends f where u.id_user=f.id_user2 and f.id_user1="+id_user+";";
					
			//String query="select id_user2 from Friends where id_user1="+id_user+";";

			result=state.executeQuery(q);
			List list=new ArrayList<String>();

		
			while(result.next()) {
			    Integer idu =result.getInt("id_user");
				String login=result.getString("login");
				String nom=result.getString("nom");
				String prenom=result.getString("prenom");
				JSONObject tp = new JSONObject();
                tp.put("id_user", idu);
                tp.put("login", login);
                tp.put("nom", nom);
                tp.put("prenom", prenom);
				
				



              
				
				list.add(tp);
			}
			JSONObject obj = new JSONObject();
			
			obj.put("abonm", list);
			return obj;
		} 
		catch (ClassNotFoundException e) {
			new BDException(e.getMessage());
		}
		catch (IllegalAccessException e) {
			new BDException(e.getMessage());
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException e){
			new BDException(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			 new BDException("user no existe "+e.getMessage());
		}
		return null;
	}
	//cette methode rend une liste des identifiants qui suive id_user
	public static ArrayList<Integer>  getfollowers(int id_user) throws BDException{
			// TODO Auto-generated method stub
			try {
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();

				conn=Database.getMySQLConnection();
				state=conn.createStatement();
				String query="select id_user1 from Friends where id_user2="+id_user+";";

				result=state.executeQuery(query);
				ArrayList<Integer> listeid=new ArrayList<Integer>();
				while(result.next())
					listeid.add(result.getInt("id_user1"));
					return listeid;
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
			return null;
		}
	
	public static JSONObject mesAbones(int id_user) throws BDException{
		// TODO Auto-generated method stub
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn=Database.getMySQLConnection();
			state=conn.createStatement();
			String q="SELECT id_user,login,nom,prenom FROM Users u,Friends f where u.id_user=f.id_user1 and f.id_user2="+id_user+";";
					
			//String query="select id_user2 from Friends where id_user1="+id_user+";";

			result=state.executeQuery(q);
			List list=new ArrayList<String>();

		
			while(result.next()) {
			    Integer idu =result.getInt("id_user");
				String login=result.getString("login");
				String nom=result.getString("nom");
				String prenom=result.getString("prenom");
				JSONObject tp = new JSONObject();
                tp.put("id_user", idu);
                tp.put("login", login);
                tp.put("nom", nom);
                tp.put("prenom", prenom);
				
				



              
				
				list.add(tp);
			}
			JSONObject obj = new JSONObject();
			
			obj.put("abonnes", list);
			return obj;
		} 
		catch (ClassNotFoundException e) {
			new BDException(e.getMessage());
		}
		catch (IllegalAccessException e) {
			new BDException(e.getMessage());
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException e){
			new BDException(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			 new BDException("user no existe "+e.getMessage());
		}
		return null;
	}
	
	
	//cette methode rend une liste des identifiants suivi par id_user
	public static ArrayList<Integer>  getfollows(int id_user) throws BDException{
				// TODO Auto-generated method stub
				try {
					
					Class.forName("com.mysql.jdbc.Driver").newInstance();

					conn=Database.getMySQLConnection();
					state=conn.createStatement();
					String query="select id_user2 from Friends where id_user1="+id_user+";";

					result=state.executeQuery(query);
					ArrayList<Integer> listeid=new ArrayList<Integer>();
					while(result.next())
						listeid.add(result.getInt("id_user2"));
						return listeid;
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
				return null;
			}
		
	
	
	public static void removeFriend(int id_user1, int id_friend) throws BDException{
		// TODO Auto-generated method stub
		 try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
			 conn=Database.getMySQLConnection();
			 state=conn.createStatement();
			 String sql="delete from Friends where id_user1="+id_user1+" and id_user2="+id_friend+";";
			 state.executeUpdate(sql);
			 
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
	}

	public static JSONObject getStats(int id_user) throws BDException{
		// TODO Auto-generated method stub
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn=Database.getMySQLConnection();
			state=conn.createStatement();
			String q1="SELECT COUNT(id_user1) as nb1 FROM Friends WHERE id_user1="+id_user+";";
			String q2="SELECT COUNT(*) as nb2 FROM Friends WHERE id_user2="+id_user+";";
			String q3="SELECT * FROM Users WHERE id_user="+id_user+";";


			
			//String query="select id_user2 from Friends where id_user1="+id_user+";";
			result=state.executeQuery(q1);
			JSONObject js=new JSONObject();
			
			while(result.next()){
				int nbr = result.getInt("nb1");
					js.put("follows", nbr);
				
			}
			result=state.executeQuery(q2);
			while(result.next()){
				int nbr = result.getInt("nb2");
				js.put("followers", nbr);
				
			}
			result=state.executeQuery(q3);
			while(result.next()){
				
				js.put("login", result.getString("login"));
				js.put("nom", result.getString("nom"));
				js.put("prenom", result.getString("prenom"));

				
			}
			return js;

		} 
		catch (ClassNotFoundException e) {
			new BDException(e.getMessage());
		}
		catch (IllegalAccessException e) {
			new BDException(e.getMessage());
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (InstantiationException e){
			new BDException(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			 new BDException("user no existe "+e.getMessage());
		}
		return null;
	}

}
