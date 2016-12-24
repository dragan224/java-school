package algorithms_api;

import java.util.ArrayList;
import java.util.Arrays;

import data_types.Point;

/**
 * Klasa za racunanje para najblizih tacaka, od liste zadatih tacaka u O(NlogN) vremenu.
 * Podeli pa vladaj algoritam.
 */
public class ClosestPoints {

    private Point best1, best2;
    private int bestDistance = Integer.MAX_VALUE;

    /**
     * Racuna par najblizih tacaka iz niza. O(NlogN)
     *
     * @param  points niz tacaka
     */
    public ClosestPoints(ArrayList<Point> points) {
        int n = points.size();
        if (n <= 1) return;

        // sort po x-y.
        Point[] pointsByX = new Point[n];
        for (int i = 0; i < n; i++)
            pointsByX[i] = points.get(i);
        Arrays.sort(pointsByX, Point.X_ORDER);

        // provera koincidentnih tacaka
        for (int i = 0; i < n-1; i++) {
            if (pointsByX[i].equals(pointsByX[i+1])) {
                bestDistance = 0;
                best1 = pointsByX[i];
                best2 = pointsByX[i+1];
                return;
            }
        }

        // sortira po y
        Point[] pointsByY = new Point[n];
        for (int i = 0; i < n; i++)
            pointsByY[i] = pointsByX[i];

        Point[] aux = new Point[n];

        closest(pointsByX, pointsByY, aux, 0, n-1);
    }

    private int closest(Point[] pointsByX, Point[] pointsByY, Point[] aux, int lo, int hi) {
        if (hi <= lo) return Integer.MAX_VALUE;

        int mid = lo + (hi - lo) / 2;
        Point median = pointsByX[mid];

        int delta1 = closest(pointsByX, pointsByY, aux, lo, mid);
        int delta2 = closest(pointsByX, pointsByY, aux, mid+1, hi);
        int delta = Math.min(delta1, delta2);

        merge(pointsByY, aux, lo, mid, hi);

        int m = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(pointsByY[i].getX() - median.getX()) < delta)
                aux[m++] = pointsByY[i];
        }

        for (int i = 0; i < m; i++) {
            for (int j = i+1; (j < m) && (aux[j].getY() - aux[i].getY() < delta); j++) {
                int distance = aux[i].distanceTo(aux[j]);
                if (distance < delta) {
                    delta = distance;
                    if (distance < bestDistance) {
                        bestDistance = delta;
                        best1 = aux[i];
                        best2 = aux[j];
                    }
                }
            }
        }
        return delta;
    }

 
	/**
     * Vraca jednu od dve najblize tacke.
     *
     * @return jedna od dve najblize tacke
     *         {@code null} ako takva tacka ne postoji.
     */
    public Point either() {
        return best1;
    }

    /**
     * Vraca durug od dve najblize tacke.
     *
     * @return druga od dve najblize tacke
     *         {@code null} ako takva tacka ne postoji (manje od dve tacke na ulazu).
     */
    public Point other() {
        return best2;
    }

    /**
     * Rastojanje izmedju dve najblize tacke.
     *
     * @return Kvadrat Euklidovog rastoanje dve najblize tacke.
     *         {@code Integer.MAX_INT} ako ne postoji par najblizih.
     */
    public int distance() {
        return bestDistance;
    }

    private static boolean less(Point v, Point w) {
        return v.compareTo(w) < 0;
    }

    private static void merge(Point[] pointsByY, Point[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = pointsByY[k];
        }
    
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              pointsByY[k] = aux[j++];
            else if (j > hi)               pointsByY[k] = aux[i++];
            else if (less(aux[j], aux[i])) pointsByY[k] = aux[j++];
            else                           pointsByY[k] = aux[i++];
        }
    }
}
