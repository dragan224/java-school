package geometrija_main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.text.Segment;

import algorithms_api.ClosestPoints;
import algorithms_api.ConvexHull;
import algorithms_api.RectangleIntersection;
import algorithms_api.Triangulation;
import data_types.Point;
import data_types.Rectangle;
import data_types.Triangle;

public class Canvas extends JPanel {
	
	public int view = 0;
	int N_POINTS = 100;
	static final int PADDING_X = 100;
	static final int PADDING_Y = 50;
	
	@Override
	protected void paintComponent(Graphics gg) {
		// TODO Auto-generated method stub
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D) gg;
		
		if (view == 0) { // input
			for (Point pt: input) {
				pt.draw(g);
			}
		} else if (view == 1) { // konveksni omotac
			for (Point pt: input) {
				pt.draw(g);
			}
			ConvexHull chull = new ConvexHull(input);
			ArrayList<Point> polygon = new ArrayList<>();
			for (Point p: chull.hull()) {
				polygon.add(p);
			}
			g.setColor(Color.RED);
			new data_types.Polygon(polygon).draw(g);
		} else if (view == 2) { // najblize tacke
			for (Point pt: input) {
				pt.draw(g);
			}
			g.setColor(Color.RED);
			ClosestPoints cp = new ClosestPoints(input);
			new data_types.Segment(cp.either(), cp.other()).draw(g);
//			g.setColor(Color.BLUE);
			cp.either().draw(g);
			cp.other().draw(g);
		} else if (view == 3) { // triangulacija 
			g.setColor(Color.BLUE);
			ArrayList<Triangle> triangles = new Triangulation(input).getTriangles();
			for (Point pt: input) {
				pt.draw(g);
			}
			g.setColor(Color.BLACK);
			for (Triangle t: triangles) {
				t.draw(g);
			}
		} else if (view == 4) { // presek n pravougaonika
			
			// Treba implementirati random generisanje ovih stvari na bolji nacin..
			ArrayList<Rectangle> rectangles = new ArrayList<>();
			Random rng = new Random();
			
			for (int i = 0; i < N_POINTS/4; i++) {
				int dx = rng.nextInt(this.getWidth()/3);
				int dy = rng.nextInt(this.getHeight()/3);
				rectangles.add(new Rectangle(new Point(250-dx, 250-dy),
											 new Point(400+dx, 250-dy),
											 new Point(400+dx, 400+dy),
											 new Point(250-dx, 400+dy)));
			}
			for (Rectangle r: rectangles) {
				r.draw(g);
			}
			
			g.setColor(Color.BLUE);
			Rectangle intersection = RectangleIntersection.interectionN(rectangles);
//			intersection.draw(g);
//			g.
			
			// Ovaj deo verovatno moze se zameniti sa interection.draw(g) ali nece da ga popuni
//			intersection.draw(g);
			int x = intersection.getA().getX();
			int y = intersection.getA().getY();
			int w = Math.abs(intersection.getA().getX() - intersection.getB().getX());
			int h = Math.abs(intersection.getA().getY() - intersection.getD().getY());
			g.fillRect(x, y, w, h);
		}
	}
	
	public void regenerateInput() {
		input.clear();
		Random rng = new Random();
		for (int i = 0; i < N_POINTS; i++) {
			int w = this.getWidth() - PADDING_X;
			int h = this.getHeight() - PADDING_Y;
			input.add(new Point(rng.nextInt(w) + PADDING_X/2, rng.nextInt(h) + PADDING_Y/2));
		}
	}
	
	private ArrayList<Point> input = new ArrayList<>();
}
