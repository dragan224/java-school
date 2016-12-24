package rest;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("auth_service")
public class Auth {
	@Path("login")
	@POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("email") String email, 
                                     @FormParam("password") String password) {
		
		try {
			Connection conn = DriverManager.getConnection(Constants.DB_NAME,"root","");
			String query = "SELECT * FROM Korisnici WHERE email = ? AND password = ?";
			PreparedStatement st=conn.prepareStatement(query);
	     	st.setString(1, email);
	     	st.setString(2, password);
	     	ResultSet res = st.executeQuery();
	     	if (!res.next()) return Response.status(Response.Status.UNAUTHORIZED).build();
	     	System.out.println("Login: OK!");
	     	String token = issueToken(email);
	     	return Response.ok("\"" + token + "\"").build();
	     	 
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	@Path("logout")
	@POST
	@Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Response logoutUser(@FormParam("auth") String token) {
		try {
			auth(token);
			tokens.remove(token);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	public static void auth(String token) throws Exception {
		if (!tokens.containsKey(token)) throw new Exception("Ne-postojeci token");
	}
	
	private String issueToken(String email) {
		SecureRandom random = new SecureRandom();
		String token = new BigInteger(130, random).toString(32);
		tokens.put(token, email);
		return token;
	}
	
	private static HashMap<String, String> tokens = new HashMap<>();
}
