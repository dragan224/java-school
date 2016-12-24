package algorithms_api;

import data_types.Point;
import data_types.Segment;
import data_types.Triangle;

/**
 * Klasa za racunanje nekih vektorskih operacija (vektorski proizvod itd.)
 */
public class Vectors {
	
	/**
	 * Racuna vektorski proizvoda tri tacke.
	 * @param A prva tacka.
	 * @param B druga tacka.
	 * @param C trece tacka.
	 * @return vektorski proizvod vektora |BA| x |BC| 
	 */
	public static int crossProduct(Point A, Point B, Point C) {
		Point V = new Point(A.getX() - B.getX(), A.getY() - B.getY());
		Point U = new Point(C.getX() - B.getX(), C.getY() - B.getY());
		return (V.getX()*U.getY() - V.getY()*U.getX());
	}
	
	/**
	 * Racuna vektorski proizvod segmenta i tacke.
	 * @param segment segment (A, B).
	 * @param C tacka.
	 * @return vektorski proizvod vektora |BA| x |BC| 
	 */
	public static int crossProduct(Segment segment, Point C) {
		return crossProduct(segment.getA(), segment.getB(), C);
	}
	
	/**
	 * Racuna vektorski proizvod temena trougla.
	 * @param triangle trougao (A, B, C).
	 * @return vektorski proizvod vektora |BA| x |BC| 
	 */
	public static int crossProduct(Triangle triangle) {
		return crossProduct(triangle.getA(), triangle.getB(), triangle.getC());
	}
}
