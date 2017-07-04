package ga_d2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	// Parametri GA
	static int mutation_prob    = 5;        // verovatnoca mutacije
	static int crossover_count  = 256;      // koliko puta radimo crossover
	static int random_count     = 16;       // koliko random hromozoma dodajemo u populaciju
	static int chromosome_count = 64;       // broj hromozoma u jednoj generacij
	static int time_limit_s     = 60;       // vremensko ogranicenje u sekundama
	
//	GA Fast Convergence
//	static int crossover_count  = 200*100; 
//	static int random_count     = 100; 
//	static int chromosome_count = 200;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader("ulaz.txt"));
		
		ArrayList<Integer> input = new ArrayList<>();
		
		while(in.hasNext()) {
		    input.add(in.nextInt());
		}
		int target = input.get(input.size()-1);
		input.remove(input.size()-1);
		Collections.sort(input);
		
		System.out.print("Cilj = " + target +" | Ulaz = {");
		for (int i = 0; i < input.size(); i++) {
			System.out.print(input.get(i));
			if (i != input.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
		
		new GA(
			target, 
			mutation_prob,
			chromosome_count, 
			crossover_count, 
			random_count,
			input, 
			time_limit_s).run();
	}

}
