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

	private GridComponent gridJComponent = new GridComponent(120, 70);
	private ControlPanel controlPanel = new ControlPanel(600, this);
	private JPanel gridPanel = new JPanel();
	private Box box = new Box(BoxLayout.Y_AXIS);

	// TODO zmenit nazov
	private PatternsDialog patternDialog = new PatternsDialog(gridJComponent);
	// create menu components
	private JMenuBar jMenuBar = new JMenuBar();
	private JMenu optionsJMenu = new JMenu("Options");
	private JMenu patternsMenu = new JMenu("Patterns");
	private JMenuItem exitJMenuItem = new JMenuItem("Exit");
	private JMenuItem patternsJMenuItem = new JMenuItem("Patter Chooser...");
	private JMenuItem removeStoredPatternJMenuItem = new JMenuItem("Remove Stored Pattern");

	public GridFrame() {
		addMenu();

		// Box box = new Box(BoxLayout.Y_AXIS);
		// box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		// box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		// box.add(Box.createGlue());
		// box.add(gridJComponent);
		// add(box, BorderLayout.CENTER);
		// add(controlPanel, BorderLayout.SOUTH);

		// X add(grigJComponent, BorderLayout.CENTER);
		// X add(controlJPanel, BorderLayout.SOUTH);

		add(controlPanel, BorderLayout.SOUTH);
		gridPanel.add(gridJComponent);
		add(box, BorderLayout.CENTER);
		box.add(Box.createVerticalGlue());
		box.add(gridPanel);

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

		exitJMenuItem.setMnemonic(KeyEvent.VK_W);
		exitJMenuItem.setToolTipText("Exits the application");
		exitJMenuItem.addActionListener(e -> System.exit(0));

		patternsJMenuItem.setMnemonic(KeyEvent.VK_P);
		patternsJMenuItem.setToolTipText("Opens Pattern chooser dialog");
		patternsJMenuItem.addActionListener(e -> patternDialog.setVisible(true));

		removeStoredPatternJMenuItem.setMnemonic(KeyEvent.VK_R);
		removeStoredPatternJMenuItem.setToolTipText("Removes current stored pattern");
		removeStoredPatternJMenuItem.addActionListener(e -> gridJComponent.getStoredPatternPositons().clear());

		patternsMenu.add(patternsJMenuItem);
		patternsMenu.add(removeStoredPatternJMenuItem);
		patternsJMenuItem.setMnemonic(KeyEvent.VK_N);
		optionsJMenu.add(exitJMenuItem);
		optionsJMenu.setMnemonic(KeyEvent.VK_O);

		jMenuBar.add(optionsJMenu);
		jMenuBar.add(patternsMenu);

		setJMenuBar(jMenuBar);
	}

	// G + S
	public GridComponent getGridC() {
		return gridJComponent;
	}
}
