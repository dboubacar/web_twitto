package servlets.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.com.CommentServices;

public class GetMessages extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	 	response.setContentType("text/plain");
		PrintWriter out = response.getWriter ();
		String key=request.getParameter("key");
		//String key="0e1dc2a9-57e4-4e4e-9d60-ae08d7edcbb5";

		
		int from=Integer.valueOf(request.getParameter("from"));
		//int from=Integer.valueOf(request.getParameter("from"));
		//int from=-1;


		

		int idMin=Integer.valueOf(request.getParameter("idMin"));
		int idMax=Integer.valueOf(request.getParameter("idMax"));

		int nbMax=Integer.valueOf(request.getParameter("nbMax"));
		
		
		//int idMin=-1;
		//int idMax=-1;

		//int nbMax=5;

		//JSONObject j= ;
		out.println(CommentServices.getMessages(key, from, idMin, idMax, nbMax));
		
			
	 }

}
