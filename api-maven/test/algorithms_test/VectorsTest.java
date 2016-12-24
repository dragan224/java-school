package algorithms_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import algorithms_api.Vectors;
import data_types.Point;
import data_types.Segment;
import data_types.Triangle;

public class VectorsTest {

	@Test
	public void crossProductTest() {
		Point A = new Point(1, 2);
		Point B = new Point(2, 4);
		Point C = new Point(3, 0);
		
		int crossProductThreePoints = Vectors.crossProduct(A, B, C);
		assertEquals(crossProductThreePoints, 6);
		
		int crossProductSegment = Vectors.crossProduct(new Segment(A, B), C);
		assertEquals(crossProductSegment, 6);
		
		int crossProductTriangle = Vectors.crossProduct(new Triangle(A, B, C));
		assertEquals(crossProductTriangle, 6);
	}
	
	@Test
	public void negativeCrossProductTest() {
		Point C = new Point(1, 2);
		Point B = new Point(2, 4);
		Point A = new Point(3, 0);
		
		int crossProductThreePoints = Vectors.crossProduct(A, B, C);
		assertEquals(crossProductThreePoints, -6);
		
		int crossProductSegment = Vectors.crossProduct(new Segment(A, B), C);
		assertEquals(crossProductSegment, -6);
		
		int crossProductTriangle = Vectors.crossProduct(new Triangle(A, B, C));
		assertEquals(crossProductTriangle, -6);
	}

}
