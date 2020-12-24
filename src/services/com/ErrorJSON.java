package services.com;


import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {
	
	public static JSONObject serviceRefused(String des,int error){
		JSONObject js=new JSONObject();
		try {
			js.put(des,error);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js;
		
	}
	public static JSONObject serviceAccepted(){
		JSONObject js=new JSONObject();
		try {
			js.put("req",1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js;
		
		
		
	}

}