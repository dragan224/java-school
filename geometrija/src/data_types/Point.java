package data_types;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

import algorithms_api.Vectors;
import javafx.scene.canvas.GraphicsContext;

/**
 * Tacka - sastoji se od dve koordinate x i y.
 */
public class Point implements Shape, Comparable<Point> {
	
	/**
     * Velicina tacke u pikselima.
     */
	public static final int POINT_SIZE = 4;
	
	/**
     * Uporedjiju tacke po uglu koji zaklapaju sa ovom tackomg
     *
     * @return komparator
     */
    public Comparator<Point> polarOrder() {
        return new PolarOrder();
    }

	
	/**
     * Uproedjuje dve tacke po x koord.
     */
    public static final Comparator<Point> X_ORDER = new XOrder();

    /**
     * Uproedjuje dve tacke po y koord.
     */
    public static final Comparator<Point> Y_ORDER = new YOrder();
	
	/**
	 * Konstruktor.
	 * @param x x koordinata tacke (0, 0) je u gornjem levom uglu.
	 * @param y y koordinata tacke (0, 0) je u gornjem levom uglu.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Kvadrat Euklidovog rastojanje do tacke.
	 * @param rhs tacka do koje se racuna rastojanje.
	 * @return kvadrat rastojanja.
	 */
	public int distanceTo(Point rhs) {
		return (rhs.getX()-this.x)*(rhs.getX()-this.x) + (rhs.getY()-this.y)*(rhs.getY()-this.y);
	}
	
	/**
	 * Euklidovo rastojanje do tacke.
	 * @param rhs tacka do koje se racuna rastojanje.
	 * @return rastojanj.
	 */
	public double distanceToSQRT(Point rhs) {
		return Math.sqrt(this.distanceTo(rhs));
	}
	
	/**
     * Uporedjuje tacke po y-koordinati, pa po x-koordinati.
     * 
     * @param  that druga tacka
     * @return -1 ako je prva tacka "manja", +1 ako je "veca", 0 ako su koincidentne tacke. 
     */
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

	/**
	 * Crta tacku na grafici g.
	 * @param g graphics nad kom se crta tacka.
	 */
	public void draw(Graphics2D g) {
		g.fillOval(x, y, POINT_SIZE, POINT_SIZE);
	}
	
 	public int norm() {
 		return x * x + y * y;
 	}
 	
 	public int cross(Point p) {
		return x * p.y - y * p.x;
	}
 	
 	public Point subtract(Point p) {
		return new Point(x - p.x, y - p.y);
	}
    
	private int x;
	private int y;

	// uporedjuje tacke po x-koordinati
    private static class XOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            if (p.x < q.x) return -1;
            if (p.x > q.x) return +1;
            return 0;
        }
    }

    // uproedjuje tacke po y-koordinati
    private static class YOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            if (p.y < q.y) return -1;
            if (p.y > q.y) return +1;
            return 0;
        }
    }
    
    // uporedjuje tacke po luku (0-2pi) koji zaklapaju sa zadatom tackom
    private class PolarOrder implements Comparator<Point> {
        public int compare(Point q1, Point q2) {
            int dx1 = q1.x - x;
            int dy1 = q1.y - y;
            int dx2 = q2.x - x;
            int dy2 = q2.y - y;

            if      (dy1 >= 0 && dy2 < 0) return -1;    // q1 iznad; q2 ispod
            else if (dy2 >= 0 && dy1 < 0) return +1;    // q1 ispod; q2 iznad
            else if (dy1 == 0 && dy2 == 0) {            // 3-kolinearni
                if      (dx1 >= 0 && dx2 < 0) return -1;
                else if (dx2 >= 0 && dx1 < 0) return +1;
                else                          return  0;
            }
            int cross = Vectors.crossProduct(Point.this, q1, q2);
            return cross < 0 ? +1 : -1; // Moguci BUG?  
        }
    }

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public void drawFX(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.fillOval(x, y, POINT_SIZE, POINT_SIZE);
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
		this.x += dx;
		this.y += dy;
	}
}
