package servlets.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.com.FriendServices;

public class ListeAbonnes extends HttpServlet{
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("json");
		PrintWriter out = response.getWriter ();
		String key=request.getParameter("key");
		int id=Integer.valueOf(request.getParameter("id"));
		//String key="0e1dc2a9-57e4-4e4e-9d60-ae08d7edcbb5";
		//int id=1;
		//System.out.println();
		out.println(FriendServices.listeAbones(key, id));
	 }

}
