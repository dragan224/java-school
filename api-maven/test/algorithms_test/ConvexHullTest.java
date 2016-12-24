package algorithms_test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

import algorithms_api.ConvexHull;
import algorithms_api.Vectors;
import data_types.Point;

public class ConvexHullTest {

	@Test
	public void convexHullTest() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(0, 0)); // 0
		points.add(new Point(10, 0)); // 1
		points.add(new Point(9, 9)); // 2
		points.add(new Point(7, 5)); // 3
		points.add(new Point(2, 10)); // 4
		points.add(new Point(4, 6)); // 5
		points.add(new Point(3, 1)); // 6
		
		ConvexHull chull = new ConvexHull(points);
		
		assertEquals(chull.size(), 4);
		assertEquals(isConvex(chull), true);
		
		// Omotac (0,0) - (2, 10) - (9, 9) - (10, 0)
		assertEquals(chull.get(0), points.get(0));
		assertEquals(chull.get(1), points.get(4));
		assertEquals(chull.get(2), points.get(2));
		assertEquals(chull.get(3), points.get(1));
	}
	
	private boolean isConvex(ConvexHull chull) {
        int n = chull.size();
        if (n <= 2) return true;

        Point[] points = new Point[n];
        int k = 0;
        for (Point p : chull.hull()) {
            points[k++] = p;
        }

        for (int i = 0; i < n; i++) {
            if (Vectors.crossProduct(points[i], points[(i+1) % n], points[(i+2) % n]) <= 0) {
                return false;
            }
        }
        return true;
    }

}
