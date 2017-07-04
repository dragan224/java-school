package server;

public class Tuple {
	public Tuple(String user, String token, String privilege) {
		super();
		this.user = user;
		this.token = token;
		this.privilege = privilege;
	}
	public String user;
	public String token;
	public String privilege;
}
