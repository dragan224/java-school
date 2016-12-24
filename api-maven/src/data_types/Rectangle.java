package data_types;

import java.awt.Graphics2D;

import javafx.scene.canvas.GraphicsContext;

/**
 * Pravougaonik - sastoji se od cetiri tacke (temena) u smeru kazaljke/suprotno od smera kazaljke na satu.
 */
public class Rectangle implements Shape {
	
	/**
	 * Konstruktor.
	 * @param a donje levo teme.
	 * @param b donje desno teme.
	 * @param c gornje desno temo.
	 * @param d gornje levo teme.
	 */
	public Rectangle(Point a, Point b, Point c, Point d) {
		super();
		A = a;
		B = b;
		C = c;
		D = d;
	}
	
	private Point A;
	private Point B;
	private Point C;
	private Point D;
	
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
	
	public Point getD() {
		return D;
	}
	
	public void setD(Point d) {
		D = d;
	}
	
	
	/**
	 * Crta cetvorougao na grafici g.
	 * @param g graphics nad kom se crta cetvorougao.
	 */
	@Override
	public void draw(Graphics2D g) {
		g.drawLine(A.getX(), A.getY(), B.getX(), B.getY());
		g.drawLine(B.getX(), B.getY(), C.getX(), C.getY());
		g.drawLine(C.getX(), C.getY(), D.getX(), D.getY());
		g.drawLine(D.getX(), D.getY(), A.getX(), A.getY());
	}

	@Override
	public void drawFX(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
		gc.strokeLine(B.getX(), B.getY(), C.getX(), C.getY());
		gc.strokeLine(C.getX(), C.getY(), D.getX(), D.getY());
		gc.strokeLine(D.getX(), D.getY(), A.getX(), A.getY());
		
	}

	@Override
	public double perimeter() {
		double a = A.distanceToSQRT(B);
		double b = B.distanceToSQRT(C);
		double c = C.distanceToSQRT(D);
		double d = D.distanceToSQRT(A);
		return a + b + c + d;
	}

	@Override
	public double area() {
		return new Triangle(A, B, C).area() * 2;
	}

	@Override
	public void translate(int dx, int dy) {
		A.translate(dx, dy);
		B.translate(dx, dy);
		C.translate(dx, dy);
		D.translate(dx, dy);
	}
}
