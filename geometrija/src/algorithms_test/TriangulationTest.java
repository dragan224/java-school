package algorithms_test;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms_api.Triangulation;
import data_types.Point;

public class TriangulationTest {

	@Test
	public void isCircleInsideTest() {
		Point A = new Point(0, 0);
		Point B = new Point(5, 0);
		Point C = new Point(0, 5);
		Point D = new Point(3, 3);
		Point E = new Point(30, 30);
		
		assertEquals(Triangulation.isInside(D, A, B, C), true);
		assertEquals(Triangulation.isInside(E, A, B, C), false);
	}

}
