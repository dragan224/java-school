

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ServerThread implements Runnable {
	
	private Socket sock;
	private BufferedReader in;
	private PrintWriter out;
	private int value;
	BlockingQueue<String> users;
	
	public ServerThread(Socket sock, int value, BlockingQueue<String> users){
		this.users = users;
		this.sock = sock;
		this.value = value;
		
		try {
			
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				String[] request = (in.readLine()).split(" ");
				String cmd = request[0];
				
				if (cmd.equals("LIST")) {
					StringBuffer response = new StringBuffer();
					for (String it: users) {
						response.append(it + " ");
					}
					//System.out.println(response.toString());
					out.println(response.toString());
				} else if (cmd.equals("ADD") && request.length >= 2) {
					users.add(request[1]);
					out.println("SUCCESS");
					
				} else {
					out.println("BAD REQUEST");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
