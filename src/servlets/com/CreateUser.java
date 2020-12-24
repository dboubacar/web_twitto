package servlets.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.com.UserServices;

public class CreateUser extends HttpServlet{
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	 	response.setContentType("json");
		PrintWriter out = response.getWriter ();
		String login=request.getParameter("login");
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String password=request.getParameter("password");
		JSONObject j= UserServices.createUser(login, password, nom, prenom);
		out.println(j);
		
			
	 }

}
