
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;

/**
 * Servlet implementation class FormTest
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static String listUsers(ServletContext context, String pattern) {
    	ArrayList<User> users = (ArrayList<User>) context.getAttribute("users");
    	StringBuilder res = new StringBuilder();
    	
    	if (users != null) {
    		for (User u: users) {
        		if (pattern.isEmpty() || u.getUser().contains(pattern)) {
        			res.append(u.getUser());
        			res.append("<form style='display:inline;' method=\"get\" action=\"http://localhost:8080/web_z5/login\">");
        			res.append("<input hidden name=\"user\" value=" + "\"" + u.getUser() +"\"/>");	
        			res.append("<input type=\"submit\" value=\"delete\"/>");
        			res.append("</form>");
        			res.append("<br>");
        		} 
    			
    		}
    	}
    	
    	
    	res.append("<br>");
    	res.append("<form method=\"get\" action=\"http://localhost:8080/web_z5/search\">");
		res.append("<input name=\"search\"/>");	
		res.append("<input type=\"submit\" value=\"search\"/>");
		res.append("</form>");
		res.append("<br>");
    	
    	return res.toString();
    }
    
    private boolean login(ServletContext context, String user, String passw) {
    	ArrayList<User> users = (ArrayList<User>) context.getAttribute("users");
    	
    	if (users == null) return false;
    	
    	for (User u: users) {
			if (u.getUser().equals(user) && u.getPassw().equals(passw)) {
				return true;
			}
		}
    	
    	return false;
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String passw = request.getParameter("password");
		
		ServletContext context = request.getSession().getServletContext();
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<body><h1>Odgovor</h1>");
		
		if (login(context, user, passw)) {
			request.getSession().setAttribute("logged", user);
			out.println(listUsers(context, ""));
		} else {
			out.println("Neuspesno logovanje - proverite podatke");
		}
		
		out.println("</body>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		String user = request.getParameter("user");
		
		ServletContext context = request.getSession().getServletContext();
		
		ArrayList<User> users = (ArrayList<User>) context.getAttribute("users");
    	
		//System.out.println(user + "###");
		String logged = (String) request.getSession().getAttribute("logged");
		
    	if (users != null && logged != null && !logged.equals(user)) {
    		for (User u: users) {
    			//System.out.println(u.getUser() + "###");
        		if (u.getUser().equals(user)) {
        			users.remove(u);
        			break;
        		} 
    		}
    		context.setAttribute("users", users);
    	}
    	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<body><h1>Odgovor</h1>");
		out.println(listUsers(context, ""));
		out.println("</body>");
		out.close();
	}

}
