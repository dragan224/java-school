package operations_test;

import static org.junit.Assert.*;

import org.junit.Test;

import data_types.Point;

public class RectangleTest {

	@Test
	public void rectangleAreaTest() {
		data_types.Rectangle r = new data_types.Rectangle(
				new Point(0, 0), 
				new Point(10, 0), 
				new Point(10, 10), 
				new Point(0, 10)); 
		
		assert(r.area() == 100);
		assert(r.perimeter() == 40);
	}

}
