package model;

import java.awt.Rectangle;

public class LifeSquare extends Rectangle {

	private static final long serialVersionUID = 1L;

	/**
	 * The X,Y coordinates of the <code>LifeSquare</code> on the grid.
	 * <b>Note</b>, they differ from the <code>x,y</code> values. Those store
	 * the squares position on the screen.
	 */
	private int coorX;
	private int coorY;

	// NOTE: parent already contains size and X,Y coords
	private boolean alive = false;
	// private int age; //TODO future feature

	LifeSquare() {
	}

	/**
	 * Creates a new <code>LifeSquare</code> with the specified parameters. By
	 * default it's life status is set to inactive.
	 * 
	 * @param x
	 *            the specified X coordinate
	 * @param y
	 *            the specified Y coordinate
	 * @param width
	 *            the width of the <code>LifeSquare</code>
	 * @param height
	 *            the height of the <code>LifeSquare</code>
	 */
	@Deprecated
	public LifeSquare(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.alive = false;
	}

	/**
	 * Creates a new <code>LifeSquare</code> with the specified parameters.
	 * 
	 * @param x
	 *            the specified X coordinate
	 * @param y
	 *            the specified Y coordinate
	 * @param width
	 *            the width of the <code>LifeSquare</code>
	 * @param height
	 *            the height of the <code>LifeSquare</code>
	 * @param active
	 *            the life state of the <code>LifeSquare</code>
	 */
	@Deprecated
	public LifeSquare(int x, int y, int width, int height, boolean active) {
		super(x, y, width, height);
		this.alive = active;
	}

	/**
	 * Creates a new <code>LifeSquare</code> with the specified parameters. By
	 * default it's life status is set to inactive.
	 * 
	 * @param x
	 *            the specified X coordinate on the screen
	 * @param y
	 *            the specified Y coordinate on the screen
	 * @param coorX
	 *            the specified X coordinate on the grid
	 * @param coorY
	 *            the specified X coordinate on the grid
	 * @param width
	 *            the width of the <code>LifeSquare</code>
	 * @param height
	 *            the height of the <code>LifeSquare</code>
	 */

	public LifeSquare(int x, int y, int coorX, int coorY, int width, int height) {
		super(x, y, width, height);
		this.coorX = coorX;
		this.coorY = coorY;
		this.alive = false;
	}

	/**
	 * Creates a new <code>LifeSquare</code> with the specified parameters.
	 * 
	 * @param x
	 *            the specified X coordinate
	 * @param y
	 *            the specified Y coordinate
	 * @param coorX
	 *            the specified X coordinate on the grid
	 * @param coorY
	 *            the specified X coordinate on the grid
	 * @param width
	 *            the width of the <code>LifeSquare</code>
	 * @param height
	 *            the height of the <code>LifeSquare</code>
	 * @param active
	 *            the life state of the <code>LifeSquare</code>
	 */

	public LifeSquare(int x, int y, int width, int coorX, int coorY, int height, boolean active) {
		super(x, y, width, height);
		this.coorX = coorX;
		this.coorY = coorY;
		this.alive = active;
	}

	/**
	 * Enables you to set the "life status" of a <code>LifeSquare</code>. True
	 * means alive, false inactive.
	 * 
	 * @param alive
	 *            desired activity status
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * Returns the life status of the called <code>LifeSquare</code>.
	 * 
	 */
	public boolean isAlive() {
		return alive;
	}

	public int getCoorX() {
		return coorX;
	}

	public int getCoorY() {
		return coorY;
	}

	public void setCoorX(int coorX) {
		this.coorX = coorX;
	}

	public void setCoorY(int coorY) {
		this.coorY = coorY;
	}

	@Override
	public String toString() {
		if (alive) {
			return "LifeSquare [x=" + (this.coorX) + " y=" + (this.coorY) + "] is Active";
		} else {
			return "LifeSquare [x=" + (this.coorX) + " y=" + (this.coorY) + "] is Inactive";
		}
	}
}
