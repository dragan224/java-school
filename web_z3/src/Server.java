


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
	
	public static final int PORT = 8988;
	private static BlockingQueue<String> users = new LinkedBlockingQueue<>();
	
	public static void main(String[] args) {
		try {
			int clientNumber = 0;
			ServerSocket ss = new ServerSocket(PORT);
			System.out.println("Server is running...");
			
			while(true){
				Socket client = ss.accept();
				System.out.println("Accepted client number "+(++clientNumber));
				
				new Thread(new ServerThread(client, clientNumber, users)).start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
