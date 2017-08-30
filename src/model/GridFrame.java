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

	private GridComponent gridC = new GridComponent();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Game Actions");
	private JMenuItem step = new JMenuItem(menuItemsNames.get(0));
	private JMenuItem start = new JMenuItem(menuItemsNames.get(1));
	private JMenuItem stop = new JMenuItem(menuItemsNames.get(2));
	private JMenuItem clear = new JMenuItem(menuItemsNames.get(3));
	private JMenuItem exit = new JMenuItem(menuItemsNames.get(4));

	public GridFrame() {
		

		// for test purposes
		gridC.getSqGrid().get(2).get(5).setActivity(true);
		gridC.getSqGrid().get(0).get(0).setActivity(true);
		gridC.getSqGrid().get(8).get(1).setActivity(true);
		System.out.println(gridC.getSqGrid().get(5).get(5).toString());
		System.out.println(gridC.getSqGrid().get(0).get(0).toString());
		System.out.println(gridC.getSqGrid().get(8).get(1).toString());
		System.out.println(gridC.getSqGrid().get(4).get(9).toString());
		System.out.println(gridC.getSqGrid().get(3).get(7).toString());

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
		
		//TODO dokoncit pre druhe pole
		clear.addActionListener((ActionEvent e) -> {
			gridC.resetGameGrid(gridC.getSqGrid());
		});
	}
}
