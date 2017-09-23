package model;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.KeyStroke;

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
	private boolean startPressed = false;
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

		// setFocusable(true);
		// addHotkeys();
		addKeybindings();
	}

	public ControlPanel(int panelWidth, GridFrame frame) {
		setBackground(Const.WHITE);

		this.frame = frame;
		this.panelWidth = panelWidth;

		addComponentsToPanel();
		addActionListeners();
		addKeybindings();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(panelWidth, panelHeight);
	}

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

		add(stepButton);
		add(startButton);
		add(stopButton);
		add(clearScreenButton);
		add(closeAppButton);
		add(stepSpeedSlider);
	}

	private void addActionListeners() {
		startButton.addActionListener(e -> startButtonAction());

		stepButton.addActionListener(e -> stepButtonAction());

		stopButton.addActionListener(e -> stopButtonAction());

		clearScreenButton.addActionListener(e -> clearButtonAction());

		closeAppButton.addActionListener(e -> exitButtonAction());

		stepSpeedSlider.addChangeListener(e -> {
		});
	}

	@SuppressWarnings("serial")
	private void addKeybindings() {
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"), "E pressed");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "R pressed");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "S pressed");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("C"), "C pressed");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("X"), "X pressed");

		this.getActionMap().put("E pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (stepButton.isEnabled()) {
					stepButtonAction();
				}
			}
		});
		this.getActionMap().put("R pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (startButton.isEnabled()) {
					startButtonAction();
				}
			}
		});
		this.getActionMap().put("S pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopButtonAction();
			}
		});
		this.getActionMap().put("C pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (clearScreenButton.isEnabled()) {
					clearButtonAction();
				}
			}
		});
		this.getActionMap().put("X pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitButtonAction();
			}
		});
	}
  
	@Deprecated
	void addHotkeys() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_E) {
					if (stepButton.isEnabled()) {
						stepButtonAction();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_R) {
					if (startButton.isEnabled()) {
						startButtonAction();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					stopButtonAction();
				}
				if (e.getKeyCode() == KeyEvent.VK_C) {
					if (clearScreenButton.isEnabled()) {
						clearButtonAction();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_X) {
					exitButtonAction();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	public void startButtonAction() {
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
	}

	public void stepButtonAction() {
		for (int i = 0; i < numOfSteps; i++) {
			GridComponent gc = frame.getGridC();
			gc.createNextGeneration(gc.getSqGrid(), gc.getSqGridTemp());
			gc.setNextGenerationAsCurrentGeneration(gc.getSqGrid(), gc.getSqGridTemp());
			gc.resetGrid(gc.getSqGridTemp());
			gc.repaint();
		}
	}

	public void stopButtonAction() {
		startPressed = false;
		startButton.setEnabled(true);
		stepButton.setEnabled(true);
		clearScreenButton.setEnabled(true);
	}

	public void clearButtonAction() {
		GridComponent gc = frame.getGridC();
		gc.resetGrid(gc.getSqGrid());
		gc.resetGrid(gc.getSqGridTemp());
		gc.repaint();
	}

	public void exitButtonAction() {
		System.exit(0);
	}
}
