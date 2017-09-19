package model;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ControlPanel extends JPanel {
	private static final long serialVersionUID = -8071333801420726916L;
	// panel constants
	private static final int DEFAULT_HEIGHT = 52;
	// TODO count from Frame
	private static final int DEFAULT_WIDTH = 600;

	// slider constants
	private static final int SPEED_MIN = 1;
	private static final int SPEED_MAX = 10;
	private static final int SPEED_INIT = 1;
	private static final int TICK_SPACING = 1;

	// variables
	private int panelWidth = DEFAULT_WIDTH;
	private int panelHeight = DEFAULT_HEIGHT;
	private GridFrame frame;
	private boolean startPressed = false; // TODO why is this not private?
	private int numOfSteps = 1;

	// buttons
	private JButton stepButton = new JButton("<HTML>St<U>e</U>p</HTML>");
	private JButton startButton = new JButton("<HTML><U>R</U>un</HTML>");
	private JButton stopButton = new JButton("<HTML><U>S</U>top</HTML>");
	private JButton clearScreenButton = new JButton("<HTML><U>C</U>lear</HTML>");
	private JButton closeAppButton = new JButton("<HTML>E<U>x</U>it</HTML>");

	// sliders
	// JLabel sliderLabel = new JLabel("Game Speed", JLabel.CENTER);
	private JSlider stepSpeedSlider = new JSlider();

	public ControlPanel(GridFrame frame) {
		setBackground(Const.WHITE);

		this.frame = frame;
		
		addComponentsToPanel();
		addActionListeners();
	}

	public ControlPanel(int panelWidth, GridFrame frame) {
		setBackground(Const.WHITE);

		this.frame = frame;
		this.panelWidth = panelWidth;
		
		addComponentsToPanel();
		addActionListeners();
		
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(panelWidth, panelHeight);
	}

	// private methods for look and feel
	private void createSlider() {
		stepSpeedSlider.setMinimum(SPEED_MIN);
		stepSpeedSlider.setMaximum(SPEED_MAX);
		stepSpeedSlider.setMajorTickSpacing(TICK_SPACING);
		stepSpeedSlider.setPaintTicks(true);
		stepSpeedSlider.setPaintLabels(true);
		stepSpeedSlider.setValue(SPEED_INIT);
		stepSpeedSlider.setBackground(Const.WHITE);
	}

	private void customizeButton(JButton b) {
		b.setBackground(Const.MAT_BLUE);
		b.setForeground(Const.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	private void addComponentsToPanel() {
		customizeButton(stepButton);
		customizeButton(startButton);
		customizeButton(stopButton);
		customizeButton(clearScreenButton);
		customizeButton(closeAppButton);
		createSlider();

		// TODO figure out a non-ALT solution
		stepButton.setMnemonic(KeyEvent.VK_E);
		startButton.setMnemonic(KeyEvent.VK_R);
		stopButton.setMnemonic(KeyEvent.VK_S);
		clearScreenButton.setMnemonic(KeyEvent.VK_C);
		closeAppButton.setMnemonic(KeyEvent.VK_X);

		add(stepButton);
		add(startButton);
		add(stopButton);
		add(clearScreenButton);
		add(closeAppButton);
		add(stepSpeedSlider);
	}

	// action Listeners
	private void addActionListeners() {
		startButton.addActionListener(e -> {
			startButton.setEnabled(false);
			stepButton.setEnabled(false);
			clearScreenButton.setEnabled(false);
			GridComponent gc = frame.getGridC();
			startPressed = true;
			new Thread() {
				@Override
				public void run() {
					while (startPressed) {
						gc.createNextGeneration(gc.getSqGrid(), gc.getSqGridTemp());
						gc.setNextGenerationAsCurrentGeneration(gc.getSqGrid(), gc.getSqGridTemp());
						gc.resetGrid(gc.getSqGridTemp());
						gc.repaint();
						try {
							sleep(101 - 10 * stepSpeedSlider.getValue());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		});

		stepButton.addActionListener(e -> {
			for (int i = 0; i < numOfSteps; i++) {
				GridComponent gc = frame.getGridC();
				gc.createNextGeneration(gc.getSqGrid(), gc.getSqGridTemp());
				gc.setNextGenerationAsCurrentGeneration(gc.getSqGrid(), gc.getSqGridTemp());
				gc.resetGrid(gc.getSqGridTemp());
				gc.repaint();
			}
		});

		stopButton.addActionListener(e -> {
			startPressed = false;
			startButton.setEnabled(true);
			stepButton.setEnabled(true);
			clearScreenButton.setEnabled(true);
		});

		clearScreenButton.addActionListener(e -> {
			GridComponent gc = frame.getGridC();
			gc.resetGrid(gc.getSqGrid());
			gc.resetGrid(gc.getSqGridTemp());
			gc.repaint();
		});

		closeAppButton.addActionListener(e -> System.exit(0));
	}
}
