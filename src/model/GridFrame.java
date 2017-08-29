package model;

import javax.swing.JFrame;

public class GridFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public GridFrame() {
		// all the print functions are for debugging
		GridComponent gridC = new GridComponent();
		gridC.sqGrid.get(5).get(5).setActivity(true);
		System.out.println(gridC.sqGrid.get(5).get(5).toString());
		System.out.println(gridC.sqGrid.get(0).get(0).toString());
		System.out.println(gridC.sqGrid.get(4).get(9).toString());
		System.out.println(gridC.sqGrid.get(3).get(7).toString());
		add(gridC);
		pack();
		setLocationRelativeTo(null);

		gridC.printGrid();

	}
}
