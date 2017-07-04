package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

import fileio.ReaderWriter;

public class ServerThread implements Runnable {
	
	private Socket sock;
	private BufferedReader in;
	private PrintWriter out;
	private int value;
	ArrayList<Tuple> tokens;
	
	public ServerThread(Socket sock, ArrayList<Tuple> tokens){
		this.sock = sock;
		this.tokens = tokens;
		
		try {
			
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void register(String[] request) {
		if (request.length != 3) {
			out.println("ERROR");
			return;
		}
		String user = request[1];
		String passw = request[2];
		ArrayList<String> users = ReaderWriter.read("users.txt");
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).split(" ")[0].equalsIgnoreCase(user)) {
				out.println("ERROR");
				return;
			}
		}
		users.add(String.format("%s %s 2 0", user, passw));
		ReaderWriter.write("users.txt", users);
		out.println("OK");
	}
	
	private void login(String[] request) {
		if (request.length != 3) {
			out.println("ERROR");
			return;
		}
		String user = request[1];
		String passw = request[2];
		ArrayList<String> users = ReaderWriter.read("users.txt");
		for (int i = 0; i < users.size(); i++) {
			String[] line = users.get(i).split(" ");
			if (line[0].equalsIgnoreCase(user) && line[1].equals(passw) && line[3].equals("0")) {
				String uuid = UUID.randomUUID().toString();
				tokens.add(new Tuple(user, uuid, line[2]));
				out.println(uuid);
				out.println("OK");
				return;
			}
		}
		out.println("ERROR");
	}
	
	private void logout(String[] request) {
		if (request.length != 2) {
			out.println("ERROR");
			return;
		}
		String token = request[1];
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).token.equals(token)) {
				out.println("OK");
				tokens.remove(i);
				return;
			}
		}
		out.println("ERROR");
	}
	
	private void addoperator(String[] request) {
		if (request.length != 4 || !validate(request[3], 0))  {
			out.println("ERROR");
			return;
		}
		String user = request[1];
		String passw = request[2];
		ArrayList<String> users = ReaderWriter.read("users.txt");
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).split(" ")[0].equalsIgnoreCase(user)) {
				out.println("ERROR");
				return;
			}
		}
		users.add(String.format("%s %s 1 0", user, passw));
		ReaderWriter.write("users.txt", users);
		out.println("OK");
	}
	
	private void showusers(String[] request) {
		if (request.length != 2 || !validate(request[1], 1)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> users = ReaderWriter.read("users.txt");
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).split(" ")[2].equals("2")) {
				out.println(String.format(users.get(i).split(" ")[0]));
			}
		}
		out.println("OK");
	}
	
	private void showairports(String[] request) {
		if (request.length != 2 || !validate(request[1], 2)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> flights = ReaderWriter.read("flights.txt");
		ArrayList<String> resp = new ArrayList<>();
		for (int i = 0; i < flights.size(); i++) {
			String[] line = flights.get(i).split(" ");
			resp.add(line[1]);
			resp.add(line[2]);
		}
		Util.unique(resp);
		for (String it: resp) {
			out.println(it);
		}
		out.println("OK");
	}
	private void removeairplane(String[] request) {
		if (request.length != 3 || !validate(request[2], 1)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> airplanes = ReaderWriter.read("airplanes.txt");
		for (int i = 0; i < airplanes.size(); i++) {
			if (airplanes.get(i).split(" ")[0].equalsIgnoreCase(request[1])) {
				airplanes.remove(i);
			}
		}
		ReaderWriter.write("airplanes.txt", airplanes);
		out.println("OK");
	}
	
	private void addairplane(String[] request) {
		if (request.length != 7 || !validate(request[6], 1)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> airplanes = ReaderWriter.read("airplanes.txt");
		for (String it: airplanes) {
			if (it.split(" ")[0].equalsIgnoreCase(request[1])) {
				out.println("ERROR");
				return;
			}
		}
		airplanes.add(String.format("%s %s %s %s %s", request[1], request[2], request[3], request[4], request[5]));
		ReaderWriter.write("airplanes.txt", airplanes);
		out.println("OK");
	}
	
	private void ban(String[] request) {
		if (request.length != 3 || !validate(request[2], 1)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> users = ReaderWriter.read("users.txt");
		for (int i = 0; i < users.size(); i++) {
			String[] line = users.get(i).split(" ");
			if (line[0].equalsIgnoreCase(request[1])) {
				users.set(i, String.format("%s %s %s %s", line[0], line[1], line[2], "1"));
				ReaderWriter.write("users.txt", users);
				out.println("OK");
				return;
			}
		}
		out.println("ERROR");
	}
	
	private void search(String[] request) {
		if (request.length != 4 || !validate(request[3], 2)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> flights = ReaderWriter.read("flights.txt");
		for (int i = 0; i < flights.size(); i++) {
			String[] line = flights.get(i).split(" ");
			if (line[1].equalsIgnoreCase(request[1]) && line[2].equalsIgnoreCase(request[2])) {
				out.println(line[0]);
			}
		}
		out.println("OK");
	}
	
	private void showflight(String[] request) {
		if (request.length != 3 || !validate(request[2], 2)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> airplanes = ReaderWriter.read("airplanes.txt");
		for (int i = 0; i < airplanes.size(); i++) {
			String[] line = airplanes.get(i).split(" ");
			if (line[4].equalsIgnoreCase(request[1])) {
				out.println(String.format("%s %s %s %s %s", line[0], line[1], line[2], line[3], line[4]));
			}
		}
		out.println("OK");
	}
	
	private void showhistory(String[] request) {
		if (request.length != 3 || !validate(request[2], 2)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> history = ReaderWriter.read("history.txt");
		for (int i = 0; i < history.size(); i++) {
			String[] line = history.get(i).split(" ");
			if (line[0].equalsIgnoreCase(request[1])) {
				out.println(String.format("%s %s %s %s %s", line[0], line[1], line[2], line[3], line[4]));
			}
		}
		out.println("OK");
	}
	
	private void buy(String[] request) {
		if (request.length != 5 || !validate(request[4], 2)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> history = ReaderWriter.read("history.txt");
		String[] last = Util.findLastOf(history, request[1], request[2], request[3]);
		if (last != null && last[4].equals("1")) {
			out.println("OK");
			last[4] = "0";
			history.add(String.format("%s %s %s %s %s", last[0], last[1], last[2], last[3], last[4]));
			ReaderWriter.write("history.txt", history);
			return;
		}
		out.println("ERROR");
	}
	
	private void reserve(String[] request) {
		if (request.length != 5 || !validate(request[4], 2)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> history = ReaderWriter.read("history.txt");
		String[] last = Util.findLastOf(history, request[1], request[2], request[3]);
		if (last == null || last[4].equals("-1")) {
			out.println("OK");
			if (last == null) {
				last = new String[5];
				last[0] = Util.tokenToUser(tokens, request[4]);
				last[1] = request[1];
				last[2] = request[2];
				last[3] = request[3];
			}
			last[4] = "1";
			history.add(String.format("%s %s %s %s %s", last[0], last[1], last[2], last[3], last[4]));
			ReaderWriter.write("history.txt", history);
			return;
		}
		out.println("ERROR");
	}
	
	private void cancel(String[] request) {
		if (request.length != 5 || !validate(request[4], 2)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> history = ReaderWriter.read("history.txt");
		String[] last = Util.findLastOf(history, request[1], request[2], request[3]);
		if (last != null && last[4].equals("1")) {
			out.println("OK");
			last[4] = "-1";
			history.add(String.format("%s %s %s %s %s", last[0], last[1], last[2], last[3], last[4]));
			ReaderWriter.write("history.txt", history);
			return;
		}
		out.println("ERROR");
	}
	private void showseats(String[] request) {
		if (request.length != 3 || !validate(request[2], 2)) {
			out.println("ERROR");
			return;
		}
		ArrayList<String> airplanes = ReaderWriter.read("airplanes.txt");
		
		int row, col, spaces;
		row = col = spaces = -1;
		
		for (String it: airplanes) {
			String[] line = it.split(" ");
			if (line[0].equalsIgnoreCase(request[1])) {
				row = Integer.parseInt(line[1]);
				col = Integer.parseInt(line[2]);
				spaces = Integer.parseInt(line[3]);
			}
		}
		
		if (row == -1) {
			out.println("ERROR");
			return;
		}
		
		ArrayList<String> history = ReaderWriter.read("history.txt");
		String user = Util.tokenToUser(tokens, request[2]);
		
		for (int i = 0; i < row; i++) {
			int cnt = Integer.MAX_VALUE;
			if (spaces > 0) {
				cnt = col/(spaces+1) + (col % (spaces+1));
			}
			for (int j = 0; j < col; j++) {
				String[] last = Util.findLastOf(history, user, request[1], Integer.toString(i), Integer.toString(j));
				if (last == null || last[4].equals("-1")) {
					out.print("0");
				} else {
					out.print("1");
				}
				if (j != col-1) {
					out.print(" ");
				}
				cnt--;
				if (cnt == 0 && j != col-1) {
					out.print("2 ");
					cnt = col/(spaces+1);
				}
			}
			out.println();
		}
		out.println("OK");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				String line = in.readLine();
				System.out.println(line);
				String[] request = line.split(" ");
				String cmd = request[0];
				switch (cmd) {
				case "register":
					Lock.users_lock.lock();
					register(request);
					Lock.users_lock.unlock();
					break;
				case "login":
					Lock.users_lock.lock();
					login(request);
					Lock.users_lock.unlock();
					break;
				case "addoperator":
					Lock.users_lock.lock();
					addoperator(request);
					Lock.users_lock.unlock();
					break;
				case "logout":
					Lock.users_lock.lock();
					logout(request);
					Lock.users_lock.unlock();
					break;
				case "showusers":
					Lock.users_lock.lock();
					showusers(request);
					Lock.users_lock.unlock();
					break;
				case "removeairplane":
					Lock.airplanes_lock.lock();
					removeairplane(request);
					Lock.airplanes_lock.unlock();
					break;
				case "addairplane":
					Lock.airplanes_lock.lock();
					addairplane(request);
					Lock.airplanes_lock.unlock();
					break;
				case "ban":
					Lock.users_lock.lock();
					ban(request);
					Lock.users_lock.unlock();
					break;
				case "search":
					Lock.flights_lock.lock();
					search(request);
					Lock.flights_lock.unlock();
					break;
				case "showflight":
					Lock.airplanes_lock.lock();
					showflight(request);
					Lock.airplanes_lock.unlock();
					break;
				case "showhistory":
					Lock.history_lock.lock();
					showhistory(request);
					Lock.history_lock.unlock();
					break;
				case "reserve":
					Lock.history_lock.lock();
					reserve(request);
					Lock.history_lock.unlock();
					break;
				case "cancel":
					Lock.history_lock.lock();
					cancel(request);
					Lock.history_lock.unlock();
					break;
				case "buy":
					Lock.history_lock.lock();
					buy(request);
					Lock.history_lock.unlock();
					break;
				case "showseats":
					Lock.history_lock.lock();
					Lock.airplanes_lock.lock();
					showseats(request);
					Lock.history_lock.unlock();
					Lock.airplanes_lock.unlock();
					break;
				case "showairports":
					Lock.flights_lock.lock();
					showairports(request);
					Lock.flights_lock.unlock();
					break;
				default:
					return;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean validate(String token, int privilege) {
		for (Tuple t: tokens) {
			if (t.token.equals(token) && Integer.parseInt(t.privilege) <= privilege) {
				return true;
			}
		}
		return false;
	}

}
