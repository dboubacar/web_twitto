package servlets.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.com.CommentServices;
import services.com.UserServices;

public class AddComment extends HttpServlet{
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	 	response.setContentType("json");
		PrintWriter out = response.getWriter ();
		String key=request.getParameter("key");
		String text=request.getParameter("text");
		int parent=Integer.valueOf(request.getParameter("parent_id"));
		//String text="Vive l'ete !";
		//String key="0e1dc2a9-57e4-4e4e-9d60-ae08d7edcbb5";
	
		
		out.println(CommentServices.addComment(key,text,parent));
		
			
	 }

}
