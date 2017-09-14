package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class PaternsDialog extends JDialog {
	private String[]	  patternTypes		= { "Still Life", "Oscilators", "Spaceships", "Breeders",
			"Paint Your Patern" };
	private String[]	  stillLifePatterns	= { "Block", "Beehive", "Loaf", "Boat", "Tub" };
	private String[]	  oscilatorPatterns	= { "Blinker", "Toad", "Beacon", "Pulsar", "Pentadecathon" };
	private String[]	  spaceshipPatterns	= { "Glider", "LWSS" };
	private String[]	  breederPatterns	= { "Gosper glider gun" };
	private String[][]	  allPatterns		= { stillLifePatterns, oscilatorPatterns, spaceshipPatterns,
			breederPatterns };

	// top panel components
	private JButton		  stillLifeButton	= new JButton(patternTypes[0]);
	private JButton		  oscilatorButton	= new JButton(patternTypes[1]);
	private JButton		  spaceshipButton	= new JButton(patternTypes[2]);
	private JButton		  breaderButton		= new JButton(patternTypes[3]);
	private JButton		  paintPaternButton	= new JButton(patternTypes[4]);
	private JButton[]	  topMenuButtons	= { stillLifeButton, oscilatorButton, spaceshipButton, breaderButton,
			paintPaternButton };

	// left panel components
	private JList<String> list				= new JList<>(stillLifePatterns);
	// right pannel
	private JLabel		  labbel			= new JLabel();
	// center pannel
	private CenterPanel	  centerPanel		= new CenterPanel();
	// botom pannel
	private JButton		  selectButton		= new JButton("Select");
	private JButton		  closeButton		= new JButton("Close");

	// helper
	String				  patternName		= "stillLifePatterns";

	public PaternsDialog() {
		// top panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.setBackground(Color.WHITE);
		topPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		topPanel.add(stillLifeButton);
		topPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		topPanel.add(oscilatorButton);
		topPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		topPanel.add(spaceshipButton);
		topPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		topPanel.add(breaderButton);
		topPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		topPanel.add(paintPaternButton);
		topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		stillLifeButton.setEnabled(false);

		// left panel
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(new FlowLayout());
		leftPanel.add(Box.createRigidArea(new Dimension(4, 10)));
		leftPanel.add(list);
		leftPanel.add(Box.createRigidArea(new Dimension(4, 0)));

		// bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(selectButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		bottomPanel.add(closeButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		// right panel
		JPanel rightPannel = new JPanel();
		rightPannel.add(labbel);
		rightPannel.setBackground(Color.WHITE);

		// container of JDialog
		JPanel container = new JPanel(new BorderLayout());
		container.add(topPanel, BorderLayout.PAGE_START);
		container.add(leftPanel, BorderLayout.LINE_START);
		container.add(centerPanel, BorderLayout.CENTER);
		container.add(rightPannel, BorderLayout.LINE_END);
		container.add(bottomPanel, BorderLayout.PAGE_END);

		// add action listeners
		addActionListenersForBottomPanel();
		addActionListenersForTopPanel();
		//addActionListenersForLeftPanel();

		// set properties for dialog
		add(container);
		pack();
		setVisible(false);
		setResizable(false);
	}

	// action listeners for bottom panel
	private void addActionListenersForBottomPanel() {
		// TODO store result
		selectButton.addActionListener(e -> {
			setVisible(false);
		});

		closeButton.addActionListener(e -> {
			setVisible(false);
			centerPanel.getGrid().resetGrid(centerPanel.getGrid().getSqGrid());
		});
	}

	// action listeners for top panel
	private void addActionListenersForTopPanel() {
		stillLifeButton.addActionListener(e -> {
			setDisabledButton(e);
			list.setListData(stillLifePatterns);
			patternName = "stillLifePatterns";
		});

		oscilatorButton.addActionListener(e -> {
			setDisabledButton(e);
			list.setListData(oscilatorPatterns);
			patternName = "oscilatorPatterns";
		});

		spaceshipButton.addActionListener(e -> {
			setDisabledButton(e);
			list.setListData(spaceshipPatterns);
			patternName = "spaceshipPatterns";
		});

		breaderButton.addActionListener(e -> {
			setDisabledButton(e);
			list.setListData(breederPatterns);
			patternName = "breederPatterns";
		});

		// TODO add free paint + disable paint for other actions
		paintPaternButton.addActionListener(e -> {
			setDisabledButton(e);
			list.setListData(new String[] { "" });
		});
	}
	
	// sets enabled to all top buttons except the clicked one
		private void setDisabledButton(ActionEvent e) {
			Object actionObj = e.getSource();
			if (actionObj instanceof JButton) {
				for (JButton b : topMenuButtons) {
					if (b.equals(actionObj)) {
						b.setEnabled(false);
					} else {
						b.setEnabled(true);
					}
				}
			}
		}

	// action listeners for left panel
	private void addActionListenersForLeftPanel(String patternName) {
		list.addListSelectionListener(e -> {
			System.out.println(list.getSelectedIndex());
			//TODO factory for patterns
			
		});
	}
}
