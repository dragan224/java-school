import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame {
	private static Window instance = null;
	private CartesianPanel panel = new CartesianPanel();
	private TopFrame frame = new TopFrame();
	
	public static Window getInstance() {
		if (instance == null){
			instance = new Window();
		}
		return instance;
	}
	
	public TopFrame getFrame() {
		return frame;
	}
	
	public CartesianPanel getPanel() {
		return panel;
	}
	
	private Window() {
		this.setSize(700, 800);
		this.setLocationRelativeTo(null);
		
		this.add(frame, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
