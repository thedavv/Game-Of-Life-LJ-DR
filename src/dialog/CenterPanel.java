package dialog;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.GridComponent;

public class CenterPanel extends JPanel {
	private final static int DEFAULT_WIDTH = 510;
	private final static int DEFAULT_HEIGHT = 510;
	private GridComponent gridOfLifeSquares = new GridComponent(50, 50);

	CenterPanel() {
		setBackground(Color.WHITE);
		add(gridOfLifeSquares);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public GridComponent getGrid() {
		return gridOfLifeSquares;
	}

	public void setGrid(GridComponent comp) {
		this.gridOfLifeSquares = comp;
	}

}
