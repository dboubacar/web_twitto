package services.com;


import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import bd.com.BDException;
import bd.com.ConnexionTools;
import bd.com.FriendTools;
import bd.com.UserTools;

public class FriendServices {
	
	public static JSONObject addFriend(String key,int id_friend){
		if(key==null || id_friend==0)
			return ErrorJSON.serviceRefused("Parametres incorrect",-1);
		try{
			
			if(!ConnexionTools.isConnect(key)||! UserTools.userExist(id_friend))
				return ErrorJSON.serviceRefused("Pas connecter",-1);
			
			
			
			int id_user1=ConnexionTools.getIdUser(key);
			FriendTools.addAbonement(id_user1,id_friend);
			return ErrorJSON.serviceAccepted();
		} catch(BDException e) {
			return ErrorJSON.serviceRefused("Probleme de generation de key", 1000);
		
		}
		
		
	}
	
	public static JSONObject removeFriend(String key,int id_friend){
		if(key==null || id_friend==0)
			return ErrorJSON.serviceRefused("Parametres incorrect",-1);
		try{
			if(!ConnexionTools.isConnect(key)||! UserTools.userExist(id_friend))
				return ErrorJSON.serviceRefused("Pas connecter",-1);
			
			int id_user1=ConnexionTools.getIdUser(key);
			if(!FriendTools.checkFriend(id_user1,id_friend))
				return ErrorJSON.serviceRefused("Pas abonne",-1);
		
			FriendTools.removeFriend(id_user1,id_friend);
			return ErrorJSON.serviceAccepted();
		} catch(BDException e) {
			return ErrorJSON.serviceRefused("Probleme de generation de key", 1000);
		
		}
		
	}
	
	public static JSONObject listeAbom(String key,int id){
		if(key==null || id==0)
			return ErrorJSON.serviceRefused("Parametres incorrect",-1);
		try{
			if(!ConnexionTools.isConnect(key)||! UserTools.userExist(id))
				return ErrorJSON.serviceRefused("Pas connecter",-1);
		     
			return FriendTools.mesAbonements(id);
			
			
		} catch(BDException e) {
			return ErrorJSON.serviceRefused("Probleme de generation de key", 1000);
		}
		
	}
	
	public static JSONObject listeAbones(String key,int id){
		if(key==null || id==0)
			return ErrorJSON.serviceRefused("Parametres incorrect",-1);
		try{
			if(!ConnexionTools.isConnect(key)||! UserTools.userExist(id))
				return ErrorJSON.serviceRefused("Pas connecter",-1);
		     
			return FriendTools.mesAbones(id);
			
			
		} catch(BDException e) {
			return ErrorJSON.serviceRefused("Probleme de generation de key", 1000);
		}
		
	}

	public static JSONObject getFollows(String key,int id){
		if(key==null|| id==0)
			return ErrorJSON.serviceRefused("Parametres incorrect",-1);
		try{
			if(!ConnexionTools.isConnect(key))
				return ErrorJSON.serviceRefused("Pas connecter",-1);
			// int id=ConnexionTools.getIdUser(key);
		     JSONObject js=new JSONObject();
			 js.put("follows", FriendTools.getfollows(id));
			
			return js;
			
			
		} catch(BDException e) {
			return ErrorJSON.serviceRefused("Probleme de generation de key", 1000);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ErrorJSON.serviceRefused("Pas connecter",-1);
		
	}
	
	public static JSONObject getFollowers(String key,int id){
		if(key==null || id==0)
			return ErrorJSON.serviceRefused("Parametres incorrect",-1);
		try{
			if(!ConnexionTools.isConnect(key))
				return ErrorJSON.serviceRefused("Pas connecter",-1);
			// int id=ConnexionTools.getIdUser(key);
		     JSONObject js=new JSONObject();
			 js.put("followers", FriendTools.getfollowers(id));
			
			return js;
			
			
		} catch(BDException e) {
			return ErrorJSON.serviceRefused("Probleme de generation de key", 1000);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ErrorJSON.serviceRefused("Pas connecter",-1);
		
	}

}
