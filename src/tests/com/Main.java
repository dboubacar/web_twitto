package tests.com;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.json.JSONException;
import org.json.JSONObject;

import bd.com.BDException;
import bd.com.Comment;
import bd.com.CommentTools;
import bd.com.ConnexionTools;
import bd.com.Database;
import bd.com.FriendTools;
import bd.com.UserTools;
import services.com.CommentServices;
import services.com.FriendServices;
import services.com.UserServices;

public class Main {
	public static void main(String[] args)  {
		Connection connexion;
			
	/*try {
	connexion = Database.getMySQLConnection();
		
		
		String nom="jean";
		String query="INSERT INTO user(id,nom) VALUES(NULL,\""+nom+"\");";
		Statement inst=connexion.createStatement();
		inst.executeUpdate(query);
		
	    System.out.println(inst.getResultSet());
	} 
	catch (SQLException e1) {e1.printStackTrace();}
	}  */
		
		/*JSONObject j=UserServices.createUser("jean", "123a","Ba", "ali");	
		try {
			String st=j.getString("code");
			System.out.println(st);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	    //String k="5ec7012c-67b6-4943-818f-28ba57884346";
		//JSONObject j=FriendServices.removeFriend(k,6);
		//System.out.println(j);
	  /*  ArrayList<Integer> a=new ArrayList<Integer>();
	    a.add(100);
	    a.add(101);
	    a.add(102);
	   ArrayList<String> l =CommentTools.timeline(12, a);
	   System.out.println(l.toString());*/
		/*long debut = System.currentTimeMillis();
		 Date date = new Date(debut);
	     SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy à h:mm",Locale.FRANCE);
	     String f= sdf.format(date);
	     System.out.println(f);*/
		
			/*try {
					for(int i=1;i<10;i++) {
						CommentTools.addComment(i, "Bonne journee "+i);
						Thread.sleep(100);
					}
			  }
			catch (InterruptedException e) {
			}
					catch (BDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
					}
			System.out.println("ok");*/
		//JSONObject j=CommentServices.timeline(1);
		//System.out.println(j);
		//UserServices.createUser("fatou1", "123", "barry", "binete");
		//JSONObject j=UserServices.logIn("fatou1", "123");
		//String k="7733051b-aaad-461b-95fd-5baf5a3915b7";
		//JSONObject j=CommentServices.addComment(k, "Bonsoir les amis");
		//System.out.println(j);
		//JSONObject j=UserServices.logIn("sidi1","123s");
		

			//System.out.println(j);
		//CommentTools.creatBdDefault();
		//CommentTools.addCommentMg();
		//System.out.println(CommentTools.getLastIdCom());
      // CommentTools.addCommentMg(2, "Merci !", 100);
       // CommentTools.addCommentMg(3, "C est gentil !", 100);
       // CommentTools.addCommentMg(5, "C est tres sympa !", 100);

		String key="f4e6ab0c-0c42-4fb1-9dac-986c852cfdce";
		//List<Comment> lk=CommentTools.getMessages(key,-1,-1,-1,2);
		//int lk=0;
		//System.out.println(lk);
		/*GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.add(Calendar.HOUR, -1);
		Date ph = calendar.getTime();
		System.out.println(ph);*/
		
		/*JSONObject js=new JSONObject();
		try {
			js.put("key", 12);
			System.out.println(js.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	//	CommentTools.deleteMessage(100);
		
		/*try {
			System.out.println(FriendTools.getStats(1));
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println(FriendServices.getFollowers(key, 1));
		//System.out.println(CommentTools.getMessages(key, -1, -1, -1, 2000));
		//System.out.println(FriendServices.listeAbones(key, 1));
       // System.out.println(UserServices.userExist("sidi11"));
		/*try {
			UserTools.creatUser("samy", "123s", "Samy", "Keznadji");
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	/*	try {
			UserTools.creatUser("idrissa1", "123i", "bah", "idrissa");
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
		

	
	
	

}

