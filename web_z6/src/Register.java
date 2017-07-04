
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webshop.User;

/**
 * Servlet implementation class FormTest
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String register(ServletContext context, String user, String passw) {
    	ArrayList<User> users = (ArrayList<User>) context.getAttribute("users");
    	
    	if (users == null) {
    		users = new ArrayList<>();
    		users.add(new User(user, passw));
    	} else {
    		for (User u: users) {
    			if (u.getUser().equals(user)) {
    				return "Neuspesna registracija - zauzet username";
    			}
    		}
    		users.add(new User(user, passw));
    	}
    	context.setAttribute("users", users);
    	
    	return "Uspesna Registracija - OK";
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String passw = request.getParameter("password");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<body><h1>Odgovor</h1>");
		out.println(register(request.getSession().getServletContext(), user, passw));
		out.println("</body>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<body><h1>Evo sta ste uneli:</h1>");
		out.println("Ime: "+ime+"<br />");
		out.println("Prezime: "+prezime+"<br />");
		out.println("</body>");
		out.close();
		
		
	}

}
