package operations_test;

import static org.junit.Assert.*;

import org.junit.Test;

import data_types.Point;

public class PointTest {

	@Test
	public void translationTest() {
		Point A = new Point(5, 3);
		
		A.translate(5, -3);
		assertEquals(A, new Point(10, 0));
		
		A.translate(-5, 3);
		assertEquals(A, new Point(5, 3));
		
		assert(A.area() == 0);
		assert(A.perimeter() == 0);
	}

}
