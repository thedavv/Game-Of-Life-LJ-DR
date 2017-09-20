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

public class GridFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private GridComponent gridComponent = new GridComponent(120, 70);
	private ControlPanel controlPanel = new ControlPanel(600, this);
	private JPanel gridPanel = new JPanel();
	Box box = new Box(BoxLayout.Y_AXIS);

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Options");

	public GridFrame() {
		addMenu();

		add(controlPanel, BorderLayout.SOUTH);
		gridPanel.add(gridComponent);
		// add(gridPanel, BorderLayout.CENTER);

		// box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		// box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		add(box, BorderLayout.CENTER);
		box.add(Box.createVerticalGlue());
		box.add(gridPanel);
		// box.add(Box.createVerticalGlue());

		// add(gridComponent, BorderLayout.NORTH);
		// box.add(controlPanel);

		pack();
		setTitle("Game of Life");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setResizable(false);
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
		return gridComponent;
	}
}
