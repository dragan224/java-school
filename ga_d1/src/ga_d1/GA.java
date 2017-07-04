package ga_d1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA {
	private ArrayList<Chromosome> generation;
	private Chromosome best = null;
	static Random rand = new Random(200);
	
	
	//parametri
	Chromosome target; // cilj
	double mutation_prob; // verovatnoca mutacije
	int chromosome_count; // broj hromozoma u jednog generaciji
	int crossover_count;
	int random_count;
	
	
	
	public GA(Chromosome target, double mutation_prob, int chromosome_count, int crossover_count, int random_count) {
		super();
		this.target = target;
		this.mutation_prob = mutation_prob;
		this.chromosome_count = chromosome_count;
		this.crossover_count = crossover_count;
		this.random_count = random_count;
	}

	Chromosome randomChromosome() {
		ArrayList<Note> notes = new ArrayList<>();
		for (int j = 0; j < target.notes.size(); j++) {
			int start = rand.nextInt(Main.MAX_START);
			int len = rand.nextInt(Main.MAX_TICKS);
			if (len < 1) len = 1;
			int pitch = Main.MAX_PITCH;
			if (pitch < 12) pitch += 12;
			notes.add(new Note(start, len, pitch));
		}
		return new Chromosome(notes);
	}
	
	private void init() {
		generation = new ArrayList<>();
		for (int i = 0; i < chromosome_count; i++) {
			generation.add(new Chromosome(randomChromosome()));
		}
	}
	
	Chromosome run() {
		init();
		Collections.sort(generation, new ChromosomeComparator(target));
		int gen_cnt = 0;
		while (true) {
			gen_cnt++;
			
			ArrayList<Chromosome> next_generation = new ArrayList<>();
			
			for (int i = 0; i < chromosome_count / 2; i++) {
				next_generation.add(new Chromosome(generation.get(i)));
			}
			
			//crossover
			for (int i = 0; i < crossover_count; i++) {
				
				int parent1 = rand.nextInt(generation.size());
				int parent2 = parent1; rand.nextInt(generation.size() / 10);
				while (parent1 == parent2) {
					parent2 = rand.nextInt(generation.size());
				}
				Chromosome son = new Chromosome(generation.get(parent1));
				son.crossOver(generation.get(parent2));
				
				next_generation.add(son);
			}
			
			// Dodajemo random hromozome
			for (int i = 0; i < random_count; i++) {
				next_generation.add(randomChromosome());
			}
			
			//radimo mutaciju
			for (int i = 0; i < next_generation.size(); i++) {
				if (rand.nextDouble() < mutation_prob) {
					next_generation.get(i).mutate(mutation_prob);
				}
			}
			
			Collections.sort(next_generation, new ChromosomeComparator(target));
			
			generation.clear();

			for (int i = 0; i < chromosome_count; i++) {
				generation.add(new Chromosome(next_generation.get(i)));
			}
			next_generation.clear();
			
			if (best == null || best.score(target) < generation.get(0).score(target)) {
				best = new Chromosome(generation.get(0));
				
				boolean same = true;
				for (int i = 0; i < best.notes.size(); i++) {
					if (best.notes.get(i).pitch != target.notes.get(i).pitch) {
						same = false;
					}
					if (best.notes.get(i).start != target.notes.get(i).start) {
						same = false;
					}
					if (best.notes.get(i).len != target.notes.get(i).len) {
						same = false;
					}
				}
				if (same) break;
			}

			if (Main.display_best) {
				generation.get(0).dbg();
				System.out.println("-----");
			};
			assert(next_generation.size() == 0);
			assert(generation.size() == chromosome_count);
			System.out.println("Generacija = " + gen_cnt + " | matchovano = " + String.format("%.2f", best.match(target)/3.0) +  " | score = " + best.score(target));
		}
		System.out.println("Generacija = " + gen_cnt + " | matchovano = " + String.format("%.2f", best.match(target)/3.0) +  " | score = " + best.score(target));
		System.out.println("");
		System.out.println("Broj Generacija   = " + gen_cnt);
		return best;
	}
}
