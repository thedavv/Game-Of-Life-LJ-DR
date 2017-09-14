package model;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class PatternsDialog extends JDialog {
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 600;

	private String[] paternTypes = {"Still Life", "Oscilators", "Spaceships", "Breeders"};
	private String[] stillLifePatterns = { "Block", "Beehive", "Loaf", "Boat", "Tub" };
	private String[] oscilatorPatterns = { "Blinker(period 2)", "Toad(period 2)", "Beacon(period 2)",
			"Pulsar(period 3)", "Pentadecathon(15 period)" };
	private String[] spaceshipPatterns = { "Glider", "Lightweight spaceship(LWSS)" };
	private String[] breederPatterns = { "Gosper glider gun" };

//	private JComboBox<String> patternChooserComboBox = new JComboBox<>();
//	private JRadioButton stillLifeRadioButton = new JRadioButton("Still Life Patterns");
//	private JRadioButton oscilatorRadioButton = new JRadioButton("Oscilator Patterns");
//	private JRadioButton spaceshipRadioButton = new JRadioButton("Spaceship Patterns");
//	private JRadioButton breederRadioButton = new JRadioButton("Breeder patterns");
	
	private JList<String> list = new JList<>(stillLifePatterns);
	
	private JButton selectButton = new JButton("Select");
	private JButton cancelButton = new JButton("Cancel");
	
	private JComboBox paternTypesCombobox = new JComboBox(paternTypes);

	public PatternsDialog(final JFrame frame, boolean modal) {
		super(frame, modal);

		JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("test");
        label.setLabelFor(list);
        listPane.add(label);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(selectButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(cancelButton);

        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = getContentPane();
        contentPane.add(listPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);

        //Initialize values.
        pack();
        setLocationRelativeTo(frame);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
