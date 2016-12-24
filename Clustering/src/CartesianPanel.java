import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

class CartesianPanel extends JPanel {
	
	public static final int X_AXIS_FIRST_X_COORD = 50;
	public static final int X_AXIS_SECOND_X_COORD = 600;
	public static final int X_AXIS_Y_COORD = 600;

	public static final int Y_AXIS_FIRST_Y_COORD = 50;
	public static final int Y_AXIS_SECOND_Y_COORD = 600;
	public static final int Y_AXIS_X_COORD = 50;

	public static final int FIRST_LENGHT = 10;
	public static final int SECOND_LENGHT = 5;

	public static final int ORIGIN_COORDINATE_LENGHT = 6;

	public static final int AXIS_STRING_DISTANCE = 20;
	
	public static final int POINT_HEIGHT = 6;
	public static final int POINT_WIDTH = 6;
	
	ArrayList<Point> points = new ArrayList<>();
	
	public CartesianPanel() {
		this.setOpaque(true);
        this.setBackground(Color.GRAY);
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
		g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD, Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);

		g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT, X_AXIS_Y_COORD - SECOND_LENGHT, X_AXIS_SECOND_X_COORD,
				X_AXIS_Y_COORD);
		g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT, X_AXIS_Y_COORD + SECOND_LENGHT, X_AXIS_SECOND_X_COORD,
				X_AXIS_Y_COORD);

		g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT, Y_AXIS_X_COORD,
				Y_AXIS_FIRST_Y_COORD);
		g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGHT, Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT, Y_AXIS_X_COORD,
				Y_AXIS_FIRST_Y_COORD);

		g2.fillOval(X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
				Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2), ORIGIN_COORDINATE_LENGHT,
				ORIGIN_COORDINATE_LENGHT);

		g2.drawString("X", X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2, X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		g2.drawString("Y", Y_AXIS_X_COORD - AXIS_STRING_DISTANCE, Y_AXIS_FIRST_Y_COORD + AXIS_STRING_DISTANCE / 2);
		g2.drawString("(0, 0)", X_AXIS_FIRST_X_COORD - AXIS_STRING_DISTANCE,
				Y_AXIS_SECOND_Y_COORD + AXIS_STRING_DISTANCE);

		// numerate axis
		int xCoordNumbers = 10;
		int yCoordNumbers = 10;
		int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD) / xCoordNumbers;
		int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD) / yCoordNumbers;

		for (int i = 1; i < xCoordNumbers; i++) {
			g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength), X_AXIS_Y_COORD - SECOND_LENGHT,
					X_AXIS_FIRST_X_COORD + (i * xLength), X_AXIS_Y_COORD + SECOND_LENGHT);
			g2.drawString(Integer.toString(i), X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
					X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		}

		for (int i = 1; i < yCoordNumbers; i++) {
			g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, Y_AXIS_SECOND_Y_COORD - (i * yLength),
					Y_AXIS_X_COORD + SECOND_LENGHT, Y_AXIS_SECOND_Y_COORD - (i * yLength));
			g2.drawString(Integer.toString(i), Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
					Y_AXIS_SECOND_Y_COORD - (i * yLength));
		}
		
		for (Point pt: points) {
			if (pt.getCluster() != -1) {
				g2.setColor(pt.colors[pt.getCluster()]);
			}
			g2.fillOval(getX(pt.getX()), getY(pt.getY()), POINT_WIDTH, POINT_HEIGHT);
		}
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> newPoints) {
		points.clear();
		for (Point pt: newPoints) {
			points.add(pt);
		}
	}
	
	private int getX(double realX) {
		int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD) / 10;
		return (int) (X_AXIS_FIRST_X_COORD + (realX * xLength));
	}
	private int getY(double realY) {
		int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD) / 10;
		return (int) (Y_AXIS_SECOND_Y_COORD - (realY * yLength));
	}
}