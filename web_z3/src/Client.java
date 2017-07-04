


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

public class Client {
	
	public static final int PORT = 8988;
	
	public static void main(String[] args) {
		
		try {
			InetAddress addr = InetAddress.getByName("127.0.0.1");
			Socket sock = new Socket(addr, PORT);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
			
			//posalji zahtev
			Scanner s = new Scanner(System.in);
			
			while (true) {
				out.println(s.nextLine());
				System.out.println(in.readLine());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
