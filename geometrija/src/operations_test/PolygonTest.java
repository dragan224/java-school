package operations_test;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import data_types.Point;
import javafx.scene.shape.Polygon;

public class PolygonTest {

	@Test
	public void convexPolygonTest() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(0, 0));
		points.add(new Point(0, 10));
		points.add(new Point(10, 10));
		points.add(new Point(10, 0));
		
		data_types.Polygon p = new data_types.Polygon(points);
		assert(p.area() == 100);
		assert(p.perimeter() == 40);
	}
	
	@Test
	public void nonConvexPolygonTest() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(0, 0));
		points.add(new Point(0, 10));
		points.add(new Point(5, 5));
		points.add(new Point(10, 10));
		points.add(new Point(10, 0));
		points.add(new Point(5, 5));
		
		data_types.Polygon p = new data_types.Polygon(points);
		assert(p.area() == 50);
		assert(p.perimeter() == 4*Math.sqrt(50)+20);
	}

}
