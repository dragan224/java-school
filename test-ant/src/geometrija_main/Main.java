package geometrija_main;

import java.awt.Rectangle;

import data_types.Circle;
import data_types.Point;
import data_types.Triangle;

public class Main {

	public static void main(String[] args) {
		Window w = new Window();
		w.canvas.regenerateInput();
		w.repaint();
		
		//Prikaz obima, povrsine, translacije
		// Za detalje i svaki oblik videti operations_test paket
		Triangle t = new Triangle(new Point(0, 0), new Point(10, 0), new Point(0, 10));
		System.out.println("Povrsina trougla = " + t.area());
		System.out.println("Obim trougla = " + t.perimeter());
		
		data_types.Rectangle r = new data_types.Rectangle(new Point(-1, -5), new Point(9, -5), new Point (9, 5), new Point(-1, 5));
		System.out.println("Povrsina pravougaonika = " + r.area());
		System.out.println("Obim pravougaonika = " + r.perimeter());
		
		Circle c = new Circle(new Point(0, 0), 5);
		System.out.println("Povrsina kruga = " + c.area());
		System.out.println("Obim kruga = " + c.perimeter());
		
		Point A = new Point(0, 0);
		Point A_T = new Point(0, 0);
		A_T.translate(-3, 4);
		System.out.println("Translacija " + A + " u " + A_T);
		
		Point B = new Point(-4, 5);
		Point B_T = new Point(-4, 5);
		B_T.translate(1, 2);
		System.out.println("Translacija " + B + " u " + B_T);
		
	}

}
