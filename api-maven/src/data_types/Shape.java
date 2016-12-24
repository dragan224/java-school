package data_types;

import java.awt.Graphics2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Interfejs za geometrijsku figuru. 
 * Sve klase u data_types paketu implementiraju ovaj interfejs.
 */
public interface Shape {
	/**
	 * Crtanje u swingu.
	 * @param g graphics2d (ili graphics) swinga.
	 */
	void draw(Graphics2D g);
	
	/**
	 * Crtanje u javaFX-u.
	 * @param gc GraphicsContext javafx-a.
	 */
	void drawFX(GraphicsContext gc);
	
	/**
	 * Racuna obim zadate figure.
	 * @return obim figure.
	 */
	double perimeter();
	
	/**
	 * Racuna povrsinu zadate figure.
	 * @return povrsinu figure.
	 */
	double area();
	
	/**
	 * Translacija
	 * @param dx translacija po x osi.
	 * @param dy translacija po y osi.
	 */
	void translate(int dx, int dy);
}
