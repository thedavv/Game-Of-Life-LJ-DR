package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class GridComponent extends JComponent {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 600;
	private static final int DEFAULT_SQUARE_SIZE = 10;
	private int width;
	private int height;
	private int sideLength;

	private ArrayList<ArrayList<LifeSquare>> sqGrid;
	private ArrayList<ArrayList<LifeSquare>> sqGridTemp;
	// private HashSet<LifeSquare> activeSqs;
	// private ArrayList<LifeSquare> trackedSqs; //TODO future feature
	private LifeSquare currentSquare;
	// private LifeSquare currentFilledSquare;

	private List<Integer[]> storedPatternPositons = null;

	/**
	 * Creates a 10x10 grid.
	 */
	public GridComponent() {
		// note: all ArrayLists are size 10 by default
		sideLength = DEFAULT_SQUARE_SIZE;
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;

		// activeSqs = new HashSet<>();
		sqGrid = new ArrayList<ArrayList<LifeSquare>>();
		sqGridTemp = new ArrayList<ArrayList<LifeSquare>>();

		for (int x = 0; x < 10; x++) {
			sqGrid.add(new ArrayList<LifeSquare>());
			sqGridTemp.add(new ArrayList<LifeSquare>());
			for (int y = 0; y < 10; y++) {
				sqGrid.get(x).add(new LifeSquare(x * sideLength, y * sideLength, x, y, sideLength, sideLength));
				sqGridTemp.get(x).add(new LifeSquare(x * sideLength, y * sideLength, x, y, sideLength, sideLength));
			}
		}

		addMouseListener(new MouseEventHandler());
		addMouseMotionListener(new MouseEventHandler());
	}

	/**
	 * Creates a grid of the desired dimensions.
	 * 
	 * @param sizeX
	 *            number of tiles in the horizontal axis
	 * @param sizeY
	 *            number of tiles in the vertical axis
	 */
	public GridComponent(int sizeX, int sizeY) {
		sideLength = DEFAULT_SQUARE_SIZE;
		// + 1 needed to show some edge lines
		width = (sizeX * sideLength) + 1;
		height = (sizeY * sideLength) + 1;

		// width = DEFAULT_WIDTH;
		// height = DEFAULT_HEIGHT;

		// activeSqs = new HashSet<>();
		sqGrid = new ArrayList<ArrayList<LifeSquare>>(sizeX);
		sqGridTemp = new ArrayList<ArrayList<LifeSquare>>(sizeX);

		for (int x = 0; x < sizeX; x++) {
			sqGrid.add(new ArrayList<LifeSquare>());
			sqGridTemp.add(new ArrayList<LifeSquare>());
			for (int y = 0; y < sizeY; y++) {
				sqGrid.get(x).add(new LifeSquare(x * sideLength, y * sideLength, x, y, sideLength, sideLength));
				sqGridTemp.get(x).add(new LifeSquare(x * sideLength, y * sideLength, x, y, sideLength, sideLength));
			}
		}

		addMouseListener(new MouseEventHandler());
		addMouseMotionListener(new MouseEventHandler());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (ArrayList<LifeSquare> array : sqGrid) {
			for (LifeSquare lsq : array) {
				g2.setColor((lsq.isAlive() ? Const.MAT_BLUE : Color.WHITE));
				g2.fill(lsq);
				g2.setColor(Const.PALE_GRAY);
				g2.draw(lsq);
			}
		}
		for (int x = 0; x < width; x += (10 * sideLength)) {
			g2.setColor(Const.DIM_GRAY);
			g2.drawLine(x, 0, x, height);
		}
		for (int y = 0; y < height; y += (10 * sideLength)) {
			g2.setColor(Const.DIM_GRAY);
			g2.drawLine(0, y, width, y);
		}
	}

	/**
	 * Method returns selected LifeSquare at point on screen
	 * 
	 * @param point
	 *            is the Point2D
	 * @return LifeSquare object
	 */
	public LifeSquare findSquare(Point2D point) {
		for (ArrayList<LifeSquare> array : sqGrid) {
			for (LifeSquare lsq : array) {
				if (lsq.contains(point)) {
					return lsq;
				}
			}
		}
		return null;
	}

	/**
	 * Returns the number of <b>active</b> neighbors of the given square,
	 * diagonal inclusive.
	 * 
	 * @param lsq
	 *            the <code>LifeSquare</code> in question
	 * @return integer {0-8}
	 */
	public int numOfActiveNeighbors(LifeSquare lsq) {
		int count = 0;
		int x;
		int y;

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				try {
					x = lsq.getCoorX() + i;
					y = lsq.getCoorY() + j;
					if (RulesForLifeCycle.wrapping) {
						if (x < 0) {
							x = sqGrid.size() - 1;
						} else if (x >= sqGrid.size()) {
							x = 0;
						}
						if (y < 0) {
							y = (sqGrid.get(0).size()) - 1;
						} else if (y >= sqGrid.get(0).size()) {
							y = 0;
						}
					}

					if (i == 0 & j == 0) {
						// do nothing (e.i. this square would equal the lsq)
					} else if (this.sqGrid.get(x).get(y).isAlive()) {
						count++;
					}
				} catch (IndexOutOfBoundsException e) {
					// catches if the lsq is on one of the edges of the grid
					// do nothing (no square exists at such a position)
				}
			}
		}
		return count;
	}

	/**
	 * Prints out the grid as a series of ☒ and ☐.
	 */
	public void printGrid() {

		for (int y = 0; y < this.sqGrid.size(); y++) {
			System.out.printf(y + " ");
			for (int x = 0; x < this.sqGrid.get(0).size(); x++) {
				if (x % 10 == 0 & x != 0) {
					System.out.printf(",");
				}
				if (this.sqGrid.get(x).get(y).isAlive()) {
					System.out.printf("☒ ");
				} else {
					System.out.printf("☐ ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Method that resets the grid of LifeSquares
	 * 
	 * @param grid
	 *            is the ArrayList of LifeSquares
	 */
	public void resetGrid(ArrayList<ArrayList<LifeSquare>> grid) {
		for (ArrayList<LifeSquare> arrayList : grid) {
			for (LifeSquare lifeSquare : arrayList) {
				lifeSquare.setAlive(false);
			}
		}
	}

	/**
	 * Method that computes next generation and stores it in sqGridTemp
	 * 
	 * @param sqGrid
	 *            is the curent generation
	 * @param sqGridTemp
	 *            is the tmporary generation used for computing next generation
	 * @return sqGridTemp that has next generation
	 */
	public ArrayList<ArrayList<LifeSquare>> createNextGeneration(ArrayList<ArrayList<LifeSquare>> sqGrid,
			ArrayList<ArrayList<LifeSquare>> sqGridTemp) {
		int numberOfNeighbors = 0;
		boolean aliveStatus = false;

		for (ArrayList<LifeSquare> arrayList : sqGrid) {
			for (LifeSquare lifeSquare : arrayList) {
				numberOfNeighbors = numOfActiveNeighbors(lifeSquare);
				aliveStatus = RulesForLifeCycle.setGenerationLifeStatus(numberOfNeighbors, lifeSquare);
				sqGridTemp.get(lifeSquare.getCoorX()).get(lifeSquare.getCoorY()).setAlive(aliveStatus);
			}
		}

		return sqGridTemp;
	}

	/**
	 * Method that sets next generation
	 * 
	 * @param sqGrid
	 *            is the grid of current generation
	 * @param sqGridTemp
	 *            is the temp grid that has next generation
	 * @return sqGrid of the new generation
	 */
	public ArrayList<ArrayList<LifeSquare>> setNextGenerationAsCurrentGeneration(
			ArrayList<ArrayList<LifeSquare>> sqGrid, ArrayList<ArrayList<LifeSquare>> sqGridTemp) {

		for (ArrayList<LifeSquare> arrayList : sqGrid) {
			for (LifeSquare lifeSquare : arrayList) {
				lifeSquare.setAlive(sqGridTemp.get(lifeSquare.getCoorX()).get(lifeSquare.getCoorY()).isAlive());
			}
		}

		return sqGrid;
	}

	/**
	 * Adapter class for Mouse actions. Action include mouse clicked, mouse
	 * pressed, mouse moved, mouse draged
	 */
	class MouseEventHandler extends MouseAdapter {

		// change active square from grid to inactive and remove active square
		// from active squares when clicked 2+ times
		// @Override
		// public void mouseClicked(MouseEvent e) {

		// currentSquare = findSquare(e.getPoint());
		// if (currentSquare != null && currentSquare.isAlive() &&
		// e.getClickCount() >= 2) {
		// currentSquare.setAlive(false);
		// }
		// repaint();
		// }

		// set activyty to presed title and add active square to actives
		@Override
		public void mousePressed(MouseEvent e) {
			currentSquare = findSquare(e.getPoint());
			// no patern stored
			if (currentSquare != null && (storedPatternPositons == null || storedPatternPositons.isEmpty())) {
				if (SwingUtilities.isLeftMouseButton(e) && !currentSquare.isAlive()) {
					currentSquare.setAlive(true);
				} else if (SwingUtilities.isRightMouseButton(e) && currentSquare.isAlive()) {
					currentSquare.setAlive(false);
				}
				// pattern is stored
			} else if (storedPatternPositons != null || !storedPatternPositons.isEmpty()) {
				for (Integer[] arrayList : storedPatternPositons) {
					sqGrid.get(currentSquare.getCoorX() + arrayList[0]).get(currentSquare.getCoorY() + arrayList[1])
							.setAlive(true);
				}
			}
			repaint();
		}

		// if mouse moves to an live square croshair changes
		@Override
		public void mouseMoved(MouseEvent e) {
			currentSquare = findSquare(e.getPoint());

			if (currentSquare != null && currentSquare.isAlive()) {
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			} else {
				setCursor(Cursor.getDefaultCursor());
			}
		}

		// changes alive status of LifeSquare to true
		@Override
		public void mouseDragged(MouseEvent e) {
			currentSquare = findSquare(e.getPoint());
			if (currentSquare != null && !currentSquare.isAlive() && SwingUtilities.isLeftMouseButton(e)) {
				currentSquare.setAlive(true);
			} else if (currentSquare != null && currentSquare.isAlive() && SwingUtilities.isRightMouseButton(e)) {
				currentSquare.setAlive(false);
			}
			repaint();
		}
	}

	// G + S
	public ArrayList<ArrayList<LifeSquare>> getSqGrid() {
		return sqGrid;
	}

	public void setSqGrid(ArrayList<ArrayList<LifeSquare>> sqGrid) {
		this.sqGrid = sqGrid;
	}

	public ArrayList<ArrayList<LifeSquare>> getSqGridTemp() {
		return sqGridTemp;
	}

	public void setSqGridTemp(ArrayList<ArrayList<LifeSquare>> sqGridTemp) {
		this.sqGridTemp = sqGridTemp;
	}

	public List<Integer[]> getStoredPatternPositons() {
		return storedPatternPositons;
	}

	public void setStoredPatternPositons(List<Integer[]> storedPatternPositons) {
		if (storedPatternPositons == null) {
			storedPatternPositons = null;
		} else {
			this.storedPatternPositons = new ArrayList<>(storedPatternPositons);
		}
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
}
