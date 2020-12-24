package services.com;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import bd.com.BDException;
import bd.com.Comment;
import bd.com.CommentTools;
import bd.com.ConnexionTools;
import bd.com.FriendTools;
import bd.com.UserTools;
public class CommentServices {
	public static JSONObject  addComment(String key,String text,int parent_id){
		//verifier les parmatres
		try{
			if(!ConnexionTools.isConnect(key) || text==null || parent_id==0)
				return ErrorJSON.serviceRefused("rep", -1);
			//recuperer le login
			
			int  id_user=ConnexionTools.getIdUser(key);
			if(parent_id==-1) {
		     CommentTools.addComment(id_user,text,parent_id);
			}else {
			  CommentTools.addCommentMg(id_user,text,parent_id);

			}
			
			return ErrorJSON.serviceAccepted();
			
			//return ErrorJSON.serviceAccepted();
			
		
		}catch(BDException e){
		return ErrorJSON.serviceRefused("error depuis la bd", 1000);
	    }
		 
		
	}
	public static JSONObject  deleteComment(String key,int idmsg){
		//verifier les parmatres
		try{
			if(!ConnexionTools.isConnect(key) || idmsg==0)
				return ErrorJSON.serviceRefused("req", -1);
			//recuperer le login
			int  id_user=ConnexionTools.getIdUser(key);
			CommentTools.deleteMessage(idmsg);
			return ErrorJSON.serviceAccepted();
			//return ErrorJSON.serviceAccepted();
		}catch(BDException e){
			return ErrorJSON.serviceRefused("error depuis la bd", 1000);
	    }
		 
	}
	
	public static BasicDBObject  getMessages(String key,int from,int idMin,int idMax,int nbMax) {
		/*if(!ConnexionTools.isConnect(key) )
			return ErrorJSON.serviceRefused("Parametres incorrects", -1);
		//recuperer le login*/
		JSONObject j= new JSONObject();
		
		BasicDBObject messages_b = new BasicDBObject();

		messages_b =CommentTools.getMessages(key, from, idMin, idMax, nbMax);
		
		return messages_b;
		
	}

 
}
