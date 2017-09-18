package model;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GridFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	// "Start", "Stop", "Clear",
	// "Exit Application");

	// create components
	private GridComponent gridC = new GridComponent(120, 70);
	private ControlPanel controlP = new ControlPanel(600, this);
	Box box = new Box(BoxLayout.Y_AXIS);

	// create menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Options");
	// private JMenuItem step = new JMenuItem(menuItemsNames.get(0));
	// private JMenuItem start = new JMenuItem(menuItemsNames.get(1));
	// private JMenuItem stop = new JMenuItem(menuItemsNames.get(2));
	// private JMenuItem clear = new JMenuItem(menuItemsNames.get(3));
	// private JMenuItem exit = new JMenuItem(menuItemsNames.get(4));

	public GridFrame() {
		// createMenuForGridFrame();
		// setJMenuBar(menuBar);
		addMenu();
		add(controlP, BorderLayout.SOUTH);

		box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		box.add(Box.createGlue());
		add(box, BorderLayout.CENTER);
		box.add(gridC);

		// gridP.setSize(gridC.getWidth(), gridC.getHeight());

		// add(gridC, BorderLayout.CENTER);
		// add(gridP, BorderLayout.CENTER);
		// add(gridC, BorderLayout.NORTH);

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

	private void addMenu() {
		menu.setMnemonic(KeyEvent.VK_O);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_W);
		exitItem.setToolTipText("Exits the application");
		exitItem.addActionListener(e -> System.exit(0));

		menu.add(exitItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}

	/**
	 * Create Menu for GridFrame
	 */
	// private void createMenuForGridFrame() {
	// menuBar.add(menu);
	// menu.add(step);
	// menu.addSeparator();
	// menu.add(start);
	// menu.add(stop);
	// menu.add(clear);
	// menu.addSeparator();
	// menu.add(exit);
	// }

	/**
	 * Method for adding menu listeners to GridFrame
	 */
	// private void addMenuEventsToGridFrame() {
	// step.addActionListener((ActionEvent e) -> {
	// gridC.setSqGridTemp(gridC.createNextGeneration(gridC.getSqGrid(),
	// gridC.getSqGridTemp()));
	// gridC.setSqGrid(gridC.setNextGenerationAsCurrentGeneration(gridC.getSqGrid(),
	// gridC.getSqGridTemp()));
	// gridC.resetGrid(gridC.getSqGridTemp());
	// gridC.repaint();
	// });
	//
	// start.addActionListener((ActionEvent e) -> {
	// // TODO dokoncit
	// });
	//
	// stop.addActionListener((ActionEvent e) -> {
	// // TODO dokoncit
	// });
	//
	// exit.addActionListener((ActionEvent e) -> {
	// dispose();
	// });
	//
	// // TODO dokoncit pre druhe pole
	// clear.addActionListener((ActionEvent e) -> {
	// gridC.resetGrid(gridC.getSqGrid());
	// gridC.resetGrid(gridC.getSqGridTemp());
	// gridC.repaint();
	// });
	// }

	// G + S
	public GridComponent getGridC() {
		return gridC;
	}
}
