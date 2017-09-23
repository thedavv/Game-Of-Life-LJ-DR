package model;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import dialog.PatternsDialog;

public class GridFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	// TODO is public needed?
	private GridComponent gridComponent = new GridComponent(120, 70, this);
	private ControlPanel controlPanel = new ControlPanel(600, this);
	private JPanel gridPanel = new JPanel();

	private Box gridBox = new Box(BoxLayout.Y_AXIS);
	private Box southBox = new Box(BoxLayout.Y_AXIS);
	public StatusPanel statusPanel = new StatusPanel();

	// TODO zmenit nazov
	private PatternsDialog patternDialog = new PatternsDialog(gridComponent);
	// create menu components
	private JMenuBar jMenuBar = new JMenuBar();
	private JMenu optionsJMenu = new JMenu("Options");
	private JMenu patternsMenu = new JMenu("Patterns");
	private JMenuItem exitJMenuItem = new JMenuItem("Exit");
	private JMenuItem patternsJMenuItem = new JMenuItem("Patter Chooser...");
	private JMenuItem removeStoredPatternJMenuItem = new JMenuItem("Remove Stored Pattern");

	public GridFrame() {
		addMenu();
		addSouthPanel();

		// Box box = new Box(BoxLayout.Y_AXIS);
		// box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		// box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		// box.add(Box.createGlue());
		// box.add(gridJComponent);
		// add(box, BorderLayout.CENTER);
		// add(controlPanel, BorderLayout.SOUTH);

		// X add(grigJComponent, BorderLayout.CENTER);
		// X add(controlJPanel, BorderLayout.SOUTH);

		// add(controlPanel, BorderLayout.SOUTH);
		add(southBox, BorderLayout.SOUTH);
		gridPanel.add(gridComponent);
		gridBox.add(Box.createVerticalGlue());
		gridBox.add(gridPanel);
		add(gridBox, BorderLayout.CENTER);

		pack();
		setTitle("Game of Life");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// comment out
		// setResizable(false);
	}

	// creates a menu with sub menus
	// sets MenuBar for frame
	private void addMenu() {
		exitJMenuItem.setMnemonic(KeyEvent.VK_E);
		exitJMenuItem.setToolTipText("Exits the application");
		exitJMenuItem.addActionListener(e -> System.exit(0));

		patternsJMenuItem.setMnemonic(KeyEvent.VK_C);
		patternsJMenuItem.setToolTipText("Opens Pattern chooser dialog");
		patternsJMenuItem.addActionListener(e -> patternDialog.setVisible(true));

		removeStoredPatternJMenuItem.setMnemonic(KeyEvent.VK_R);
		removeStoredPatternJMenuItem.setToolTipText("Removes current stored pattern");
		removeStoredPatternJMenuItem.addActionListener(e -> gridComponent.getStoredPatternPositons().clear());

		patternsMenu.add(patternsJMenuItem);
		patternsMenu.add(removeStoredPatternJMenuItem);
		patternsMenu.setMnemonic(KeyEvent.VK_N);

		optionsJMenu.add(exitJMenuItem);
		optionsJMenu.setMnemonic(KeyEvent.VK_O);

		jMenuBar.add(optionsJMenu);
		jMenuBar.add(patternsMenu);

		setJMenuBar(jMenuBar);
	}

	private void addSouthPanel() {
		southBox.add(controlPanel);
		southBox.add(statusPanel);

		// bar1.setSize(10, statusPanel.getHeight());
		// statusPanel.setBackground(Const.PALE_GRAY);
		// statusPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());

	}

	// G + S
	public GridComponent getGridC() {
		return gridComponent;
	}
}
