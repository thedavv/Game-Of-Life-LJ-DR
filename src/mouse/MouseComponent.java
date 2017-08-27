package mouse;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class MouseComponent extends JComponent {
	List<Rectangle2D> grid = new ArrayList<>();
	List<Rectangle2D> filledSquares = new ArrayList<>();
	Rectangle2D currentGridSquare = null;
	Rectangle2D currentFilledSquare = null;

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	private static final int DEFAULT_SQUARE_SIZE = 10;

	public MouseComponent() {
		grid.add(new Rectangle2D.Double(0, 0, DEFAULT_SQUARE_SIZE, DEFAULT_SQUARE_SIZE));
		grid.add(new Rectangle2D.Double(10, 0, DEFAULT_SQUARE_SIZE, DEFAULT_SQUARE_SIZE));
		grid.add(new Rectangle2D.Double(20, 0, DEFAULT_SQUARE_SIZE, DEFAULT_SQUARE_SIZE));
		grid.add(new Rectangle2D.Double(30, 0, DEFAULT_SQUARE_SIZE, DEFAULT_SQUARE_SIZE));
		grid.add(new Rectangle2D.Double(40, 0, DEFAULT_SQUARE_SIZE, DEFAULT_SQUARE_SIZE));

		// add listeners
		addMouseListener(new MouseEventHandler());
		addMouseMotionListener(new MouseEventHandler());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (Rectangle2D rect : grid) {
			g2.setColor(Color.BLACK);
			g2.draw(rect);
		}

		// TODO my rectangles
		for (Rectangle2D rect : filledSquares) {
			g2.setColor(Color.RED);
			g2.fill(rect);
			g2.setColor(Color.BLACK);
			g2.draw(rect);
		}
	}

	public Rectangle2D findGridSquare(Point2D point) {
		for (Rectangle2D rect : grid) {
			if (rect.contains(point)) {
				return rect;
			}
		}
		return null;
	}

	public Rectangle2D findFilledSquare(Point2D point) {
		for (Rectangle2D rect : filledSquares) {
			if (rect.contains(point)) {
				return rect;
			}
		}
		return null;
	}

	public void addActiveSquareOnGrid(Rectangle2D rectangle) {
		double x = rectangle.getX();
		double y = rectangle.getY();

		currentGridSquare = new Rectangle2D.Double(x, y, DEFAULT_SQUARE_SIZE, DEFAULT_SQUARE_SIZE);
		filledSquares.add(currentGridSquare);
		repaint();
	}

	public void removeActiveSquareOnrid(Rectangle2D rectangle) {
		if (rectangle == currentFilledSquare)
			currentFilledSquare = null;
		filledSquares.remove(rectangle);
		repaint();
	}

	class MouseEventHandler extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			currentGridSquare = findGridSquare(e.getPoint());
			currentFilledSquare = findFilledSquare(e.getPoint());
			if (currentGridSquare != null && currentFilledSquare != null && e.getClickCount() >= 2) {
				// TODO remove test
				System.out.println("Removed rectangle at point " + e.getPoint() + " " + currentGridSquare.toString());
				removeActiveSquareOnrid(currentFilledSquare);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO remove test
			System.out.println("clicked");
			currentGridSquare = findGridSquare(e.getPoint());
			currentFilledSquare = findFilledSquare(e.getPoint());
			if (currentGridSquare != null && currentFilledSquare == null) {
				// TODO remove test
				System.out.println("Filled rectangle at point " + e.getPoint() + " " + currentGridSquare.toString());
				addActiveSquareOnGrid(currentGridSquare);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			currentFilledSquare = findFilledSquare(e.getPoint());
			if (currentFilledSquare != null) {
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			} else {
				setCursor(Cursor.getDefaultCursor());
			}
		}
	}
}
