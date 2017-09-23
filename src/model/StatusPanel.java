package model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	// TODO : current stored pattern

	public int cycleCount = 0;
	public int aliveCellCount = 0;
	public int mouseX = 0;
	public int mouseY = 0;
	public int stepSize = 1;

	private JLabel cycleText = new JLabel("Cycle: " + cycleCount);
	private JLabel stepText = new JLabel("Step Size: " + stepSize);
	private JLabel numOfAlive = new JLabel("Number of Living Cells: " + aliveCellCount);
	private JLabel mouseXY = new JLabel("Square [" + mouseX + "," + mouseY + "]");
	private JLabel patternText = new JLabel("text");

	public StatusPanel() {
		// setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// setLayout(new BorderLayout()); // advised
		// setPreferredSize(new Dimension(getWidth(), 18));

		// JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5,
		// 3));
		// leftPanel.setOpaque(false);
		// add(leftPanel, BorderLayout.WEST);
		// leftPanel.setBackground(Color.BLACK);

		add(Box.createHorizontalStrut(10));
		// cycleText.setHorizontalAlignment(SwingConstants.LEFT);
		add(cycleText);
		add(Box.createHorizontalStrut(10));
		add(new SeparatorBar(Color.GRAY, Color.WHITE));
		// add(Box.createHorizontalStrut(10));
		add(stepText);
		add(Box.createHorizontalStrut(10));
		add(new SeparatorBar(Color.GRAY, Color.WHITE));
		add(Box.createHorizontalStrut(10));
		add(numOfAlive);
		add(Box.createHorizontalStrut(10));
		add(new SeparatorBar(Color.GRAY, Color.WHITE));
		add(Box.createHorizontalStrut(10));
		add(mouseXY);
		add(Box.createHorizontalGlue());
		add(patternText);
		add(Box.createHorizontalStrut(5));

	}

	public void updateCountText() {
		cycleText.setText("Cycle: " + this.cycleCount);
	}

	public void updateStepText(int stepSize) {
		this.stepSize = stepSize;
		stepText.setText("Step Size: " + stepSize);
	}

	public void updateCellCount() {
		numOfAlive.setText("Number of Living Cells: " + this.aliveCellCount);
	}

	public void updateMouseCoors() {
		mouseXY.setText("Square [" + mouseX + "," + mouseY + "]");
	}

	class SeparatorBar extends JPanel {

		private static final long serialVersionUID = 1L;

		protected Color leftColor;
		protected Color rightColor;

		public SeparatorBar(Color leftColor, Color rightColor) {
			this.leftColor = leftColor;
			this.rightColor = rightColor;
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(leftColor);
			g.drawLine(0, 2, 0, (getHeight() - 2));
			g.setColor(rightColor);
			g.drawLine(1, 2, 1, (getHeight() - 2));
		}

	}

}
