package data_types;

import java.awt.Graphics2D;

import algorithms_api.Vectors;
import javafx.scene.canvas.GraphicsContext;

/**
 * Trougao - sastoji se od tri tacke (temena trouglova).
 */
public class Triangle implements Shape {
	
	/**
	 * Konstruktor.
	 * @param a prvo teme trougla.
	 * @param b drugo teme trougla.
	 * @param c trece teme trougla.
	 */
	public Triangle(Point a, Point b, Point c) {
		super();
		A = a;
		B = b;
		C = c;
	}
	
	/**
	 * Dvostruka povrsina trougla.
	 * @return dvostruku povrsinu trougla.
	 */
	int doubleArea() {
		return Math.abs(Vectors.crossProduct(this));
	}

	private Point A;
	private Point B;
	private Point C;
	
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

	public Point getC() {
		return C;
	}

	public void setC(Point c) {
		C = c;
	}

	/**
	 * Crta trougao na grafici g.
	 * @param g graphics nad kom se crta trougao.
	 */
	public void draw(Graphics2D g) {
		g.drawLine(A.getX(), A.getY(), B.getX(), B.getY());
		g.drawLine(B.getX(), B.getY(), C.getX(), C.getY());
		g.drawLine(C.getX(), C.getY(), A.getX(), A.getY());
	}

	@Override
	public void drawFX(GraphicsContext gc) {
		gc.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
		gc.strokeLine(B.getX(), B.getY(), C.getX(), C.getY());
		gc.strokeLine(C.getX(), C.getY(), A.getX(), A.getY());
	}

	@Override
	public double perimeter() {
		double a = A.distanceToSQRT(B);
		double b = B.distanceToSQRT(C);
		double c = A.distanceToSQRT(C);
		return a + b + c;
		
	}

	@Override
	public double area() {
		double a = A.distanceToSQRT(B);
		double b = B.distanceToSQRT(C);
		double c = A.distanceToSQRT(C);
		double s = 0.5  * (a+b+c);
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

	@Override
	public void translate(int dx, int dy) {
		A.translate(dx, dy);
		B.translate(dx, dy);
		C.translate(dx, dy);
	}
}
