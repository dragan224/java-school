package rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import entities.Proizvod;

@Path("webshop")
public class WebShop {
	@Path("list")
	@POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public List<Proizvod> listItems(@FormParam("auth") String token) {
		try {
			Auth.auth(token);
			Connection conn = DriverManager.getConnection(Constants.DB_NAME, "root", "");
			String query = "SELECT * FROM Proizvodi WHERE kolicina > 0";
			PreparedStatement st=conn.prepareStatement(query);
	     	ResultSet res = st.executeQuery();
	     	ArrayList<Proizvod> proizvodi = new ArrayList<>();
	     	while (res.next()) {
	     		Proizvod proizvod = new Proizvod(res.getString("ProizvodiID"),
	     								res.getString("Ime"),
	     								res.getString("Kolicina"));
	     		proizvodi.add(proizvod);
	     	}
	     	res.close();
	     	conn.close();
	     	return proizvodi;
	     	 
		} catch (Exception e) {
			return null;
		}
	}

}
