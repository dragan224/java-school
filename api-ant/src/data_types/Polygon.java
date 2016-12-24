package data_types;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

/**
 * Poligon - sastoji se od array liste tacaka u smeru kazaljke na satu ili u suprotnom smeru (ne sme biti random razbacano).
 */
public class Polygon implements Shape {
	
	/**
	 * Konstruktor.
	 * @param points lista temena sortirana u smeru kazaljke na satu, ili suprotnom smeru.
	 */
	public Polygon(ArrayList<Point> points) {
		super();
		this.points = points;
	}
	
	private ArrayList<Point> points;
 	
	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	/**
	 * Crta poligon na grafici g.
	 * @param g graphics nad kom se crta poligon.
	 */
	@Override
	public void draw(Graphics2D g) {
		for (int i = 1; i < points.size(); i++) {
			Point A = points.get(i - 1);
			Point B = points.get(i);
			g.drawLine(A.getX(), A.getY(), B.getX(), B.getY());
		}
		Point A = points.get(points.size()-1);
		Point B = points.get(0);
		g.drawLine(A.getX(), A.getY(), B.getX(), B.getY());
	}

	@Override
	public void drawFX(GraphicsContext gc) {
		for (int i = 1; i < points.size(); i++) {
			Point A = points.get(i - 1);
			Point B = points.get(i);
			gc.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
		}
		Point A = points.get(points.size()-1);
		Point B = points.get(0);
		gc.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
	}

	@Override
	public double perimeter() {
		double perimeter = 0;
		for (int i = 1; i < points.size(); i++) {
			Point A = points.get(i - 1);
			Point B = points.get(i);
			perimeter += A.distanceToSQRT(B);
		}
		Point A = points.get(points.size()-1);
		Point B = points.get(0);
		perimeter += A.distanceToSQRT(B);
		return perimeter;
	}

	@Override
	public double area() {
		double area = 0;
		for (int i = 1; i < points.size(); i++) {
			Point A = points.get(i - 1);
			Point B = points.get(i);
			area += A.getX()*B.getY() - A.getY()*B.getX();
		}
		Point A = points.get(points.size()-1);
		Point B = points.get(0);
		area += A.getX()*B.getY() - A.getY()*B.getX();
		return Math.abs(area) * 0.5;
	}

	@Override
	public void translate(int dx, int dy) {
		for (Point p: points) {
			p.translate(dx, dy);
		}
	}
}
