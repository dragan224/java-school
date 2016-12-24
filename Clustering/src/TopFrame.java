import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TopFrame extends JPanel {
	JSlider slider = new JSlider(0, 9, 0);
	
	JButton kmeans = new JButton("k-means");
	JButton hierarchical = new JButton("hierarchical");
	JButton random = new JButton("random");
	
	public TopFrame() {
		this.setLayout(new GridLayout(1, 2));
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());

		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
			    if (!source.getValueIsAdjusting()) {
			        try {
						setPanelPoints(readPoints(source.getValue()));
						Window.getInstance().getPanel().repaint();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
				
			}
		});
		
		top.setBorder(new EmptyBorder(0, 10, 0, 10));
		top.add(new JLabel("Ulaz:"), BorderLayout.WEST);
		top.add(slider);
		this.add(top);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		bottom.add(new JLabel("Algoritam:"), BorderLayout.WEST);
		JPanel bottomButtons = new JPanel();
		bottom.add(bottomButtons);
		
		bottomButtons.setLayout(new GridLayout(1, 3, 10, 0));
		bottomButtons.setBorder(new EmptyBorder(10, 10, 10, 10));
		bottomButtons.add(kmeans);
		bottomButtons.add(hierarchical);
		bottomButtons.add(random);
		
		bottom.setBorder(new EmptyBorder(0, 0, 0, 10));
		this.add(bottom);
		
		random.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CartesianPanel instance = Window.getInstance().getPanel();
				instance.setPoints(ClusteringAlgorithms.randomAlgorithm(instance.getPoints()));
				instance.repaint();
			}
		});
		
		hierarchical.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inp= JOptionPane.showInputDialog("Unesite broj grupa od 2 do 8.");
				try {
					int k = Integer.parseInt(inp);
					if (k >= 2 && k <= 8) {
						CartesianPanel instance = Window.getInstance().getPanel();
						instance.setPoints(ClusteringAlgorithms.hierarchicalAlgorithm(instance.getPoints(), k));
						instance.repaint();
					}
				} catch (Exception exc) {
					// TODO: handle exception
				}
				
			}
		});
		
		kmeans.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inp= JOptionPane.showInputDialog("Unesite broj grupa od 2 do 8.");
				try {
					int k = Integer.parseInt(inp);
					if (k >= 2 && k <= 8) {
						CartesianPanel instance = Window.getInstance().getPanel();
						instance.setPoints(ClusteringAlgorithms.kmeansAlgorithm(instance.getPoints(), k));
						instance.repaint();
					}
				} catch (Exception exc) {
					// TODO: handle exception
				}
				
			}
		});
	}
	
	public void start() {
		try {
			setPanelPoints(readPoints(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setPanelPoints(ArrayList<Point> points) {
		Window.getInstance().getPanel().setPoints(points);
	}
	
	private void redrawPanel() {
		Window.getInstance().getPanel().repaint();
	}
	
	private ArrayList<Point> readPoints(int index) throws Exception {
		ArrayList<Point> result = new ArrayList<>();
		Scanner s = new Scanner(new File("input/input_" + index + ".txt"));
		
		while (s.hasNextDouble()) {
			result.add(new Point(s.nextDouble(), s.nextDouble()));
		}
		
		s.close();
		return result;
	}
}
