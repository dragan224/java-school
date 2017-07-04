package mvc2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

/**
 * Servlet implementation class for Servlet: LoginServlet
 * 
 */
public class LoginServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uname = request.getParameter("username");
		String passwd = request.getParameter("password");
		String logoff = request.getParameter("logoff");
		User user = (User) session.getAttribute("user");
		if (user != null) {
			if (logoff != null && logoff.equals("true")) {
				// ako je poslat parametar za logoff, odjavimo se
				user.logoff();
				// i odemo na login stranicu
				RequestDispatcher disp = request
						.getRequestDispatcher("/login.jsp");
				// redirektovacemo na login stranicu
				disp.forward(request, response);
				return;
			}
			if (uname != null && passwd != null) {
				// user session bean postoji, uname i passwd postoje,
				// pa cemo pokusati da se prijavimo i
				// da odemo na stranicu results.jsp
				user.setUsername(uname);
				user.setPassword(passwd);
				user.login();
				// ovo je takodje legalno:
				// apsolutna putanja se u ovom slucaju tretira kao apsolutna
				// unutar konteksta.
				// RequestDispatcher disp =
				// request.getRequestDispatcher("/primer06/results.jsp");

				// ovako dobijen dispecer MORA da ima URL sa apsolutnom putanjom
				RequestDispatcher disp = getServletContext()
						.getRequestDispatcher("/results.jsp");
				// redirektovacemo na results stranicu bez obzira da li je
				// uspelo logovanje ili ne
				disp.forward(request, response);
			} else {
				// ne postoji uname ili passwd, pa idemo na login
				// redirektovacemo ga relativno u odnosu na url pozvanog
				// servleta
				RequestDispatcher disp = request
						.getRequestDispatcher("login.jsp");
				// redirektovacemo na results stranicu bez obzira da li je
				// uspelo logovanje ili ne
				disp.forward(request, response);
			}
		} else {
			// ako user ne postoji, neko je pokusao direktno da gadja ovaj
			// servlet
			
			RequestDispatcher disp = request
					.getRequestDispatcher("login.jsp");
			// redirektovacemo na login stranicu
			disp.forward(request, response);
		}
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
