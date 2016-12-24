import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.SynchronousQueue;

import javax.rmi.CORBA.Util;
import javax.swing.Painter;

// kolekcija algoritama za klasterovanje.


public class ClusteringAlgorithms {
	
	// Random algoritam.. kao sto ime kaze generise broj grupa na slucajas izbor
	// i onda stavi svaku tacku u neku od tih grupa...
	// slozenost O(n)
	public static ArrayList<Point> randomAlgorithm(ArrayList<Point> input) {
		ArrayList<Point> output = new ArrayList<>();
		
		Random generator = new Random(System.currentTimeMillis());
		int clusterCnt = generator.nextInt(7) + 2; // od 2 do 8 broj grupa
		
		for (Point pt: input) {
			pt.setCluster(generator.nextInt(clusterCnt)); // od 0 do clusterCnt-1
			output.add(pt);
		}
		
		return output;
	}
	
	// Hijerajhiski algoritam.
	// https://en.wikipedia.org/wiki/Hierarchical_clustering
	// Slozenost je O((n-k)*nlogn) = O(n^2logn), n - broj tacaka a k - broj grupa/klastera 
	// Kratki opis algoritma: U svakom koraku spajamo dve najblize grupe, dok broj grupa
	// ne postane k. Blizina dve grupa = blizina centara te dve grupa, a najblize dve
	// nalazimo u nlogn (algoritam najblizi par tacaka).
	public static ArrayList<Point> hierarchicalAlgorithm(ArrayList<Point> input, int k) {
		ArrayList<Point> output = new ArrayList<>();
		
		// U pocetku je svaka tacka grupa za sebe...
		for (Point pt: input) {
			output.add(new Point(pt.getX(), pt.getY(), -1));
		}
		for (int i = 0; i < output.size(); i++) {
			output.get(i).setCluster(i);
		}
		//
		
		int cnt_clusters = output.size(); // broj grupa
		while (cnt_clusters > k) {
			// nalazimo centralne tacke svake grupe O(n)
			ArrayList<Point> centroids = findCentroids(output);
			ClosestPairOfPoints cp = new ClosestPairOfPoints(centroids);
			// spajamo grupe sa indeksom cl1 i cl2
			int cl1 = centroids.get(cp.eitherIndex()).getCluster();
			int cl2 = centroids.get(cp.otherIndex()).getCluster();
			for (Point pt: output) {
				if (pt.getCluster() == cl2) {
					pt.setCluster(cl1);
				}
			}
			
			cnt_clusters--;
		}
		return normalize(output);
	}
	
	// K-Means algoritam
	// https://en.wikipedia.org/wiki/K-means_clustering
	// Kratak opis algoritma:
	// 1) Izebere se na nasumican nacin k centralnih tacaka
	// 2) Za svaku tacku sa ulaza se nadje njeno rastojanje do svih centralnih tacaka.
	// 3) Dodeljuju se grupe za svaku tacku po najblizoj centralnoj tacki, tj. dve tacke
	//    A i B dele istu grupu akko je ista centralna tacka najbliza i za A i za B.
	// 4) Izracunava se k novih centralnih tacaka za svaku tako novodobijenu grupu.
	// 5) Za svaku tacku sa ulaza se nadje njeno rastojanje do svih centralnih tackaka.
	// 6) Ako postoji neka tacka koja je u koraku 5) promenila grupu, onda se ponavljaju
	//    koraci 2) i 3), a ako ne postoji algoritam se zavsava.
	
	// Slozenost je O(t*n*d*k) 
	// t = broj iteracija dok se algoritam ne zavrsi
	// n = broj tacaka sa ulaza
	// k = broj grupa
	// d = broj dimenzija (u ovom slucaju 2)
	// moze se pokazati da t ima polinomnu zavisnost od n, d i k
	
	public static ArrayList<Point> kmeansAlgorithm(ArrayList<Point> input, int k) {
		ArrayList<Point> output = new ArrayList<>();
		
		// Biranje pocetnih centara (korak 1)
		// generisanje osam 
		for (Point pt: input) {
			output.add(new Point(pt.getX(), pt.getY(), -1));
		}
		Collections.shuffle(output);
		for (int i = 0; i < k; i++) {
			output.get(i).setCluster(i);
		}
		
		boolean changing = true;
		int iter = 0;
		while (changing) {
			changing = false;
			++iter;
			
			ArrayList<Point> centroids = findCentroids(output);
			
			changing = groupByDistance(output, centroids);
		}
		
		System.out.println("Broj iteracija u k-means algoritmu: t = " + iter);
		return normalize(output);
	}
	
	
	
	// pomocne funkcije
	
	// grupise tacke u listi input po razdaljini od najblize tacke iz liste centroids
	// vraca boolean vrednost.. da li je nesto promenjeno u input listi
	private static boolean groupByDistance(ArrayList<Point> input, ArrayList<Point> centroids) {
		boolean changed = false;
		
		for (Point pt: input) {
			double minDist = Double.POSITIVE_INFINITY;
			int cluster_index = -1;
			for (Point c: centroids) {
				if (pt.distanceTo(c) < minDist) {
					minDist = pt.distanceTo(c);
					cluster_index = c.getCluster();
				}
			}
			if (cluster_index != pt.getCluster()) {
				changed = true;
				pt.setCluster(cluster_index);
			}
		}
		
		return changed;
	}
	
	// Nalazi centre(tezista) za svaku od n grupa O(n)
	private static ArrayList<Point> findCentroids(ArrayList<Point> input) {
		ArrayList<Point> output = new ArrayList<>();
		
		double sumX[] = new double[input.size()];
		double sumY[] = new double[input.size()];
		int cnt[] = new int[input.size()];
		
		for (Point pt: input) {
			if (pt.getCluster() == -1) continue; // tacka ne pripada ni jednoj grupi
			sumX[pt.getCluster()] += pt.getX();
			sumY[pt.getCluster()] += pt.getY();
			cnt[pt.getCluster()]++;
		}
		
		for (int i = 0; i < input.size(); i++) {
			if (cnt[i] == 0) continue;
			output.add(new Point(sumX[i] / cnt[i], sumY[i] / cnt[i], i));
		}
		
		return output;
	}
	
	
	// mapira k razlicitih brojeva od 1 do n u brojeve od 0 do k-1.
	// primer: {1, 4, 5, 5, 5, 7} -> {0, 1, 2, 2, 2, 3}
	// O(n*k) gde je k u nasem slucaju od 2 do 8 -> O(n)
	private static ArrayList<Point> normalize(ArrayList<Point> input) {
		ArrayList<Point> output = new ArrayList<>();
		
		
		
		int MAX_K = 10;
		int mapper[] = new int[MAX_K];
		for (int i = 0; i < MAX_K; i++) {
			mapper[i] = -1;
		}
		
		int cnt[] = new int[input.size()];
		// koliko se svaka grupa pojavljuje puta...
		// ako je taj broj == 1 onda ta tacka nije upala ni u jednu drugu grupu
		for (Point pt: input) {
			cnt[pt.getCluster()]++;
		}
		
		for (Point pt: input) {
			if (cnt[pt.getCluster()] < 2) {
				output.add(new Point(pt.getX(), pt.getY(), -1));
				continue;
			}
			int cluster_index = -1;
			for (int i = 0; i < MAX_K; i++) {
				if (mapper[i] == pt.getCluster()) {
					cluster_index = i;
					break;
				}
				if (mapper[i] == -1) {
					mapper[i] = pt.getCluster();
					cluster_index = i;
					break;
				}
			}
			output.add(new Point(pt.getX(), pt.getY(), cluster_index));
		}
		
		return output;
	}
}
