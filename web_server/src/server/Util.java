package server;

import java.util.ArrayList;
import java.util.List;

public class Util {
	public static String tokenToUser(ArrayList<Tuple> tokens, String token) {
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).token.equals(token)) {
				return tokens.get(i).user;
			}
		}
	return "ERROR";
	}
	
	public static void unique(List<String> list){
		ArrayList<String> tmp = new ArrayList<>();
		tmp.addAll(list);
	    list.clear();
	    for(String obj : tmp){
	        if(!list.contains(obj)) {
	             list.add(obj);
	        } 
	    }
	}
	
	public static String[] findLastOf(ArrayList<String> history, String user, String airplane, String row, String col) {
		for (int i = history.size() - 1; i >= 0; i--) {
			String[] line = history.get(i).split(" ");
			if ((line[0].equalsIgnoreCase(user) || user.isEmpty()) && line[2].equals(row) && line[3].equals(col) 
					&& airplane.equals(line[1])) {
				return line;
			}
		}
		return null;
	}
	
	public static String[] findLastOf(ArrayList<String> history, String airplane, String row, String col) {
		return findLastOf(history, "", airplane, row,  col);
	}
}
