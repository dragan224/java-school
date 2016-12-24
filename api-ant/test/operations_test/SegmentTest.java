package operations_test;

import static org.junit.Assert.*;

import org.junit.Test;

import data_types.Point;
import data_types.Segment;

public class SegmentTest {

	@Test
	public void translationTest() {
		Segment seg = new Segment(new Point(10, 15), new Point(5, 4));
		assert(seg.area() == 0);
		assert(seg.perimeter() == 0);
		
		//translacija
		Segment segT = new Segment(new Point(15, 10), new Point(10, -1));
		segT.translate(-5, 5);
		assertEquals(segT, seg);
		
		segT.translate(-300, +500);
		segT.translate(300, -500);
		assertEquals(segT, seg);
	}

}
