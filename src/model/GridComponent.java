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

	ArrayList<ArrayList<LifeSquare>> sqGrid;
	private HashSet<LifeSquare> activeSqs;
	// private ArrayList<LifeSquare> trackedSqs; //TODO future feature
	LifeSquare currentSquare;
	LifeSquare currentFilledSquare;

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

		for (int x = 0; x < 10; x++) {
			sqGrid.add(new ArrayList<LifeSquare>());
			for (int y = 0; y < 10; y++) {
				sqGrid.get(x).add(new LifeSquare(x * sideLength, y * sideLength, x, y, sideLength, sideLength));
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

		for (int x = 0; x < sizeX; x++) {
			sqGrid.add(new ArrayList<LifeSquare>());
			for (int y = 0; y < sizeY; y++) {
				sqGrid.get(x).add(new LifeSquare(x * sideLength, y * sideLength, x, y, sideLength, sideLength));
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

	// potentially too resource demanding?
	// TODO tieto dve metody dat do interface a mat dve implementacie
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
	// TODO remove later if not needed
	// pre najdenie life squares
	// public LifeSquare findActiveSquare(Point2D point) {
	// for (LifeSquare lsq : activeSqs) {
	// if (lsq.contains(point)) {
	// return lsq;
	// }
	// }
	// System.out.println("null");
	// return null;
	// }

	public void setActive(LifeSquare lsq) {
		lsq.setActivity(true);
		activeSqs.add(lsq);
		repaint();
	}

	public void setInactive(LifeSquare lsq) {
		lsq.setActivity(false);
		activeSqs.remove(lsq);
		repaint();
	}

	// TODO pozriet sa na to este raz
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
	 * Method for adding selected LifeSquare to activeSqs
	 * 
	 * @param lsq
	 *            is the curent LifeSquare
	 */
	@Deprecated
	public void addActiveSquareOnGrid(LifeSquare lsq) {
		double x = lsq.getX();
		// TODO remove tests
		System.out.println(x + "X coordinate");
		double y = lsq.getY();
		System.out.println(y + "Y coordinate");

		currentFilledSquare = new LifeSquare((int) x, (int) y, DEFAULT_SQUARE_SIZE, DEFAULT_SQUARE_SIZE, true);
		activeSqs.add(currentFilledSquare);
	}

	/**
	 * Method that removes selected LifeSquare from activeSqs
	 * 
	 * @param lsq
	 *            is the curent LifeSquare
	 */
	@Deprecated
	public void removeActiveSquareOnrid(LifeSquare lsq) {
		if (lsq == currentFilledSquare)
			currentFilledSquare = null;
		activeSqs.remove(lsq);
	}

	class MouseEventHandler extends MouseAdapter {

		// change active square from grid to inactive and remove active square
		// from active squares when clicked 2+ times
		@Override
		public void mouseClicked(MouseEvent e) {
			currentSquare = findSquare(e.getPoint());
			if (currentSquare != null && currentSquare.isAlive() && e.getClickCount() >= 2) {
				// TODO remove test
				// TODO remove active if not needed
				currentSquare.setActivity(false);
				System.out.println(
						"changed Grid rectangle at point to inactive" + e.getPoint() + " " + currentSquare.toString());
				// vSystem.out.println(
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
				currentSquare.setActivity(true);
				// addActiveSquareOnGrid(currentSquare);
				System.out.println(
						"changed Grid rectangle at point to active" + e.getPoint() + " " + currentSquare.toString());
				// System.out
				// .println("added activeSqs rectangle at point " + e.getPoint()
				// + " " + currentSquare.toString());
				repaint();
			}
		}

		// TODO nefacha
		@Override
		public void mouseMoved(MouseEvent e) {
			currentSquare = findSquare(e.getPoint());
			// TODO remove test
			// System.out.println("square found at : " + currentSquare);
			if (currentSquare != null && currentSquare.isAlive()) {
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			} else {
				setCursor(Cursor.getDefaultCursor());
			}
		}
	}
}
