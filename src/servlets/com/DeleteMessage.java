package servlets.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.com.CommentServices;

public class DeleteMessage extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	 	response.setContentType("json");
		PrintWriter out = response.getWriter ();
		String key=request.getParameter("key");
		int idmsg=Integer.valueOf(request.getParameter("idmsg"));
		//String text="Vive l'ete !";
		//String key="0e1dc2a9-57e4-4e4e-9d60-ae08d7edcbb5";
		//int idmsg=107;
		out.println(CommentServices.deleteComment(key, idmsg));
			
	 }

}
