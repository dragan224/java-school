package algorithms_api;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import data_types.Point;

/**
 * Klasa za racunanje konveksnog omotaca tacaka, od liste zadatih tacaka u O(NlogN) vremenu.
 * Konveksni omotac je najmanji (povrsine) poligon koji obuhvata sve tacke sa ulaza.
 */
public class ConvexHull {
	/**
     * Racuna konveksni omotac. O(NlogN)
     *
     * @param  pts niz tacaka
     */
	public ConvexHull(List<Point> pts) {

		int n = pts.size();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++)
            points[i] = pts.get(i);

        Arrays.sort(points);

        Arrays.sort(points, 1, n, points[0].polarOrder());

        hull.push(points[0]);       

        // find index k1 of first point not equal to points[0]
        int k1;
        for (k1 = 1; k1 < n; k1++)
            if (!points[0].equals(points[k1])) break;
        if (k1 == n) return;        // all points equal

        // find index k2 of first point not collinear with points[0] and points[k1]
        int k2;
        for (k2 = k1+1; k2 < n; k2++)
            if (Vectors.crossProduct(points[0], points[k1], points[k2]) != 0) break;
        hull.push(points[k2-1]);    // points[k2-1] is second extreme point

        // Graham scan; note that points[n-1] is extreme point different from points[0]
        for (int i = k2; i < n; i++) {
            Point top = hull.pop();
            while (Vectors.crossProduct(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }

//        assert isConvex();
	}
	
	/**
    * Vraca tacke na omotacu.
    *
    * @return tacke na omotacu u smeru suprotnom od kazaljke na satu (mozda u smeru kazaljke na satu nisam siguran).
    */
    public Iterable<Point> hull() {
        Stack<Point> s = new Stack<Point>();
        for (Point p : hull) s.push(p);
        return s;
    }
    
    public Point get(int index) {
    	return hull.get(index);
    }
    
    public int size() {
    	return hull.size();
    }
	
	private Stack<Point> hull = new Stack<Point>();
}
