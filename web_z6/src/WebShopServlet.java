

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webshop.Product;
import webshop.Products;

/**
 * Servlet implementation class WebShopServlet
 */
@WebServlet("/WebShopServlet")
public class WebShopServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Products products;

	
	/*
	 * Obratiti paznju da se metod init() zove samo jednom, prilikom prvo pokretanja (inicijalziacije)
	 * servleta.
	 * => Ukoliko bismo u products.txt dodali novi proizvod, bez restartovanja web servera, a prethodno
	 * je servlet vec bio pokrenut, novi proizvod ne bi bio procitan.
	 */
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig cfg) throws ServletException {
		try {
			// obavezan poziv super metode, kako bi se korektno izvrsila inicijalizacija
			super.init(cfg);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		ServletContext ctx = getServletContext();
		products = new Products(ctx.getRealPath("/"));
		ctx.setAttribute("products", products);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter pout = response.getWriter();
		
		pout.println("<html>");
		pout.println("<head>");
		pout.println("</head>");
		pout.println("<body>");
		String filter;
		if (request.getSession().getAttribute("search") != null) {
			filter = (String) request.getSession().getAttribute("search") ;
		} else {
			filter = "";
		}
		
		if (request.getSession().getAttribute("logged") != null) {
			pout.println("Raspolozivi proizvodi:");
			
			pout.println("<table border=\"1\"><tr bgcolor=\"lightgrey\"><th>Naziv</th><th>Cena</th><th>&nbsp;</th></tr>");
			for ( Product p : products.values() ) {
				
				if (!p.getName().contains(filter) && !filter.equals("")) continue;
				
				pout.println("<tr>");
				pout.println("<form method=\"get\" action=\"ShoppingCartServlet\">");
				pout.println("<td>" + p.getName() + "</td>");
				pout.println("<td>" + p.getPrice() + "</td>");
				pout.println("<td>");
				pout.println("<input type=\"text\" size=\"3\" name=\"itemCount\">");
				pout.println("<input type=\"hidden\" name=\"itemId\" value=\"" + p.getId() + "\">");
				pout.println("<input type=\"submit\" value=\"Dodaj\">");
				pout.println("</form>");
				pout.println("</td>");
				pout.println("</tr>");
			}
			pout.println("</table>");

			pout.println("<p>");
			pout.println("<a href=\"ShoppingCartServlet\">Pregled sadrzaja korpe</a>");
			pout.println("</p>");
			
			pout.println("<br>");
			pout.println("<form method=\"post\" action=\"http://localhost:8080/web_z6/WebShopServlet\">");
			pout.println("<input name=\"search\"/>");	
			pout.println("<input type=\"submit\" value=\"search\"/>");
			pout.println("</form>");
			pout.println("<br>");
		} else {
			pout.println("Niste logovani!");
		}

		pout.println("</body>");
		pout.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		request.getSession().setAttribute("search", search);
		doGet(request, response);
	}

}
