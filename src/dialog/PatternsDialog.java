package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dialog.patterns.GifPattern;
import dialog.patterns.ImagePattern;
import dialog.patterns.Pattern;
import dialog.patterns.PatternFactory;
import model.Const;
import model.GridComponent;

/**
 * Creates a PatternsDialogTest that holds many patterns that you can select for
 * your game or you can paint your own pattern in this
 */
public class PatternsDialog extends JDialog {
	private static final long serialVersionUID = 7434553908752139595L;

	// default dimensions
	private static final int X_LENGHT_OF_RIGID_AREA = 5;
	private static final int Y_LENGHT_OF_RIGID_AREA = 50;
	private static final int BUTTON_X_LENGHT = 150;
	private static final int BUTTON_Y_LENGHT = 30;

	// buttons
	private List<JButton> topPanelButtons = new ArrayList<>();
	private List<JButton> leftPanelButtons = new ArrayList<>();
	private List<JButton> bottomPanelButtons = new ArrayList<>();

	// button names
	private List<String> bottomPannelButtonNames = Arrays.asList("Store", "Close");
	private List<String> patternNames = Arrays.asList("Still Life", "Oscilators", "Spaceships", "Breeders",
			"Paint Your Patern");
	private List<String> stillLifePatterns = Arrays.asList("Block", "Beehive", "Loaf", "Boat", "Tub");
	private List<String> oscilatorPatterns = Arrays.asList("Blinker", "Toad", "Beacon", "Pulsar", "Pentadecathon");
	private List<String> spaceshipPatterns = Arrays.asList("Glider", "LWSS");
	private List<String> breederPatterns = Arrays.asList("Gosper glider gun");
	private List<String> customPatterns = Arrays.asList("Show grid");

	private Hashtable<String, List<String>> leftPanelButtonsHashTable = new Hashtable<>();

	// panels
	private TopPanel topJPanel = null;
	private BottomPanel bottomJPanel = null;
	private LeftPanel leftJPanel = null;
	private RightPanel rightJPanel = null;
	private CenterPanel centerJPanel = null;
	private Container container = null;

	// helper variables
	private String topPannelButtonPressedName = "Still Life";
	private List<Integer[]> currentPattern = null;
	private GridComponent gridComponent = null;

	// factory
	private PatternFactory patternFactory = new PatternFactory();

	// patterns
	private Pattern pattern = null;
	private Pattern customPattern = null;
	private ImagePattern patternImage = null;
	private GifPattern patternGif = null;

	public PatternsDialog(GridComponent gridComponent) {
		this.gridComponent = gridComponent;

		setVisible(false);
		setResizable(false);

		topJPanel = new TopPanel();
		bottomJPanel = new BottomPanel();
		leftJPanel = new LeftPanel();
		rightJPanel = new RightPanel();
		centerJPanel = new CenterPanel();
		container = new Container();

		// add(topJPanel, BorderLayout.PAGE_START);
		// add(leftJPanel, BorderLayout.LINE_START);
		// add(centerJPanel, BorderLayout.CENTER);
		// add(rightJPanel, BorderLayout.LINE_END);
		// add(bottomJPanel, BorderLayout.PAGE_END);

		add(container);
		pack();

	}

	/**
	 * Creates Customized JButton. Button after customisation will be Light blue
	 * 
	 * @param buttonName
	 *            is the name of a button you want to give
	 * @return customized JButton
	 */
	public JButton createStylizedButton(String buttonName) {
		JButton button = new JButton(buttonName);
		button.setBackground(Const.MAT_BLUE);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		// for flow
		button.setPreferredSize(new Dimension(BUTTON_X_LENGHT, BUTTON_Y_LENGHT));
		// for box
		button.setMaximumSize(new Dimension(BUTTON_X_LENGHT, BUTTON_Y_LENGHT));
		return button;
	}

	/**
	 * Fills List<JButton> with customized buttons type JButton
	 * 
	 * @param buttonNames
	 *            are the names you want to give to buttons
	 * @param buttonList
	 *            is the button list that needs to be filled
	 */
	public void addButtonsToList(List<String> buttonNames, List<JButton> buttonList) {
		for (String string : buttonNames) {
			buttonList.add(createStylizedButton(string));
		}
	}

	/**
	 * Method adds Buttons to JPanel. It creates a riggid area between buttons
	 * that is dimension size
	 * 
	 * @param dimension
	 *            is the size of the rigid area between buttons
	 * @param jPanelButtons
	 *            is the List<JButton> where you have buttons you want to add to
	 *            JPanel
	 */
	public void addButtonsToPanel(Dimension dimension, List<JButton> jPanelButtons) {
		add(Box.createRigidArea(dimension));
		for (JButton jButton : jPanelButtons) {
			add(jButton);
			add(Box.createRigidArea(new Dimension(dimension)));
		}
	}

	/**
	 * Returns this PatternsDialog
	 * 
	 * @return PatternsDialog
	 */
	public PatternsDialog getDialogInstance() {
		return PatternsDialog.this;
	}

	/**
	 * This class is used to create LeftPanel pannel buttons. Buttons are
	 * ordered with Box layout around Y axis. Buttons are changed when there is
	 * action from TopPanel buttons. LeftPanel buttons handle the printout of
	 * patterns onto canvas
	 */
	class LeftPanel extends JPanel implements ActionListener {
		private static final long serialVersionUID = -8459273757426477308L;

		public LeftPanel() {
			// link top buttons with left side buttons
			leftPanelButtonsHashTable.put(patternNames.get(0), stillLifePatterns);
			leftPanelButtonsHashTable.put(patternNames.get(1), oscilatorPatterns);
			leftPanelButtonsHashTable.put(patternNames.get(2), spaceshipPatterns);
			leftPanelButtonsHashTable.put(patternNames.get(3), breederPatterns);
			leftPanelButtonsHashTable.put(patternNames.get(4), customPatterns);

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setBackground(Const.BLURRY_WOOD);

			// create buttons
			// this is initial creation it doesnt use createLeftPanelButtons
			// method it uses parent addButtonsToList
			addButtonsToList(leftPanelButtonsHashTable.get(patternNames.get(0)), leftPanelButtons);

			// add buttons to pannel
			addButtonsToLeftPanel();

			// add listeners
			addActionListeners();

			pack();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// clear currentPattern list
			if (currentPattern != null) {
				currentPattern.clear();
			}

			// add pattern to currentPattern
			String pressedButtonName = e.getActionCommand();
			switch (pressedButtonName) {
			case "Block":
				creatImgPattern(pressedButtonName, "resources/block.jpg");
				break;
			case "Beehive":
				creatImgPattern(pressedButtonName, "resources/beehive.jpg");
				break;
			case "Loaf":
				creatImgPattern(pressedButtonName, "resources/loaf.jpg");
				break;
			case "Boat":
				creatImgPattern(pressedButtonName, "resources/boat.jpg");
				break;
			case "Tub":
				creatImgPattern(pressedButtonName, "resources/tub.jpg");
				break;
			case "Blinker":
				createGifPattern(pressedButtonName, "resources/blinker.gif");
				break;
			case "Toad":
				createGifPattern(pressedButtonName, "resources/toad.gif");
				break;
			case "Beacon":
				createGifPattern(pressedButtonName, "resources/beacon.gif");
				break;
			case "Pulsar":
				createGifPattern(pressedButtonName, "resources/pulsar.gif");
				break;
			case "Pentadecathon":
				createGifPattern(pressedButtonName, "resources/pentadecathlon.gif");
				break;
			case "Glider":
				createGifPattern(pressedButtonName, "resources/glider.gif");
				break;
			case "LWSS":
				createGifPattern(pressedButtonName, "resources/LWSS.gif");
				break;
			case "Gosper glider gun":
				creatImgPattern(pressedButtonName, "resources/breeder.png");
				break;
			case "Show grid":
				createGridPattern();
				break;
			default:
				break;
			}
		}

		/**
		 * Adds picture to centerPanel and stores pattern
		 * 
		 * @param patternName
		 *            is the name of the pattern
		 * @param picUrl
		 *            is the url of picture
		 */
		private void creatImgPattern(String patternName, String picUrl) {
			pattern = patternFactory.createImagePattern(patternName);
			patternImage = patternFactory.createImagePattern(patternName);
			currentPattern = pattern.createPattern();
			try {
				centerJPanel.removeAll();

				BufferedImage myPicture = patternImage.createImage(picUrl);
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));

				centerJPanel.add(picLabel);
				centerJPanel.revalidate();
				centerJPanel.repaint();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		private void createGifPattern(String patternName, String picUrl) {
			pattern = patternFactory.createGifPattern(patternName);
			patternGif = patternFactory.createGifPattern(patternName);
			currentPattern = pattern.createPattern();

			centerJPanel.removeAll();

			Icon myGif = patternGif.createGif(picUrl);
			JLabel gifLabel = new JLabel(myGif);

			centerJPanel.add(gifLabel);
			centerJPanel.revalidate();
			centerJPanel.repaint();
		}

		private void createGridPattern() {
			customPattern = patternFactory.createCustomPattern(centerJPanel.getGrid());
			currentPattern = customPattern.createPattern();

			centerJPanel.removeAll();

			centerJPanel.add(centerJPanel.getGrid());
			centerJPanel.revalidate();
			centerJPanel.repaint();
		}

		/**
		 * creates list with left panel buttons from leftPanelButtonsHashTable.
		 * Its important that leftPanelHashKey String is Hash key in
		 * leftPanelButtonsHashTable
		 * 
		 * @param leftPanelHashKey
		 *            is the hash key in leftPanelButtonsHashTable
		 */
		public void createLeftPanelButtons(String leftPanelHashKey) {
			addButtonsToList(leftPanelButtonsHashTable.get(topPannelButtonPressedName), leftPanelButtons);
		}

		/**
		 * Adds buttons from leftPanelButtons to JPannel
		 */
		public void addButtonsToLeftPanel() {
			add(Box.createRigidArea(new Dimension(X_LENGHT_OF_RIGID_AREA * 3, Y_LENGHT_OF_RIGID_AREA)));
			for (JButton jButton : leftPanelButtons) {
				add(jButton);
				add(Box.createRigidArea(new Dimension(X_LENGHT_OF_RIGID_AREA * 3, X_LENGHT_OF_RIGID_AREA * 3)));
			}
		}

		public void addActionListeners() {
			for (JButton jButton : leftPanelButtons) {
				jButton.addActionListener(this);
			}
		}
	}

	/**
	 * This class is used to create RightPanel pannel .
	 */
	class RightPanel extends JPanel {
		private static final long serialVersionUID = 4949097861670413713L;

		public RightPanel() {
			setLayout(new FlowLayout());
			setBackground(Const.BLURRY_WOOD);
			add(Box.createRigidArea(new Dimension(X_LENGHT_OF_RIGID_AREA, 0)));
			pack();
		}
	}

	/**
	 * This class is used to create TopPanel and its buttons buttons. Buttons
	 * are ordered with Flow layout. Buttons change LeftPanel buttons.
	 */
	class TopPanel extends JPanel implements ActionListener {
		private static final long serialVersionUID = -325195948601882615L;

		public TopPanel() {
			setLayout(new FlowLayout());
			setBackground(Const.BLURRY_WOOD);

			// create buttons
			addButtonsToList(patternNames, topPanelButtons);

			// add buttons to panel
			add(Box.createRigidArea(new Dimension(0, Y_LENGHT_OF_RIGID_AREA)));
			for (JButton jButton : topPanelButtons) {
				add(jButton);
				add(Box.createRigidArea(new Dimension(X_LENGHT_OF_RIGID_AREA, 0)));
			}
			add(Box.createRigidArea(new Dimension(X_LENGHT_OF_RIGID_AREA, 0)));
			pack();

			// add listeners
			for (JButton jButton : topPanelButtons) {
				jButton.addActionListener(this);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object actionObj = e.getSource();
			if (actionObj instanceof JButton) {
				for (JButton b : topPanelButtons) {
					if (b.equals(actionObj)) {
						topPannelButtonPressedName = e.getActionCommand();

						// update center panel
						centerJPanel.removeAll();
						centerJPanel.repaint();
						centerJPanel.revalidate();

						// if paint your own component is selected
						b.setEnabled(false);

						// remove buttons from left panel
						leftPanelButtons.clear();
						leftJPanel.removeAll();

						// add components to left panel
						leftJPanel.createLeftPanelButtons(topPannelButtonPressedName);
						leftJPanel.addButtonsToLeftPanel();
						leftJPanel.addActionListeners();

						// update left panel
						leftJPanel.revalidate();
						leftJPanel.repaint();
					} else {
						b.setEnabled(true);
					}
				}
			}
		}
	}

	/**
	 * This class is used to create BottomPanel and its buttons. Buttons are
	 * ordered with Box layout. Buttons commit or return to main frame
	 */
	class BottomPanel extends JPanel implements ActionListener {
		private static final long serialVersionUID = 4111518793729191748L;

		BottomPanel() {
			setBackground(Const.BLURRY_WOOD);
			setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
			add(Box.createHorizontalGlue());

			// create buttons
			addButtonsToList(bottomPannelButtonNames, bottomPanelButtons);

			for (JButton jButton : bottomPanelButtons) {
				add(jButton);
				add(Box.createRigidArea(new Dimension(X_LENGHT_OF_RIGID_AREA, 0)));
			}
			add(Box.createRigidArea(new Dimension(X_LENGHT_OF_RIGID_AREA, 0)));
			add(Box.createRigidArea(new Dimension(X_LENGHT_OF_RIGID_AREA, 0)));

			// add listeners
			for (JButton jButton : bottomPanelButtons) {
				jButton.addActionListener(this);
			}
			pack();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			switch (actionCommand) {
			case "Close":
				getDialogInstance().setVisible(false);
				break;
			case "Store":
				if (currentPattern != null) {
					if (!currentPattern.isEmpty()) {
						if (topPannelButtonPressedName == patternNames.get(4)) {
							currentPattern = customPattern.createPattern();
							gridComponent.setStoredPatternPositons(currentPattern);
							currentPattern.clear();
						} else {
							gridComponent.setStoredPatternPositons(currentPattern);
							currentPattern.clear();
						}
						JOptionPane.showMessageDialog(this, "Pattern Stored, press close to return");
					} else {
						JOptionPane.showMessageDialog(this, "Nothing Stored, select Pattern");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Nothing Stored, select Pattern");
				}
				break;

			default:
				break;
			}
		}
	}

	/**
	 * Center frame is for painting and showing current pattern
	 */
	class CenterPanel extends JPanel {
		private static final long serialVersionUID = 4699926243224863908L;
		private final static int DEFAULT_WIDTH = 300;
		private final static int DEFAULT_HEIGHT = 300;
		private GridComponent gridOfLifeSquares = new GridComponent(30, 30);

		CenterPanel() {
			setBackground(Const.BLURRY_WOOD);
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		}

		public GridComponent getGrid() {
			return gridOfLifeSquares;
		}

		public void setGrid(GridComponent comp) {
			this.gridOfLifeSquares = comp;
		}
	}

	/**
	 * Container that holds all panels and puts them in Border Layout
	 */
	class Container extends JPanel {
		private static final long serialVersionUID = -2127189115076318418L;

		public Container() {
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);
			add(topJPanel, BorderLayout.PAGE_START);
			add(leftJPanel, BorderLayout.LINE_START);
			add(centerJPanel, BorderLayout.CENTER);
			add(rightJPanel, BorderLayout.LINE_END);
			add(bottomJPanel, BorderLayout.PAGE_END);
		}
	}
}
