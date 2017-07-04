
package http;

public class User {
	String user;
	String passw;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassw() {
		return passw;
	}
	public void setPassw(String passw) {
		this.passw = passw;
	}
	public User(String user, String passw) {
		super();
		this.user = user;
		this.passw = passw;
	}
	
}
