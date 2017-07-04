package fileio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ReaderWriter {
	public static ArrayList<String> read(String filename) {
		ArrayList<String> res = new ArrayList<>();
		Scanner in;
		try {
			in = new Scanner(new FileReader(filename));
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line.trim().isEmpty()) continue;
				res.add(line.trim());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Integer> input = new ArrayList<>();
		return res;
	}
	
	public static void write(String filename, ArrayList<String> data) {
		File fout = new File(filename);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			for (String it: data) {
				bw.write(it);
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
