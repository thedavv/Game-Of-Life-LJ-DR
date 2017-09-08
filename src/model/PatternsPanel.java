package model;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class PatternsPanel extends JPanel {
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 600;
	
	private String[] stillLifePatterns = { "Block", "Beehive", "Loaf", "Boat", "Tub" };
	private String[] oscilatorPatterns = { "Blinker(period 2)", "Toad(period 2)", "Beacon(period 2)", "Pulsar(period 3)",
			"Pentadecathon(15 period)" };
	private String[] spaceshipPatterns = {"Glider", "Lightweight spaceship(LWSS)"};
	private String[] breederPatterns = {"Gosper glider gun"};
	
	private JComboBox<String> patternChooserComboBox = new JComboBox<>();
	private JRadioButton stillLifeRadioButton = new JRadioButton("Still Life Patterns");
	private JRadioButton oscilatorRadioButton = new JRadioButton("Oscilator Patterns");
	private JRadioButton spaceshipRadioButton = new JRadioButton("Spaceship Patterns");
	private JRadioButton breederRadioButton = new JRadioButton("Breeder patterns");
	
	JList<String> list = new JList(stillLifePatterns); 
	JScrollPane listScroller = new JScrollPane(list);
	
	public PatternsPanel(){
		patternChooserComboBox.setModel(new DefaultComboBoxModel<>(stillLifePatterns));
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		
		listScroller.setPreferredSize(new Dimension(250, 80));
		add(patternChooserComboBox);
		
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
