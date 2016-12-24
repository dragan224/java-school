import java.util.ArrayList;
import java.util.Arrays;

// Klasa koja racuna najblizi par tacaka u O(nlogn)
public class ClosestPairOfPoints {

    private Point best1, best2;
    private int index1, index2;
    private double bestDistance = Double.POSITIVE_INFINITY;
    
    
    public ClosestPairOfPoints(ArrayList<Point> points) {
        int N = points.size();
        if (N <= 1) return;

        Point[] pointsByX = new Point[N];
        for (int i = 0; i < N; i++)
            pointsByX[i] = points.get(i);
        Arrays.sort(pointsByX, Point.X_ORDER);

        for (int i = 0; i < N-1; i++) {
            if (pointsByX[i].equals(pointsByX[i+1])) {
                bestDistance = 0.0;
                best1 = pointsByX[i];
                best2 = pointsByX[i+1];
                return;
            }
        }

        Point[] pointsByY = new Point[N];
        for (int i = 0; i < N; i++)
            pointsByY[i] = pointsByX[i];

         Point[] aux = new Point[N];

        closest(pointsByX, pointsByY, aux, 0, N-1);
        
        for (int i = 0; i < points.size(); i++) {
        	if (points.get(i).equals(best1)) {
        		index1 = i;
        	}
        	if (points.get(i).equals(best2)) {
        		index2 = i;
        	}
        }
    }

    private double closest(Point[] pointsByX, Point[] pointsByY, Point[] aux, int lo, int hi) {
        if (hi <= lo) return Double.POSITIVE_INFINITY;

        int mid = lo + (hi - lo) / 2;
        Point median = pointsByX[mid];

        double delta1 = closest(pointsByX, pointsByY, aux, lo, mid);
        double delta2 = closest(pointsByX, pointsByY, aux, mid+1, hi);
        double delta = Math.min(delta1, delta2);

        merge(pointsByY, aux, lo, mid, hi);

        int M = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(pointsByY[i].getX() - median.getX()) < delta)
                aux[M++] = pointsByY[i];
        }

        for (int i = 0; i < M; i++) {
            for (int j = i+1; (j < M) && (aux[j].getY() - aux[i].getY() < delta); j++) {
                double distance = aux[i].distanceTo(aux[j]);
                if (distance < delta) {
                    delta = distance;
                    if (distance < bestDistance) {
                        bestDistance = delta;
                        best1 = aux[i];
                        best2 = aux[j];
                        // StdOut.println("better distance = " + delta + " from " + best1 + " to " + best2);
                    }
                }
            }
        }
        return delta;
    }

    public Point either() {
        return best1;
    }

    public Point other() {
        return best2;
    }
    
    public int eitherIndex() {
    	return index1;
    }
    
    public int otherIndex() {
    	return index2;
    }


    public double distance() {
        return bestDistance;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
    
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }
}