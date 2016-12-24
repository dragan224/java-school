package operations_test;

import static org.junit.Assert.*;

import org.junit.Test;

import data_types.Point;
import javafx.scene.shape.Circle;

public class CircleTest {

	@Test
	public void circleAreaTest() {
		data_types.Circle c = new data_types.Circle(new Point(2, 5), 5);
		
		assert(Math.abs(c.area() - 5*Math.PI*Math.PI) < 0.01);
		assert(Math.abs(c.perimeter() - 10*Math.PI) < 0.01);
		
		c.translate(500, -300);
		
		assert(Math.abs(c.area() - 5*Math.PI*Math.PI) < 0.01);
		assert(Math.abs(c.perimeter() - 10*Math.PI) < 0.01);
//		assert(c.area() == 5*Math.PI*Math.PI);
//		assert(c.perimeter() == 10*Math.PI);
	}

}
