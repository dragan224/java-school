

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webshop.Products;
import webshop.ShoppingCart;
import webshop.ShoppingCartItem;
import webshop.User;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String SHOPPING_CART_KEY = "ShoppingCart";
	
	// zasto ovako ne valja?
	// obratiti paznju na prirodu http protokola, koji je stateless
	// private ShoppingCart sc = new ShoppingCart();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ovako nije dobro !
		// obratiti paznju na prirodu http protokola, koji je stateless
		// ShoppingCart sc = new ShoppingCart();

		
		// pogledamo da li u tekucoj sesiji postoji objekat ShoppingCart
		HttpSession session = request.getSession();
		ShoppingCart sc = null;
		
		if (session.getAttribute("logged") != null) {
			User u = (User) session.getAttribute("logged");
			sc = u.cart;
		}
		
		if ( sc == null ) {
			assert(false); 
		}

		response.setContentType("text/html");
		
		PrintWriter pout = response.getWriter();
		
		pout.println("<html>");
		pout.println("<head>");
		pout.println("</head>");
		pout.println("<body>");
		
		if ( request.getParameter("itemId") != null ) {
			// ako smo pozvali ovaj servlet sa parametrima za dodavanje proizvoda u korpu
			try {
				Products products = (Products) getServletContext().getAttribute("products");
				// probamo da ga dodamo
				sc.addItem(products.getProduct(request.getParameter("itemId")),
						Integer.parseInt(request.getParameter("itemCount")));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		pout.println("Proizvodi u korpi:");
		pout.println("<table><tr bgcolor=\"lightgrey\"><th>Naziv</th><th>Jedinicna cena</th><th>Komada</th><th>Ukupna cena</th></tr>");
		double total = 0;
		for (ShoppingCartItem i : sc.getItems() ) {
			pout.println("<tr>");
			pout.println("<td>" + i.getProduct().getName() + "</td>");
			pout.println("<td>" + i.getProduct().getPrice() + "</td>");
			pout.println("<td>" + i.getCount() + "</td>");
			double price = i.getProduct().getPrice() * i.getCount();
			pout.println("<td>" + price + "</td>");
			pout.println("</tr>");
			total += price;
		}
		pout.println("</table>");

		pout.println("<p>");
		pout.println("Ukupno: " + total + " dinara.");
		pout.println("</p>");

		pout.println("<p>");
		pout.println("<a href=\"WebShopServlet\">Povratak</a>");
		pout.println("</p>");

		pout.println("</body>");
		pout.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
