package servlets.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.com.UserServices;

public class LoginUser extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	 	response.setContentType("json");
		PrintWriter out = response.getWriter ();
		String login=request.getParameter("login");
		String password=request.getParameter("password");
		JSONObject j= UserServices.logIn(login, password);
		out.println(j);
		
			
	 }

}
