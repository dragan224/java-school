package http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ServerThread extends Thread {
	
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	
	public ServerThread(Socket sock){
		this.client = sock;
		
		 try {
			//inicijalizacija ulaznog sistema
			in = new BufferedReader(
				        new InputStreamReader(
				          client.getInputStream()));
			
			//inicijalizacija izlaznog sistema
		    out = new PrintWriter(
		    	        new BufferedWriter(
		    	          new OutputStreamWriter(
		    	            client.getOutputStream())), true);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void run(){
		try {
			String komanda = in.readLine();
			String response = "";
			
			response = napraviOdogovor(komanda);
			
			//ovaj deo nam sluzi samo da bismo ispisali na konzoli servera ceo HTTP zahtev
			System.out.println("HTTP ZAHTEV KLIJENTA:\n");
			do{
				System.out.println(komanda);
				komanda = in.readLine();
			} while(!komanda.trim().equals(""));
			
			
		
			
			//treba odgovoriti browser-u po http protokolu:
			out.println(response);
			
			in.close();
			out.close();
			client.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String vrati(String response) {
		String retVal = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
		retVal += "<html><head><title>Odgovor servera</title></head>\n";
		retVal += "<body><h1>Odgovor: "+response+"</h1>\n";
		retVal += "</body></html>";
		
		System.out.println("HTTP odgovor:");
		System.out.println(retVal);
		
		return retVal;
	}
	
	int min(int a, int b) {
		return a < b ? a : b;
	}
	
	private String napraviOdogovor(String komanda){
		String retVal = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
		String vrednost = komanda.substring(komanda.indexOf("poljeForme=")+11, komanda.indexOf("HTTP")-1);
		if (komanda.indexOf("hidden1") != -1) {
			String user = komanda.substring(komanda.indexOf("poljeForme1=")+12, komanda.indexOf("&"));
			
			if (user.equals("-1")) {
				return vrati("Nevalidno korisnicko ime");
			}
			
			String passw = komanda.substring(komanda.indexOf("poljeForme2=")+12, komanda.indexOf("&", komanda.indexOf("&") + 1));
			int idx = Server.atomicSize.getAndIncrement();
			
			for (int i = 0; i < idx; i++) {
				while (Server.users[i] == null) {}
				if (Server.users[i].getUser().equals(user)) {
					Server.users[idx] = new User("-1", "-1");
					return vrati("Username vec zauzet!");
				}
			}
			
			Server.users[idx] = new User(user, passw);
			System.out.println("user = " + user + " passw=" + passw);
			return vrati("Uspesno sam dodao " + user);
		} else if (komanda.indexOf("hidden2") != -1) {
			StringBuilder sb = new StringBuilder();
			int sz = Server.atomicSize.get();
			String user = komanda.substring(komanda.indexOf("poljeForme1=")+12, komanda.indexOf("&"));
			for (int i = 0; i < sz; i++) {
				while (Server.users[i] == null) {}
				if (Server.users[i].getUser().equals("-1")) continue;
				if (Server.users[i].getUser().contains(user)) {
					sb.append(Server.users[i].getUser() + "\n");
				}
			}
			return vrati(sb.toString());
		}	
		return retVal;
	}
	
}
