package data_types;

import java.awt.Graphics2D;
import java.util.Collection;

import javafx.scene.canvas.GraphicsContext;

/**
 * Klasa za krug. Krug sadrzi tacku (centar) i poluprecnik (radius).
 */
public class Circle implements Shape {

	/**
	 * Konstruktor.
	 * @param center centar kruga.
	 * @param radius velicina poluprecnika kruga.
	 */
	public Circle(Point center, int radius) {
		super();
		this.center = center;
		this.radius = radius;
	}
	
	Point center;
	int radius;
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/**
	 * Crta krug na grafici g.
	 * @param g graphics nad kom se crta krug.
	 */
	public void draw(Graphics2D g) {
		g.drawOval(center.getX(), center.getY(), 2*radius, 2*radius);
	}
	
	/**
	 * Da li krug sadrzi zadatu tacku
	 * @param p tacka iz upita.
	 * @return true/false pridanosti/ne.
	 */
	public boolean contains(Point p) {
		return center.distanceTo(p) <= radius * radius;
	}
	
	/**
	 * Da li krug sadrzi zadatu listu tacaka.
	 * @param ps kolekcija tacaka iz upita.
	 * @return true/false od zavisnosti od pripadnosti.
	 */
	public boolean contains(Collection<Point> ps) {
		for (Point p : ps) {
			if (!contains(p))
				return false;
		}
		return true;
	}

	@Override
	public void drawFX(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.strokeOval(center.getX(), center.getY(), 2*radius, 2*radius);
	}

	@Override
	public double perimeter() {
		return 2*Math.PI*radius;
	}

	@Override
	public double area() {
		return Math.PI*Math.PI*radius;
	}

	@Override
	public void translate(int dx, int dy) {
		center.setX(center.getX()+dx);
		center.setY(center.getY()+dy);
	}
}
