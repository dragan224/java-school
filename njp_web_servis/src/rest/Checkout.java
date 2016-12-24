package rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("checkout")
public class Checkout {
	@Path("buy")
	@POST
    @Produces("application/json")
    public Response buy(@FormParam("auth") String token,
    					@FormParam("email") String email,
            			@FormParam("payload") String payload) {
		try {
			try {
				Auth.auth(token);
			} catch (Exception e) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
			Connection conn = DriverManager.getConnection(Constants.DB_NAME,"root","");
			String items[] = payload.split(",");
			if (items.length % 2 != 0) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			int user_id = getUserId(conn, email);
			for (int i = 0; i < items.length; i += 2) {
				int item_id = Integer.parseInt(items[i]);
				int item_ammount = Integer.parseInt(items[i+1]);
				int current_item_ammount = getItemCurrentAmmount(conn, item_id);
				updateItemTable(conn, item_id, item_ammount, current_item_ammount);
				System.out.println(item_id + " " + item_ammount);
				addToPurchaseTable(conn, user_id, item_id, item_ammount);
			}
			conn.close();
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	private int getUserId(Connection conn, String email) throws Exception {
		String query = "SELECT * FROM Korisnici WHERE email = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, email);
		ResultSet res = st.executeQuery();
		if (!res.next()) throw new Exception();
		return res.getInt("KorisniciID");
	}
	
	private void addToPurchaseTable(Connection conn, int user_id, int item_id, int item_ammount) throws SQLException {
		String query = "INSERT INTO Kupovine VALUES (?, ?, ?)";
		PreparedStatement st = conn.prepareStatement(query);
		st.setInt(1, user_id);
		st.setInt(2, item_id);
		st.setInt(3, item_ammount);
		System.out.println(st.toString());
		st.executeUpdate();
		st.close();
	}
	
	private int getItemCurrentAmmount(Connection conn, int item_id) throws Exception {
		String query = "SELECT * FROM Proizvodi WHERE ProizvodiID = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setInt(1, item_id);
		System.out.println(st.toString());
		ResultSet res = st.executeQuery();
		if (!res.next()) throw new Exception();
		return res.getInt("kolicina");
	}
	
	private void updateItemTable(Connection conn, int item_id, int bought_ammount, int item_ammount) throws Exception {
		if (bought_ammount > item_ammount) {
			throw new Exception();
		} else {
			String query = "UPDATE Proizvodi SET kolicina = ? WHERE ProizvodiID = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, item_ammount - bought_ammount);
			st.setInt(2, item_id);
			System.out.println(st.toString());
			st.executeUpdate();
		}
	}
}
