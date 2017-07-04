package ga_d2;

import java.util.Comparator;

public class ChromosomeComparator implements Comparator<Chromosome> {
	int target;
	
	public ChromosomeComparator(int target) {
		super();
		this.target = target;
	}



	@Override
	public int compare(Chromosome o1, Chromosome o2) {
		double score1 = o1.score(target);
		double score2 = o2.score(target);
		if (score1 < score2) {
			return -1;
		} else if (score1 > score2) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
