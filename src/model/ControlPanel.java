package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

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
	boolean startPressed = false;

	// buttons
	private JButton stepButton = new JButton("Step");
	private JButton startButton = new JButton("Start");
	private JButton stopButton = new JButton("Stop");
	private JButton clearScreenButton = new JButton("Clc");
	private JButton closeAppButton = new JButton("Close");

	// sliders
	// JLabel sliderLabel = new JLabel("Game Speed", JLabel.CENTER);
	private JSlider stepSpeedSlider = new JSlider();

	public ControlPanel(GridFrame frame) {
		this.frame = frame;
		addComponentsToPanel();
		setBackground(Color.WHITE);
		addActionListeners();
	}

	public ControlPanel(int panelWidth, GridFrame frame) {
		this.frame = frame;
		this.panelWidth = panelWidth;
		addComponentsToPanel();
		addActionListeners();
		setBackground(Color.WHITE);
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
		stepSpeedSlider.setBackground(Color.WHITE);
	}

	private void customizeButton(JButton b) {
		b.setBackground(new Color(59, 89, 182));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	private void addComponentsToPanel() {
		customizeButton(stepButton);
		customizeButton(startButton);
		customizeButton(stopButton);
		customizeButton(clearScreenButton);
		customizeButton(closeAppButton);

		add(stepButton);
		add(startButton);
		add(stopButton);
		add(clearScreenButton);
		add(closeAppButton);
		createSlider();
		// add(sliderLabel);
		add(stepSpeedSlider);

		setBackground(Color.WHITE);
	}

	// action Listeners
	private void addActionListeners() {
		startButton.addActionListener((ActionEvent e) -> {
			startButton.setEnabled(false);
			stepButton.setEnabled(false);
			clearScreenButton.setEnabled(false);
			GridComponent gc = frame.getGridC();
			startPressed = true;
			new Thread() {
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

		stepButton.addActionListener((ActionEvent e) -> {
			GridComponent gc = frame.getGridC();
			gc.createNextGeneration(gc.getSqGrid(), gc.getSqGridTemp());
			gc.setNextGenerationAsCurrentGeneration(gc.getSqGrid(), gc.getSqGridTemp());
			gc.resetGrid(gc.getSqGridTemp());
			gc.repaint();
		});

		stopButton.addActionListener((ActionEvent e) -> {
			startPressed = false;
			startButton.setEnabled(true);
			stepButton.setEnabled(true);
			clearScreenButton.setEnabled(true);
		});

		clearScreenButton.addActionListener((ActionEvent e) -> {
			GridComponent gc = frame.getGridC();
			gc.resetGrid(gc.getSqGrid());
			gc.resetGrid(gc.getSqGridTemp());
			gc.repaint();
		});
		
		closeAppButton.addActionListener((ActionEvent e) -> {
			frame.dispose();
		});

		stepSpeedSlider.addChangeListener((ChangeEvent e) -> {
		});
	}
}
