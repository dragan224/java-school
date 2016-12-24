import java.awt.Color;
import java.util.Comparator;

public final class Point implements Comparable<Point> {
	private double x;
	private double y;
	private int cluster;
	static final Color colors[] = { // 8
			Color.RED, 
			Color.BLUE, 
			Color.CYAN, 
			Color.GREEN, 
			Color.ORANGE,
			Color.MAGENTA,
			Color.YELLOW,
			Color.PINK
	};
	
	public static final Comparator<Point> X_ORDER = new XOrder();
	 
	public static final Comparator<Point> Y_ORDER = new YOrder();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cluster;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (cluster != other.cluster)
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	public double distanceTo(Point p) {
		double xDistSquared = (this.x - p.getX()) * (this.x - p.getX());
		double yDistSquared = (this.y - p.getY()) * (this.y - p.getY());
		return Math.sqrt(xDistSquared + yDistSquared);
	}
	
	public int getCluster() {
		return cluster;
	}

	public void setCluster(int cluster) {
		this.cluster = cluster;
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		this.cluster = -1;
	}
	
	public Point(double x, double y, int cluster) {
		this.x = x;
		this.y = y;
		this.cluster = cluster;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
    private static class XOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            if (p.x < q.x) return -1;
            if (p.x > q.x) return +1;
            return 0;
        }
    }

    private static class YOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            if (p.y < q.y) return -1;
            if (p.y > q.y) return +1;
            return 0;
        }
    }
    
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }


}
