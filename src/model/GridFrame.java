package model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GridFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final List<String> menuItemsNames = Arrays.asList("Patterns...", "Start", "Stop", "Clear",
			"Exit Application");

	// create components
	private GridComponent gridC = new GridComponent(60, 60);
	private ControlPanel controlP = new ControlPanel(600, this);
	private PatternsPanel paternP = new PatternsPanel();

	// create menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Tools");
	private JMenuItem patterns = new JMenuItem(menuItemsNames.get(0));
//	private JMenuItem start = new JMenuItem(menuItemsNames.get(1));
//	private JMenuItem stop = new JMenuItem(menuItemsNames.get(2));
//	private JMenuItem clear = new JMenuItem(menuItemsNames.get(3));
//	private JMenuItem exit = new JMenuItem(menuItemsNames.get(4));

	public GridFrame() {
		// adding components to frame
		menu.add(patterns);
		menuBar.add(menu);
		setJMenuBar(menuBar);

		add(gridC, BorderLayout.NORTH);
		add(controlP, BorderLayout.CENTER);

		// frame setup
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setResizable(false);

		// test grid
		// gridC.printGrid();

		// adding action listeners
		//addMenuEventsToGridFrame();
	}


	/**
	 * Method for adding menu listeners to GridFrame
	 */
	private void addMenuEventsToGridFrame() {
		patterns.addActionListener((ActionEvent e) -> {
			new PatternsPanel();
		});
//
//		start.addActionListener((ActionEvent e) -> {
//			// TODO dokoncit
//		});
//
//		stop.addActionListener((ActionEvent e) -> {
//			// TODO dokoncit
//		});
//
//		exit.addActionListener((ActionEvent e) -> {
//			dispose();
//		});
//
//		// TODO dokoncit pre druhe pole
//		clear.addActionListener((ActionEvent e) -> {
//			gridC.resetGrid(gridC.getSqGrid());
//			gridC.resetGrid(gridC.getSqGridTemp());
//			gridC.repaint();
//		});
	}

	// G + S
	public GridComponent getGridC() {
		return gridC;
	}
}
