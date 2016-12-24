package data_types;

import java.awt.Graphics2D;

import javafx.scene.canvas.GraphicsContext;

/**
 * Segment(duz) - sastoji se od dve tacke, pocetak i kraj duzi.
 */
public class Segment implements Shape {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((A == null) ? 0 : A.hashCode());
		result = prime * result + ((B == null) ? 0 : B.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segment other = (Segment) obj;
		if (A == null) {
			if (other.A != null)
				return false;
		} else if (!A.equals(other.A))
			return false;
		if (B == null) {
			if (other.B != null)
				return false;
		} else if (!B.equals(other.B))
			return false;
		return true;
	}

	/**
	 * Konstruktor.
	 * @param A jedna tacka duzi.
	 * @param B druga tacka duzi.
	 */
	public Segment(Point A, Point B) {
		this.A = A;
		this.B = B;
	}
	
	private Point A;
	private Point B;
	
	public Point getA() {
		return A;
	}

	public void setA(Point a) {
		A = a;
	}

	public Point getB() {
		return B;
	}

	public void setB(Point b) {
		B = b;
	}

	/**
	 * Crta liniju na grafici g.
	 * @param g graphics nad kom se crta linija.
	 */
	public void draw(Graphics2D g) {
		g.drawLine(A.getX(), A.getY(), B.getX(), B.getY());
	}

	@Override
	public void drawFX(GraphicsContext gc) {
		gc.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
		
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void translate(int dx, int dy) {
		A.translate(dx, dy);
		B.translate(dx, dy);
	}
}
