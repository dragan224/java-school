package ga_d2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA {
	private ArrayList<Chromosome> generation;
	private Chromosome best = null;
	
	//parametri
	static double eps = 1e-9; // poredenje sa ciljem (relativna greska)
	
	int target; // cilj
	int mutation_prob; // verovatnoca mutacije
	int chromosome_count; // broj hromozoma u jednog generaciji
	int crossover_count;
	int random_count;
	ArrayList<Integer> input = new ArrayList<>();
	int time_limit_s;

	public GA(int target, int mutation_prob, int chromosome_count, int crossover_count, int random_count,
			ArrayList<Integer> input, int time_limit_s) {
		super();
		this.target = target;
		this.mutation_prob = mutation_prob;
		this.chromosome_count = chromosome_count;
		this.crossover_count = crossover_count;
		this.random_count = random_count;
		this.input = input;
		this.time_limit_s = time_limit_s;
	}

	private void init() {
		generation = new ArrayList<>();
		for (int i = 0; i < chromosome_count; i++) {
			generation.add(RNG.nextChromosome(
					input,  RNG.nextInt(input.size())+1));
		}
	}
	
	void run() {
		init();
		Collections.sort(generation, new ChromosomeComparator(target));
		int gen_cnt = 0;
		
		long startTime = System.currentTimeMillis();
		long time_limit_ms = time_limit_s * 1000L;
		while (System.currentTimeMillis() - startTime < time_limit_ms) {
			gen_cnt++;
			
			ArrayList<Chromosome> next_generation = new ArrayList<>();
			
			for (int i = 0; i < chromosome_count / 2; i++) {
				next_generation.add(new Chromosome(generation.get(i)));
			}
			
			//crossover
			for (int i = 0; i < crossover_count; i++) {
				
				int parent1 = RNG.nextInt(generation.size());
				int parent2 = parent1; RNG.nextInt(generation.size() / 10);
				while (parent1 == parent2) {
					parent2 = RNG.nextInt(generation.size());
				}
				Chromosome son = new Chromosome(generation.get(parent1));
				son.crossover(generation.get(parent2), 
						     RNG.nextInt(input.size())+1);
				
				next_generation.add(son);
			}
			
			// Dodajemo random hromozome
			for (int i = 0; i < random_count; i++) {
				next_generation.add(RNG.nextChromosome(
						input, RNG.nextInt(input.size())+1));
			}
			
			//radimo mutaciju
			for (int i = 0; i < next_generation.size(); i++) {
				next_generation.get(i).mutate(mutation_prob);
			}
			
			Collections.sort(next_generation, new ChromosomeComparator(target));
			
			generation.clear();

			for (int i = 0; i < chromosome_count; i++) {
				generation.add(new Chromosome(next_generation.get(i)));
			}
			next_generation.clear();
			
			if (best == null || best.score(target) > generation.get(0).score(target)) {
				best = new Chromosome(generation.get(0));
				System.out.println("Generacija = " + gen_cnt + " | Izraz = " + best.infix() + " | Razlika = " + best.score(target));
			}
			
			if (best.score(target) < eps) {
				break;
			}

			assert(next_generation.size() == 0);
			assert(generation.size() == chromosome_count);
		}
		double time = (System.currentTimeMillis() - startTime)/1000.;
		System.out.println("Vreme = " + time +"s");
	}
}
