package servlets.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.com.CommentServices;
import services.com.FriendServices;

public class AddAbonnement extends HttpServlet{
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	 	response.setContentType("text/plain");
		PrintWriter out = response.getWriter ();
		String key=request.getParameter("key");
		int id_ab=Integer.valueOf(request.getParameter("id_friend"));
		JSONObject j= FriendServices.addFriend(key, id_ab);
		out.println(j);
		
			
	 }

}
