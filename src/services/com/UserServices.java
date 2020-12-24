package services.com;
import bd.com.BDException;
import bd.com.CommentTools;
import bd.com.ConnexionTools;
import bd.com.FriendTools;
import bd.com.UserTools;

import org.json.JSONException;
import org.json.JSONObject;


public class UserServices {
	
	public static JSONObject logIn(String login,String password)  {
		//1-verifier les parametres
		if(login==null || password==null)
			return ErrorJSON.serviceRefused("codError", -1);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//2-verifier que le couple (login,password) est ok dans bd
			boolean password_ok=UserTools.checkPassword(login,password);
			if(!password_ok)
			return ErrorJSON.serviceRefused("codError", 1);
			//3-recuperer l'id de user
			int id_user=UserTools.getIdUser(login);
			JSONObject js=new JSONObject();
			
			//inserer une nouvelle session dans le bd
			String key=ConnexionTools.insertSession(id_user,-1);
			js.put("key",key);
			js.put("id_user", id_user);
			js.put("login", login);
			return js;
		}catch (JSONException e) {
			// probleme de json envoie code 100
			return ErrorJSON.serviceRefused("Probleme de Json", 100);

		}catch(BDException e) {
			return ErrorJSON.serviceRefused("Probleme de generation de key", 1000);
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
	
	public static JSONObject logOut(String key) {
		//1-verifier les parametres
		if(key==null)
			return ErrorJSON.serviceRefused("Parametres incorrects", -1);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		//verifier  si key est connecte
		if(!ConnexionTools.isConnect(key)) 
			return ErrorJSON.serviceRefused("user non connecter", -1);
		ConnexionTools.removeKey(key);
		return ErrorJSON.serviceAccepted();
		}catch(BDException e) {
			//impossible 
			return ErrorJSON.serviceRefused(e.getMessage(),1000);
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
	
	public static JSONObject createUser(String login, String password, String nom, String prenom)  {
		//1-verification des parmatres
		if(login==null || password==null || nom==null || prenom==null) 
			return ErrorJSON.serviceRefused("Parametres incorrect", -1);
		//verifier si login n'existe pas
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			if(UserTools.userExist(login))
				return ErrorJSON.serviceRefused("user existe", -1);
			UserTools.creatUser(login,password,nom,prenom);
			return ErrorJSON.serviceAccepted();
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
		catch(BDException e) {
			//impossible 
			return ErrorJSON.serviceRefused("impossible creer user :"+e.getMessage(), 1000);
		}
		return ErrorJSON.serviceRefused("Erreur de connexion de bdd", -1);
		
		
			
}

	public static JSONObject removeFriend(String key,int id_friend) {
		//1-verifier les parametres
		if(key==null || id_friend==0)
			return ErrorJSON.serviceRefused("Parametres incorrects", -1);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		//verifier  si key est connecte
		if(!ConnexionTools.isConnect(key)) 
			return ErrorJSON.serviceRefused("user non connecter", -1);
		
		int  id_user=ConnexionTools.getIdUser(key);
		FriendTools.removeFriend(id_user, id_friend);
		return ErrorJSON.serviceAccepted();
		}catch(BDException e) {
			//impossible 
			return ErrorJSON.serviceRefused(e.getMessage(),1000);
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
	
	public static JSONObject addFriend(String key,int id_friend) {
		//1-verifier les parametres
		if(key==null || id_friend==0)
			return ErrorJSON.serviceRefused("Parametres incorrects", -1);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		//verifier  si key est connecte
		if(!ConnexionTools.isConnect(key)) 
			return ErrorJSON.serviceRefused("user non connecter", -1);
		
		int  id_user=ConnexionTools.getIdUser(key);
		FriendTools.addAbonement(id_user, id_friend);
		return ErrorJSON.serviceAccepted();
		}catch(BDException e) {
			//impossible 
			return ErrorJSON.serviceRefused(e.getMessage(),1000);
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
	
	public static JSONObject getStats(String key,int id) {
		//1-verifier les parametres
		if(key==null || id==0)
			return ErrorJSON.serviceRefused("Parametres incorrects", -1);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		//verifier  si key est connecte
		if(!ConnexionTools.isConnect(key)) 
			return ErrorJSON.serviceRefused("user non connecter", -1);
		JSONObject jm=new JSONObject();
		JSONObject j=new JSONObject();

		String login=UserTools.getLogin(id);
		int ntwt=CommentTools.getNombreMsg(login);
	 	jm=FriendTools.getStats(id);
			j.put("ntweets", ntwt);
			j.put("friend", jm);
			return j;
		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(BDException e) {
			//impossible 
			return ErrorJSON.serviceRefused(e.getMessage(),1000);
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
     
	public static JSONObject userExist(String login) {
		//1-verifier les parametres
		if(login==null)
			return ErrorJSON.serviceRefused("req", -1);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		//verifier  si key est connecte
		if(UserTools.userExist(login))
			return ErrorJSON.serviceAccepted();
		return ErrorJSON.serviceRefused("req", -1);

		}catch(BDException e) {
			//impossible 
			return ErrorJSON.serviceRefused(e.getMessage(),1000);
		}catch (ClassNotFoundException e) {
			new BDException(e.getMessage());
		}
		catch (IllegalAccessException e) {
			new BDException(e.getMessage());
		}
		catch (InstantiationException e){
			new BDException(e.getMessage());
		}
		return ErrorJSON.serviceRefused("req", -1);
		
	}

}