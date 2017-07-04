package beans;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class util {
	public static ArrayList<String> readFile(String path) {
		try {
			return (ArrayList<String>) Files.readAllLines(Paths.get(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
}
