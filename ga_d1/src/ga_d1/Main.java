package ga_d1;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	//Input/Output parametri
	static boolean use_stdin = false; //promeniti u true, da bi se kanal, broj nota itd unosio sa standardnog unosa.
	static boolean display_best = false; //ispisuje najbolji hromozom iz svake generacije
	
	// Ogranicenja za note 
	static int  MAX_START = 50000;
	static int	MAX_TICKS = 500;
	static int	MAX_PITCH = 127;
	
	//parametri GA
	static double mutation_prob = 0.05; // verovatnoca mutacije
	static int crossover_count = 200*100; // koliko puta radimo crossover
	static int random_count = 100; // koliko random hromozoma dodajemo u svakoj generaciji
	static int chromosome_count = 200; // broj hromozoma u jednoj generacij

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int track = 1;
			int channel = 0;
			int note_count = 20;
			
			if (use_stdin) {
				Scanner s = new Scanner(System.in);
				
				track = s.nextInt();
				channel = s.nextInt();
				note_count = s.nextInt();
				
				s.close();
			}
			
			Midi midi = new Midi("midi/test.mid");
			
			Chromosome target = new Chromosome(midi.parse(track, channel, note_count));

			Chromosome best = new GA(
					target, 
					mutation_prob, 
					chromosome_count,
					crossover_count,
					random_count).run();
			
			System.out.println("Cilj              = " + target);
			System.out.println("Najbolji hromozom = " + best);
			
			/*
			System.out.println("Debug output");
			target.dbg();
			System.out.println("-----");
			best.dbg();*/
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			System.out.println(sw.toString());
		}
	}

}
