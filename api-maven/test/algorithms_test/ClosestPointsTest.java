package algorithms_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algorithms_api.ClosestPoints;
import data_types.Point;
import data_types.Segment;
import data_types.Triangle;

public class ClosestPointsTest {

	@Test
	public void closestPointsTest() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(1, 2));
		points.add(new Point(2, 4));
		points.add(new Point(3, 0));
		points.add(new Point(100, 224));
		points.add(new Point(-100, 224));
		
		assertEquals(new ClosestPoints(points).distance(), 5);
	}
	
	@Test
	public void closestPointsTestCoincidentPoints() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(1, 2));
		points.add(new Point(2, 4));
		points.add(new Point(2, 4));
		
		assertEquals(new ClosestPoints(points).distance(), 0);
	}

}
