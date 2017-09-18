package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import dialog.PatternsDialog;

public class GridFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	// create components
	private GridComponent grigJComponent = new GridComponent(111, 50);
	private ControlPanel controlJPanel = new ControlPanel(600, this);
	 // TODO zmenit nazov
	private JPanel gridPanel = new JPanel();
	private PatternsDialog patternDialog = new PatternsDialog(); 

	// create menu components
	private JMenuBar jMenuBar = new JMenuBar();
	private JMenu optionsJMenu = new JMenu("Options");
	private JMenu patternsMenu = new JMenu("Patterns");
	private JMenuItem exitJMenuItem = new JMenuItem("Exit");
	private JMenuItem patternsJMenuItem = new JMenuItem("Patter Chooser...");

	public GridFrame() {
		addMenu();

		gridPanel.setSize(grigJComponent.getWidth(), grigJComponent.getHeight());
		gridPanel.add(grigJComponent);
		add(gridPanel, BorderLayout.CENTER);
		add(controlJPanel, BorderLayout.SOUTH);

		// frame setup
		pack();
		setTitle("Game of Life");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setResizable(false);

		// test grid
		// gridC.printGrid();

		// adding action listeners
		// addMenuEventsToGridFrame();
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
		
		optionsJMenu.add(exitJMenuItem);
		patternsMenu.add(patternsJMenuItem);
		optionsJMenu.setMnemonic(KeyEvent.VK_O);
		patternsJMenuItem.setMnemonic(KeyEvent.VK_N);
		
		jMenuBar.add(optionsJMenu);
		jMenuBar.add(patternsMenu);
		
		setJMenuBar(jMenuBar);
	}
	
	// G + S
	public GridComponent getGridC() {
		return grigJComponent;
	}
}
