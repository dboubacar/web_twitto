package bd.com;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import services.com.*;


public class CommentTools {
  
    private static  DBCollection collection;
    private static Mongo mongo;
    private static BasicDBObject bson;
    private static DB db;
    static DBCursor c;
    //Ajouter un message
	public static int addComment (int id_user, String text,int parent_id)throws BDException {
		// TODO Auto-generated method stub
		int id=1;
		try {
			mongo=new Mongo(DBStatic.mongohost,DBStatic.mongoport);
			db=mongo.getDB(DBStatic.mongodb);
			collection=db.getCollection("comments");
			bson=new BasicDBObject();
			id=CommentTools.getLastIdCom()+1;
			bson.put("_id", id);
			bson.put("id_user", id_user);
			bson.put("login",UserTools.getLogin(id_user));
			bson.put("text", text);
			
			long debut = System.currentTimeMillis(); //temps en seconde depuis 1 janvier 1970
			 Date date = new Date(debut);
		     SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yy à hh:mm",Locale.FRANCE);
		    //_date= sdf.format(date);
			bson.put("datem", sdf.format(date));
			bson.put("parent_id", parent_id);
			

			
			
			collection.insert(bson);
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			new BDException("Erreur de bd mongo "+e.getMessage());
		}
		return id;
		
		
		
	}
	//les messages
	
	public static BasicDBObject getMessages(String key,int from,int idMin,int idMax,int nbMax){
		BasicDBObject messages_b = new BasicDBObject();
		
		List<DBObject> messages=null;
		try {
			mongo=new Mongo(DBStatic.mongohost,DBStatic.mongoport);
			db=mongo.getDB(DBStatic.mongodb);
			collection=db.getCollection("comments");
			bson=new BasicDBObject();
			messages = new ArrayList<DBObject>();
			DBCursor c=null;
			int id_user;
			id_user = ConnexionTools.getIdUser(key);
			String login=UserTools.getLogin(id_user);
			/////
			if (from == id_user) {
				bson.put("id_user", id_user);
				bson.put("login", login);

			} 
			else {
				
				ArrayList<Integer> list_amis;
				
				// Home Page
				if (from==-1) {
					list_amis = FriendTools.getfollows(id_user);
					//bson.put("id_user", id_user);
					//bson.put("login",login);
					//list_auteur.add(bson);
					list_amis.add(id_user);
				} else {
					// SomeOne Page
					//String login_other_user = UserTools.getLogin(from);
					list_amis = FriendTools.getfollows(from);
					//bson.put("id_user", from);
					//bson.put("login",login_other_user);
					//list_auteur.add(bson);
					list_amis.add(from);
				}	
				bson.put("id_user",new BasicDBObject("$in",list_amis));

			}
		
			if (idMax >0) {
				BasicDBObject lt = new BasicDBObject();
				lt.put("$lt", idMax);
				bson.put("_id", lt);
				//BasicDBObject and = new BasicDBObject();
			}
			if(idMin>0) {
				BasicDBObject gt = new BasicDBObject();
				gt.put("$gt",idMin);
				bson.put("_id", gt);
			}
			
		   if(nbMax>0) {
			   
		    	c=collection.find(bson).sort(new BasicDBObject("_id",1)).limit(nbMax).sort(new BasicDBObject("_id",1));
		    }else {
		    	c=collection.find(bson).sort(new BasicDBObject("_id",1)).sort(new BasicDBObject("_id",1));
		    }
			
			//c=collection.find(bson).sort(new BasicDBObject("_id",1));			
			while(c.hasNext()) {
				DBObject o=c.next();
				messages.add(o);
			
			}
			messages_b.put("messages", messages);
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			new BDException("Erreur de bd mongo "+e.getMessage());
		}catch (BDException e) {
			// TODO Auto-generated catch block
		}
		return messages_b;
		
		
	}

	//Ajouter un Commentaire a un message
	public static void addCommentMg(int id_user,String text,int idmsg) {
		try {
			int id=CommentTools.addComment(id_user, text,idmsg);
			mongo=new Mongo(DBStatic.mongohost,DBStatic.mongoport);
			db=mongo.getDB(DBStatic.mongodb);
			collection=db.getCollection("comments");
			BasicDBObject bson1=new BasicDBObject();
			BasicDBObject bson2=new BasicDBObject();

			bson1.put("_id",idmsg);
			bson2.append("$push" , new BasicDBObject().append("comments", id));
			collection.update(bson1, bson2);


			
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			new BDException("Erreur de bd mongo "+e.getMessage());
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getIdComments(int id_user,long debut) {
		String str="";
		try {
			mongo=new Mongo(DBStatic.mongohost,DBStatic.mongoport);
			db=mongo.getDB(DBStatic.mongodb);
			collection=db.getCollection("comments");
			BasicDBObject bson=new BasicDBObject();
			bson.put("id_user", id_user);
			bson.put("debut", debut);

			DBCursor c=collection.find(bson);
			while(c.hasNext()){
				DBObject obj=c.next();
				str=  obj.get("_id").toString();
				
			}


			
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			new BDException("Erreur de bd mongo "+e.getMessage());
		}
		return str;

	}
	
	
	public static void deleteMessage(int idmsg) {
		try {
			mongo=new Mongo(DBStatic.mongohost,DBStatic.mongoport);
			db=mongo.getDB(DBStatic.mongodb);
			collection=db.getCollection("comments");
			BasicDBObject bson=new BasicDBObject();
			bson.put("_id", idmsg);
			DBCursor c=collection.find(bson);
			/*boolean com=false;
			while(c.hasNext() && !com){
				DBObject obj=c.next();
				
				if( ((ArrayList<DBObject>)obj.get("comments"))!=null) {
					com=true;
				}
			}
			if(com) {
				BasicDBObject replace = new BasicDBObject();
				replace.append("$set" , new BasicDBObject().append("text","Ce Message a été supprimer"));
				collection.update(bson, replace);
			}else {*/
				collection.remove(bson);
			//}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	//recuperer le dernier id de message
	public static int getLastIdCom(){
		int id=99;
		try {
			mongo=new Mongo(DBStatic.mongohost,DBStatic.mongoport);
			db=mongo.getDB(DBStatic.mongodb);
			collection=db.getCollection("comments");
			BasicDBObject bson=new BasicDBObject();
			DBCursor c=collection.find().sort(new BasicDBObject("_id",-1)).limit(1);
			//ObjectId i=new ObjectId("5acc883d5872a1dbe8886eac");

			while(c.hasNext()){
				DBObject obj=c.next();
				id= (Integer) obj.get("_id");	
			}
			
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			new BDException("Erreur de bd mongo "+e.getMessage());
		}
		return id;
	}
	
	
	public static int getNombreMsg(String log) {
		try {
			mongo=new Mongo(DBStatic.mongohost,DBStatic.mongoport);
			db=mongo.getDB(DBStatic.mongodb);
			collection=db.getCollection("comments");
			BasicDBObject bson=new BasicDBObject();
			bson.put("login", log);
			int nb=collection.find(bson).count();
			//ObjectId i=new ObjectId("5acc883d5872a1dbe8886eac");
			return nb;

		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			new BDException("Erreur de bd mongo "+e.getMessage());
		}
		return 0;
	}
	
	
	

	public static void creatBdDefault() {
	    
		try {
			CommentTools.addComment(1, "Travaillez,prenez de la peine: C'est le fonds qui manque le moins"
					+"Un riche Laboureur ,sentant sa mort prochaine,Fit venir ses enfants leur parla sans temoins....",-1);

			CommentTools.addComment(2,"Ne confondons pas le fait d être exigeant, avec celui d être capricieux. L exigence doit nous pousser à élever nos normes, et à nous améliorer, pour être la personnification de ce que nous exigeons.",-1);
			CommentTools.addComment(3,"Un couple devient réellement épanouie lorsque chacun accepte que son autre moitié est différent de lui et que les deux apprennent à faire avec. Autrement ça sera frustration sur frustration, embrouilles sur embrouilles",-1);
			CommentTools.addComment(4,"Si vous vous sentez con dites-vous que ça fait un an que j arrose mes cactus et je viens de me rendre compte qu ils sont en plastique",-1);
			CommentTools.addComment(5,"Just because you don t share it on social media, doesn t mean you re not up to big things. Live it and stay low key, privacy is everything.",-1);
			CommentTools.addComment(6,"Je veux juste dire à une personne ce soir que le Mois d avril est le mois de la renonciation.",-1);
			CommentTools.addComment(7, "Sans l humilité vous nêtes rien, vous penser que tout vous est dû et que vos récompenses ne sont que le fruits de vos efforts.\nDétrompez vous lorsque vous faites des efforts c est l univers tout entier qui concourt a votre récompense afin que la récolte soit digne de la semance.",-1);
		    ///commentaires
			CommentTools.addCommentMg(2, "Le travail est le seul deliberateur dans ce monde", 100);
			CommentTools.addCommentMg(3, "J'ai appris ce poeme en 1975,je l'ai recite a ll'examen du CEPE.Mon enfant de 8 ans le recite avec moi", 100);
			CommentTools.addCommentMg(4, "Merci notre pere de la Fontaine Il faut que nous aussi on deviendra sage en un jour comme vous", 100);
			CommentTools.addCommentMg(5, "Merci Jean de la Fontaine de m'avoir fait de moi un laboureure", 100);
			CommentTools.addCommentMg(7, "Le beau poeme et le beau vieux temps tres beau souvenir scolaire de 1974-1975", 100);





			//CommentTools.addComment(1,,-1);
			//CommentTools.addComment(1,,-1);


		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	    }
		
	}
	
	
}
