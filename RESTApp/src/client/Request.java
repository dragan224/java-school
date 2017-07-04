package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Request {
	private static final int PORT = 8976;
	
	private static String send(String cmd, String data) {
		try {
			InetAddress addr = InetAddress.getByName("127.0.0.1");
			Socket sock = new Socket(addr, PORT);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
			
			out.println(String.format("%s %s", cmd, data));
			
			StringBuilder response = new StringBuilder();
			while (true) {
				String line = in.readLine().trim();
				if (!line.equals("OK")) {
					response.append(line + "\n");
				}
				if (line.equals("ERROR") || line.equals("OK")) {
					break;
				}
			}
			
			out.println("END");
			
			if (response.toString().isEmpty()) return "OK";
			
			return response.toString().substring(0, response.toString().length()-1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
	}
	
	static public String logout(String token) {
		return send("logout", String.format("%s", token));
	}
	
	static public String login(String user, String pass) {
		return send("login", String.format("%s %s", user, pass));
	}
	
	static public String register(String user, String pass) {
		return send("register", String.format("%s %s", user, pass));
	}
	
	static public String addoperator(String user, String pass, String token) {
	  return send("addoperator", String.format("%s %s %s", user, pass, token));
	}
		    
	static public String addairplane(String name, String row, String col, String space, String flight, String token) {
	  return send("addairplane", String.format("%s %s %s %s %s %s", name, row, col, space, flight, token));
	}

	static public String removeairplane(String name, String token) {
	  return send("removeairplane", String.format("%s %s", name, token));
	}

	static public String ban(String user, String token) {
	  return send("ban", String.format("%s %s", user, token));
	}

	static public String showusers(String token) {
	  return send("showusers", String.format("%s", token));
	}

	static public String search(String source, String dest, String token) {
	  return send("search", String.format("%s %s %s", source, dest, token));
	}

	static public String reserve(String airplane, String row, String col, String token) {
	  return send("reserve", String.format("%s %s %s %s", airplane, row, col, token));
	}

	static public String cancel(String airplane, String row, String col, String token) {
	  return send("cancel", String.format("%s %s %s %s", airplane, row, col, token));
	}

	static public String buy(String airplane, String row, String col, String token) {
	  return send("buy", String.format("%s %s %s %s", airplane, row, col, token));
	}

	static public String showflight(String flight, String token) {
	  return send("showflight", String.format("%s %s", flight, token));
	}

	static public String showhistory(String user, String token) {
	  return send("showhistory", String.format("%s %s", user, token));
	}

	static public String showseats(String airplane, String token) {
	  return send("showseats", String.format("%s %s", airplane, token));
	}
	
	static public String showairports( String token) {
		  return send("showairports", String.format("%s", token));
		}
}
