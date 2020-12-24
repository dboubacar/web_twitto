package servlets.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.com.FriendServices;
import services.com.UserServices;

public class GetFollowers extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	 	response.setContentType("json");
		PrintWriter out = response.getWriter ();
		String key=request.getParameter("key");
		int id=Integer.valueOf(request.getParameter("id"));
		JSONObject j= FriendServices.getFollowers(key, id);
		out.println(j);
			
	 }


}
