package geometrija_main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JFrame {
	public Window() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		this.setTitle("Demo API-a geometrijskih algoritama!");
		this.setLocationRelativeTo(null);
		initRadioButtons();
		this.add(canvas);
		this.setVisible(true);
	}
	
	public Canvas canvas = new Canvas();
	
	private void initRadioButtons() {
		JPanel top = new JPanel(new FlowLayout());
		top.setSize(WIDTH, 200);
		
		final JRadioButton input = new JRadioButton("Input Tacke");
		final JRadioButton omotac = new JRadioButton("Konveksni Omotac");
		final JRadioButton najblizeTacke = new JRadioButton("Najblize Tacke");
		final JRadioButton triangu = new JRadioButton("Delaunay Triangulacija");
		final JRadioButton pravougaonici = new JRadioButton("Presek N pravougaonika");
		
		input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				canvas.regenerateInput();
				canvas.view = 0;
				canvas.repaint();
			}
		});
		
		omotac.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.view = 1;
				canvas.repaint();
			}
		});
		
		najblizeTacke.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				canvas.view = 2;
				canvas.repaint();
			}
		});
		
		triangu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.view = 3;
				canvas.repaint();
			}
		});
		
		pravougaonici.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				canvas.view = 4;
				canvas.repaint();
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		group.add(input);
		group.add(omotac);
		group.add(najblizeTacke);
		group.add(triangu);
		group.add(pravougaonici);
		
		top.add(input);
		top.add(omotac);
		top.add(najblizeTacke);
		top.add(triangu);
		top.add(pravougaonici);
		
		input.setSelected(true);
		
		JSlider numPoints = new JSlider(JSlider.HORIZONTAL, 10, 100, 60);
		
		JLabel N = new JLabel("60");
		
		numPoints.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				 int num_points = ((JSlider)e.getSource()).getValue();
				 canvas.N_POINTS = num_points;
				 N.setText(""+num_points);
				 canvas.regenerateInput();
				 canvas.repaint();
				
			}
		});
		top.add(N);
		top.add(numPoints);
		this.add(top, BorderLayout.NORTH);
	}
}
