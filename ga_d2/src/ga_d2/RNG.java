package ga_d2;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RNG {
	private static Random rng = new Random(System.currentTimeMillis()); 
	
	public static String[] op = {"+", "-", "/", "*"};
	
	public static String random_op() {
		return new String(op[RNG.nextInt()%4]);
	}
	
	public static int nextInt() {
		return Math.abs(rng.nextInt());
	}
	
	public static int nextInt(int ub) {
		return rng.nextInt(ub);
	}
	
	public static Chromosome nextChromosome(List<Integer> input, int len) {
		Chromosome c = new Chromosome();
		Collections.shuffle(input);
		for (int i = 0; i < len; i++) {
			c.addToken(new Token(input.get(i)));
		}
		for (int i = 0; i < len-1; i++) {
			c.addToken(new Token("+"));
		}
		return c;
	}
}
