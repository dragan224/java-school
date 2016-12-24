package operations_test;

import static org.junit.Assert.*;

import org.junit.Test;

import data_types.Point;
import data_types.Triangle;

public class TriangleTest {

	@Test
	public void triangleOperationTest() {
		Triangle t = new Triangle(new Point(0, 0), new Point(10, 0), new Point(0, 10));
		t.translate(5, 5);
		assertEquals(t.getA(), new Point(5, 5));
		assertEquals(t.getB(), new Point(15, 5));
		assertEquals(t.getC(), new Point(5, 15));
		
		assert(t.area() == 50);
		assert(Math.abs(t.perimeter() - 34.142) < 0.01);
	}

}
