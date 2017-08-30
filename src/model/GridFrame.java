package model;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GridFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final List<String> menuItemsNames = Arrays.asList("Step", "Start", "Stop", "Clear",
			"Exit Application");

	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("Game Actions");
	JMenuItem step = new JMenuItem(menuItemsNames.get(0));
	JMenuItem start = new JMenuItem(menuItemsNames.get(1));
	JMenuItem stop = new JMenuItem(menuItemsNames.get(2));
	JMenuItem clear = new JMenuItem(menuItemsNames.get(3));
	JMenuItem exit = new JMenuItem(menuItemsNames.get(4));

	public GridFrame() {
		GridComponent gridC = new GridComponent();

		// for test purposes
		gridC.sqGrid.get(2).get(5).setActivity(true);
		gridC.sqGrid.get(0).get(0).setActivity(true);
		gridC.sqGrid.get(8).get(1).setActivity(true);
		System.out.println(gridC.sqGrid.get(5).get(5).toString());
		System.out.println(gridC.sqGrid.get(0).get(0).toString());
		System.out.println(gridC.sqGrid.get(8).get(1).toString());
		System.out.println(gridC.sqGrid.get(4).get(9).toString());
		System.out.println(gridC.sqGrid.get(3).get(7).toString());

		// adding components to frame
		createMenuForGridFrame();
		setJMenuBar(menuBar);
		add(gridC);
		pack();
		setLocationRelativeTo(null);

		// test grid
		gridC.printGrid();

		// adding action listeners
		addMenuEventsToGridFrame();
	}

	private void createMenuForGridFrame() {
		menuBar.add(menu);
		menu.add(step);
		menu.addSeparator();
		menu.add(start);
		menu.add(stop);
		menu.add(clear);
		menu.addSeparator();
		menu.add(exit);
	}

	private void addMenuEventsToGridFrame() {
		step.addActionListener((ActionEvent e) -> {
			// TODO dokoncit
		});
		start.addActionListener((ActionEvent e) -> {
			// TODO dokoncit
		});
		stop.addActionListener((ActionEvent e) -> {
			// TODO dokoncit
		});
		exit.addActionListener((ActionEvent e) -> {
			dispose();
		});
		clear.addActionListener((ActionEvent e) -> {
			// TODO reset fields
		});
	}
}
