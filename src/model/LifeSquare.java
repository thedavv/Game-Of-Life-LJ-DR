package model;

import java.awt.Rectangle;

public class LifeSquare extends Rectangle {

	private static final long serialVersionUID = 1L;

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

	public LifeSquare(int x, int y, int width, int height, boolean active) {
		super(x, y, width, height);
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

	@Override
	public String toString() {
		if (alive) {
			return "LifeSquare [x=" + (this.x / width) + " y=" + (this.y / width) + "] is Active";
		} else {
			return "LifeSquare [x=" + (this.x / width) + " y=" + (this.y / width) + "] is Inactive";
		}
	}
}
