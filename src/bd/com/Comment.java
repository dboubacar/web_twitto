package bd.com;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class Comment {
	private String login;
	private int _id;
	private String text;
	private List listcom;
	private String _date;
	private int parent;
	public Comment(int _id,String login, String text,Long debut,List<Integer > l,int parent) {
		this._id=_id;
		this.login = login;
		this.text = text;
		//la date
		 Date date = new Date(debut);
	     SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy à h:mm",Locale.FRANCE);
	    _date= sdf.format(date);
		if(parent==0) {
			parent=-1;
		}
		this.parent=parent;
		if(l==null)
		listcom=new ArrayList<Integer>();
		else
			listcom=l;
	}
	
	

	



	@Override
	public String toString() {
		JSONObject js=new JSONObject();
		try {
			js.put("key", 12);
			System.out.println(js.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js.toString();
			   
	}
	






	public void ajoutComment(Integer c) {
		listcom.add(c);
	}
	
	

	

}
