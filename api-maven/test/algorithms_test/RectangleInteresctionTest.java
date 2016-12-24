package algorithms_test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import algorithms_api.RectangleIntersection;
import data_types.Point;
import data_types.Rectangle;

public class RectangleInteresctionTest {

	@Test
	public void interesctionOfTwoRectanglesTest() {
		Rectangle A = new Rectangle(new Point(0, 0),
									new Point(10, 0),
									new Point(10, 10),
									new Point(0, 10));
		
		Rectangle B = new Rectangle(new Point(2, 2), 
									new Point(12, 2),
									new Point(12, 12),
									new Point(2,12));
		
		Rectangle interesction = RectangleIntersection.interesction(A, B);
		
		assertEquals(interesction.getA(), new Point(2, 2));
		assertEquals(interesction.getB(), new Point(10, 2));
		assertEquals(interesction.getC(), new Point(10, 10));
		assertEquals(interesction.getD(), new Point(2, 10));
	}

}
