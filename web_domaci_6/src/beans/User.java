package beans;

import java.util.ArrayList;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = -6365144622373103590L;

	public User() {
		username = "";
		password = "";
		loggedIn = false;
	}

	public void setUsername(String x) {
		username = x;
		System.out.println("User.username: " + x);
	}

	public void setPassword(String x) {
		password = x;
		System.out.println("User.password: " + x);
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public boolean login() {
		ArrayList<String> users = util.readFile("/Users/dragan/Documents/apache-tomcat-8.0.39/web_z7/users.txt");
		for (String it: users) {
			String[] line_split = it.split("");
			if (line_split.length >= 2 && username.equalsIgnoreCase(line_split[0]) &&
					password.equals(line_split[1])) {
				loggedIn = true;
				break;
			}
		}
		return loggedIn;
	}
	
	public void logoff() {
		loggedIn = false;
	}
	

	private String username;
	private String password;
	private boolean loggedIn;
}
