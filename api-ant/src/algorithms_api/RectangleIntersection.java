package algorithms_api;

import java.util.ArrayList;

import data_types.Point;
import data_types.Rectangle;

/**
 * Klasa za racunanje preseka N pravougaonika.
 */
public class RectangleIntersection {
	/**
	 * Racuna presek dva pravougaonika.
	 * @param A prva pravougaonik.
	 * @param B drugi pravougaonik.
	 * @return pravougaonik koji je presek ta dva.
	 */
	public static Rectangle interesction(Rectangle A, Rectangle B) {
		if (A == null || B == null) {
			return null;
		}
		if (A.getA().getX() > B.getA().getX()) {
			return interesction(B, A);
		}
		java.awt.Rectangle r1 = new java.awt.Rectangle(A.getA().getX(), A.getA().getY(), A.getC().getX()-A.getD().getX(), A.getD().getY()-A.getA().getY());
		java.awt.Rectangle r2 = new java.awt.Rectangle(B.getA().getX(), B.getA().getY(), B.getC().getX()-B.getD().getX(), B.getD().getY()-B.getA().getY());
		java.awt.Rectangle interesction = r1.intersection(r2);
		if (interesction.isEmpty()) return null;
		
		return new Rectangle(new Point(interesction.x, interesction.y),
							 new Point(interesction.x + interesction.width, interesction.y), 
							 new Point(interesction.x + interesction.width, interesction.y+interesction.height),
							 new Point(interesction.x, interesction.y+interesction.height));
	}
	/**
	 * Racuna presek N pravougaonika.
	 * @param rectangles lista pravougaonika.
	 * @return pravougaonik koji je presek tih N.
	 */
	public static Rectangle interectionN(ArrayList<Rectangle> rectangles) {
		if (rectangles.size() == 1) {
			return rectangles.get(0);
		} else {
			Rectangle start = interesction(rectangles.get(0), rectangles.get(1));
			for (int i = 2; i < rectangles.size(); i++) {
				start = interesction(start, rectangles.get(i));
			}
			return start;
		}
	}
}
