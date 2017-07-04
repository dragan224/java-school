package http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

	static int maxUsers = 500224;
	
	public static AtomicInteger atomicSize = new AtomicInteger(0);
	
	public static User[] users = new User[maxUsers];
	
	public static final int TCP_PORT = 8113;
	
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(TCP_PORT);
			System.out.println("Server is running...");
			int prevSize = 0;
			while(true){
				Socket sock = ss.accept();
				ServerThread st = new ServerThread(sock);
				st.start();
				/*int currSize = atomicSize.get();
				if (currSize != prevSize) {
					StringBuilder baza = new StringBuilder();
					for (int i = 0; i < currSize; i++) {
						baza.append(users[i].getUser() + " " + users[i].getPassw());
					}
				}
				/moze se bezbedno ispisati baza u tekst datoteku ovde ako je potrebno
				prevSize = currSize;*/
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
