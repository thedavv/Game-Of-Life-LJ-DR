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
import java.util.HashSet;

import javax.swing.JComponent;

public class GridComponent extends JComponent {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	private static final int DEFAULT_SQUARE_SIZE = 10;
	private int width;
	private int height;
	private int sideLength;

	private ArrayList<ArrayList<LifeSquare>> sqGrid;
	private ArrayList<ArrayList<LifeSquare>> sqGridTemp;
	private HashSet<LifeSquare> activeSqs;
	// private ArrayList<LifeSquare> trackedSqs; //TODO future feature
	private LifeSquare currentSquare;
	// private LifeSquare currentFilledSquare;

	/**
	 * Creates a 10x10 grid.
	 */
	public GridComponent() {
		// note: all ArrayLists are size 10 by default
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		sideLength = DEFAULT_SQUARE_SIZE;

		activeSqs = new HashSet<>();
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
		width = (sizeX * 10) + 100; // +100 for debugging purpose
		height = (sizeY * 10) + 100; // +100 for debugging purpose
		sideLength = DEFAULT_SQUARE_SIZE;

		activeSqs = new HashSet<>();
		sqGrid = new ArrayList<ArrayList<LifeSquare>>(sizeX);
		sqGridTemp.add(new ArrayList<LifeSquare>());

		for (int x = 0; x < sizeX; x++) {
			sqGrid.add(new ArrayList<LifeSquare>());
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
				if (lsq.isAlive()) {
					g2.setColor(Color.YELLOW);
					g2.fill(lsq);
					g2.setColor(Color.BLACK);
					g2.draw(lsq);
				} else {
					g2.setColor(Color.BLACK);
					g2.draw(lsq);
				}
			}
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
	 * Return the number of <b>active</b> neighbors of the given square,
	 * diagonal inclusive.
	 * 
	 * @param lsq
	 *            the <code>LifeSquare</code> in question
	 * @return integer {0-8}
	 */
	public int numOfActiveNeighbors(LifeSquare lsq) {
		int count = 0;

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				try {
					if (i == 0 & j == 0) {
						// do nothing (e.i. the square would equal the lsq)
					} else if (this.sqGrid.get((lsq.getCoorX()) + i).get((lsq.getCoorY()) + j).isAlive()) {
						count++;
					}
				} catch (IndexOutOfBoundsException e) {
					// catches if the lsq is on one of the edges of the grid
					// do nothing (no square exists at such a position
					//e.printStackTrace();
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


	/**
	 * Adapter class for Mouse actions. Action include mouse clicked, mouse
	 * pressed, mouse moved, mouse draged
	 */
	class MouseEventHandler extends MouseAdapter {

		// change active square from grid to inactive and remove active square
		// from active squares when clicked 2+ times
		@Override
		public void mouseClicked(MouseEvent e) {
			currentSquare = findSquare(e.getPoint());
			if (currentSquare != null && currentSquare.isAlive() && e.getClickCount() >= 2) {
				// TODO remove test
				// TODO remove active if not needed
				currentSquare.setAlive(false);
				System.out.println(
						"changed Grid rectangle at point to inactive" + e.getPoint() + " " + currentSquare.toString());
				// System.out.println(
				// "removed activeSqs rectangle at point " + e.getPoint() + " "
				// + currentSquare.toString());
				// removeActiveSquareOnrid(currentSquare);
				repaint();
			}
		}

		// set activyty to presed title and add active square to actives
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO remove test
			currentSquare = findSquare(e.getPoint());
			if (currentSquare != null && !currentSquare.isAlive()) {
				// TODO remove test
				// TODO remove remove if not needed
				currentSquare.setAlive(true);
				// addActiveSquareOnGrid(currentSquare);
				System.out.println(
						"changed Grid rectangle at point to active" + e.getPoint() + " " + currentSquare.toString());
				// System.out
				// .println("added activeSqs rectangle at point " + e.getPoint()
				// + " " + currentSquare.toString());
				repaint();
			}
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

			if (currentSquare != null && !currentSquare.isAlive()) {
				currentSquare.setAlive(true);
			}
			repaint();
		}
	}
}
